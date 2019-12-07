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

class DetailEventViewModel @Inject constructor(val repository : EventRepository, private val teamRepository: TeamRepository): ViewModel(){
    companion object{
        val TAG = DetailEventViewModel::class.java.name
    }
    private val eventIdLiveData : MutableLiveData<Int> = MutableLiveData()
    val eventLiveData : LiveData<Resource<Event>>
    private val eventInDB : LiveData<Boolean>
    val homeTeamLiveData : LiveData<Resource<Team>>
    val awayTeamLiveData : LiveData<Resource<Team>>
    val loading : LiveData<Boolean>

    private var _isFavorite : MutableLiveData<Boolean> = MutableLiveData()
    val isFavorite :LiveData<Boolean>
    init {
        eventInDB = eventIdLiveData.switchMap {
            eventIdLiveData.value?.let {

                Timber.d("$TAG insert $it")
                val res = repository.getEventByIdInSqliteDb(it)

                Timber.d("$TAG timber ${res.value}")
                res
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
        /**
         *  Room Version

        isFavorite = eventInDB.switchMap {
            it.isFavorite.let {
                postEventIsFavorite(it==1)
                _isFavorite
            }
        }
         */

        isFavorite = eventInDB.switchMap {
            Timber.d("$TAG favorite event ${it}")
            postEventIsFavorite(it)
            _isFavorite
        }

    }
    fun postEventId(idEvent : Int){
        eventIdLiveData.postValue(idEvent)
    }
    private fun postEventIsFavorite (state : Boolean){
        _isFavorite.postValue(state)
    }
    fun onClickedFavorite(){
        /**
         * Room Version
        eventInDB.value?.isFavorite.let{
            val currentFavorite : Boolean = it==1
            if(currentFavorite){
                removeToFavorite()
            }else{
                addToFavorite()
            }
            postEventIsFavorite(!currentFavorite)
        }
        **/
        isFavorite.value?.let {
            if(it) removeToFavorite()
            else addToFavorite()

            postEventIsFavorite(!it)
        }



    }
    private fun addToFavorite(){
        val event : Event? = eventLiveData.value?.data
        event?.isFavorite = 1
        if(event!=null){
            Timber.d("$TAG ${event.isFavorite} ${event.awayTeam}")
            repository.insertEventToSqliteDb(event)
        }
    }

    private fun removeToFavorite(){
        val event : Event? = eventLiveData.value?.data
        event?.isFavorite = 0
        if(event!=null){
            repository.deleteEventInSqliteDb(event.idEvent?:-1)
        }
    }
}