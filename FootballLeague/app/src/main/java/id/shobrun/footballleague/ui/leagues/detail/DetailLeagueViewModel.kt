package id.shobrun.footballleague.views.leagues

import androidx.lifecycle.*
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.repositories.LeagueRepository
import id.shobrun.footballleague.utils.AbsentLiveData
import timber.log.Timber
import javax.inject.Inject


class DetailLeagueViewModel @Inject constructor(val repository: LeagueRepository) : ViewModel(){
    companion object{
        val TAG = this.javaClass.name
    }
    private val leagueIdLiveData: MutableLiveData<Int> = MutableLiveData()

    val leagueLiveData : LiveData<Resource<League>>

    init {
        this.leagueLiveData = leagueIdLiveData.switchMap {
            this.leagueIdLiveData.value?.let {
                repository.loadLeagueDetail(it)
            }?: AbsentLiveData.create()
        }
    }

    fun postLeagueId(id : Int){
        leagueIdLiveData.postValue(id)
    }
}