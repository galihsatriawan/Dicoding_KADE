package id.shobrun.footballleague.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import id.shobrun.footballleague.AppExecutors
import id.shobrun.footballleague.api.ApiResponse
import id.shobrun.footballleague.mapper.NetworkResponseMapper
import id.shobrun.footballleague.models.NetworkResponseModel
import id.shobrun.footballleague.models.Resource


abstract class NetworkBoundRepository<ResultType,
        RequestType : NetworkResponseModel,
        Mapper : NetworkResponseMapper<RequestType>>
internal constructor(private val appExecutors: AppExecutors) {

    private val result: MediatorLiveData<Resource<ResultType>> = MediatorLiveData()

    init {

        val loadedFromDB = this.loadFromDb()
        result.addSource(loadedFromDB) { data ->
            result.removeSource(loadedFromDB)
            if (shouldFetch(data)) {
                result.postValue(Resource.loading(null))
                fetchFromNetwork(loadedFromDB)
            } else {
                result.addSource<ResultType>(loadedFromDB) { newData ->
                    setValue(Resource.success(newData, false))
                }
            }
        }
    }

    private fun fetchFromNetwork(loadedFromDB: LiveData<ResultType>) {
        val apiResponse = fetchService()
        result.addSource(apiResponse) { response ->
            response?.let {
                when (response.isSuccessful) {
                    true -> {
                        if(response.body != null){
                            response.body?.let {
                                appExecutors.diskIO().execute{
                                    saveFetchData(it)
                                    appExecutors.mainThread().execute{
                                        // we specially request a new live data,
                                        // otherwise we will get immediately last cached value,
                                        // which may not be updated with latest results received from network.
                                        val loaded = loadFromDb()
                                        result.addSource(loaded) { newData ->
                                            newData?.let {
                                                setValue(Resource.success(newData, mapper().onLastPage(response.body)))
                                            }
                                        }
                                    }

                                }

                            }
                        }else{
                            appExecutors.mainThread().execute{
                                // reload from disk whatever we had
                                result.addSource(loadFromDb()) { newData ->
                                    newData?.let {
                                        setValue(Resource.success(newData,true))
                                    }
                                }
                            }
                        }
                    }
                    false -> {
                        result.removeSource(loadedFromDB)
                        onFetchFailed(response.message)
                        response.message?.let {
                            result.addSource<ResultType>(loadedFromDB) { newData ->
                                setValue(Resource.error(it, newData))
                            }
                        }
                    }
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        result.value = newValue
    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    @WorkerThread
    protected abstract fun saveFetchData(items: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun fetchService(): LiveData<ApiResponse<RequestType>>

    @MainThread
    protected abstract fun mapper(): Mapper

    @MainThread
    protected abstract fun onFetchFailed(message: String?)
}
