package id.shobrun.footballleague.ui.events.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.Status
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.repository.EventRepository
import id.shobrun.footballleague.utils.AbsentLiveData
import javax.inject.Inject

class DetailEventViewModel @Inject constructor(val repository : EventRepository): ViewModel(){
    private val eventIdLiveData : MutableLiveData<Int> = MutableLiveData()
    val eventLiveData : LiveData<Resource<Event>>
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
    }
    fun postEventId(idEvent : Int){
        eventIdLiveData.postValue(idEvent)
    }
}