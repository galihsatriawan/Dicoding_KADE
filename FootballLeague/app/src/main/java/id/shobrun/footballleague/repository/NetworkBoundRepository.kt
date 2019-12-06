package id.shobrun.footballleague.repository

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import id.shobrun.footballleague.api.ApiResponse
import id.shobrun.footballleague.mapper.NetworkResponseMapper
import id.shobrun.footballleague.models.NetworkResponseModel
import id.shobrun.footballleague.models.Resource
import timber.log.Timber

abstract class NetworkBoundRepository<ResultType,
        RequestType : NetworkResponseModel,
        Mapper : NetworkResponseMapper<RequestType>>
internal constructor() {
    companion object{
        val TAG = NetworkBoundRepository.javaClass.name
    }
    private val result: MediatorLiveData<Resource<ResultType>> = MediatorLiveData()

    init {
        Log.d("NetworkBound","Injection NetworkBoundRepository")
        result.postValue(Resource.loading(null))
        val loadedFromDB = this.loadFromDb()
        result.addSource(loadedFromDB) { data ->
            result.removeSource(loadedFromDB)
            if (shouldFetch(data)) {
                Timber.d("$TAG is fetch")
                result.postValue(Resource.loading(null))
                fetchFromNetwork(loadedFromDB)
            } else {
                Timber.d("$TAG is db")
                result.addSource<ResultType>(loadedFromDB) { newData ->
                    setValue(Resource.success(newData, false))
                }
            }
        }
    }

    private fun fetchFromNetwork(loadedFromDB: LiveData<ResultType>) {
        Timber.d("$TAG fetch data")
        val apiResponse = fetchService()
        result.addSource(apiResponse) { response ->
            response?.let {
                when (response.isSuccessful) {
                    true -> {
                        Timber.d("$TAG fetch success")
                        response.body?.let {
                            Timber.d("$TAG data : $it")
                            saveFetchData(it)
                            val loaded = loadFromDb()
                            result.addSource(loaded) { newData ->
                                Timber.d("$TAG new Data : ${newData.toString()}")
                                newData?.let {
                                    setValue(Resource.success(newData, mapper().onLastPage(response.body)))
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
