package id.shobrun.footballleague.ui.leagues.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.Status
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.repository.LeagueRepository
import id.shobrun.footballleague.utils.AbsentLiveData
import javax.inject.Inject


class DetailLeagueViewModel @Inject constructor(val repository: LeagueRepository) : ViewModel(){
    companion object{
        val TAG = this.javaClass.name
    }
    private val leagueIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val loading : LiveData<Boolean>
    val leagueLiveData : LiveData<Resource<League>>

    init {
        this.leagueLiveData = leagueIdLiveData.switchMap {
            this.leagueIdLiveData.value?.let {
                repository.loadLeagueDetail(it)
            }?: AbsentLiveData.create()
        }
        this.loading = leagueLiveData.switchMap {
            val mutableLiveData : MutableLiveData<Boolean> = MutableLiveData()
            mutableLiveData.postValue(it.status == Status.LOADING)
            mutableLiveData
        }
    }

    fun postLeagueId(id : Int){
        leagueIdLiveData.postValue(id)
    }
}