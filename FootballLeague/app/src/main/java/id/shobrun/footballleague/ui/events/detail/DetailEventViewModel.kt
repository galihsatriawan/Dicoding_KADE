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
import id.shobrun.footballleague.repository.utils.IEventLocalDB
import id.shobrun.footballleague.utils.AbsentLiveData
import timber.log.Timber
import javax.inject.Inject

class DetailEventViewModel @Inject constructor(val repository : EventRepository, val teamRepository: TeamRepository): ViewModel(){
    companion object{
        val TAG = DetailEventViewModel::class.java.name
    }
    private val eventIdLiveData : MutableLiveData<Int> = MutableLiveData()
    val eventLiveData : LiveData<Resource<Event>>
    var eventInDB : LiveData<Event>
    val homeTeamLiveData : LiveData<Resource<Team>>
    val awayTeamLiveData : LiveData<Resource<Team>>
    val loading : LiveData<Boolean>

    private var _isFavorite : MutableLiveData<Boolean> = MutableLiveData()
    val isFavorite :LiveData<Boolean>
    init {
        eventInDB = eventIdLiveData.switchMap {
            eventIdLiveData.value?.let {
                repository.getEventByIdInDb(it)
            }?: AbsentLiveData.create()
        }
        eventLiveData = eventIdLiveData.switchMap {
            eventIdLiveData.value?.let {
                repository.getDetailEvent(it)

            }
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
        isFavorite = eventInDB.switchMap {
            it.isFavorite.let {
                Timber.d("$TAG is Favorite: $it")
                postEventIsFavorite(it==1)
                _isFavorite
            }
        }

    }
    fun postEventId(idEvent : Int){
        eventIdLiveData.postValue(idEvent)
    }
    fun postEventIsFavorite (state : Boolean){
        _isFavorite.postValue(state)
    }
    fun onClickedFavorite(){
        eventInDB.value?.isFavorite.let{
            var currentFavorite : Boolean = it==1
            if(currentFavorite){
                removeToFavorite()
            }else{
                addToFavorite()
            }
            postEventIsFavorite(!currentFavorite)
        }
    }
    private fun addToFavorite(){
        val event : Event? = eventInDB.value
        event?.isFavorite = 1
        if(event!=null){
            repository.updateEventInDb(event)
        }
    }

    private fun removeToFavorite(){
        val event : Event? = eventInDB.value
        event?.isFavorite = 0
        if(event!=null){
            repository.updateEventInDb(event)
        }
    }



}