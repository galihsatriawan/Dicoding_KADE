package id.shobrun.footballleague.ui.events.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.repository.EventRepository
import id.shobrun.footballleague.utils.AbsentLiveData
import javax.inject.Inject

class SearchEventsViewModel @Inject constructor(repository: EventRepository) :ViewModel() {
    val eventLiveData : LiveData<Resource<List<Event>>>
    val filterLiveData : MutableLiveData<String> = MutableLiveData()

    init {
        eventLiveData = filterLiveData.switchMap {
            filterLiveData.value?.let { repository.getSearchEvent(it) }
                ?:AbsentLiveData.create()
        }
    }

    fun postFilter(qry : String){
        this.filterLiveData.postValue(qry)
    }
}