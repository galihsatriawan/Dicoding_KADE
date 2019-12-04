package id.shobrun.footballleague.ui.events.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.Status
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.models.entity.Team
import id.shobrun.footballleague.repository.EventRepository
import id.shobrun.footballleague.repository.TeamRepository
import id.shobrun.footballleague.utils.AbsentLiveData
import javax.inject.Inject

class DetailEventViewModel @Inject constructor(val repository : EventRepository, val teamRepository: TeamRepository): ViewModel(){
    private val eventIdLiveData : MutableLiveData<Int> = MutableLiveData()
    val eventLiveData : LiveData<Resource<Event>>
    val homeTeamLiveData : LiveData<Resource<Team>>
    val awayTeamLiveData : LiveData<Resource<Team>>
    val loading : LiveData<Boolean>
    init {
        eventLiveData = eventIdLiveData.switchMap {
            eventIdLiveData.value?.let { repository.getDetailEvent(it) }
                ?:AbsentLiveData.create()
        }
        loading = eventLiveData.switchMap {
            val mutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
            mutableLiveData.postValue((it.status == Status.LOADING))
            mutableLiveData
        }
        homeTeamLiveData = eventLiveData.switchMap {
            it.data?.idHomeTeam.let {
                teamRepository.loadTeamDetailById(it ?: -1)
            }
        }
        awayTeamLiveData = eventLiveData.switchMap {
            it.data?.idAwayTeam.let {
                teamRepository.loadTeamDetailById(it ?: -1)
            }
        }
    }
    fun postEventId(idEvent : Int){
        eventIdLiveData.postValue(idEvent)
    }
}