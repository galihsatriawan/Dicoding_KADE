package id.shobrun.footballleague.ui.events.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.repository.EventRepository
import id.shobrun.footballleague.testing.OpenForTesting
import id.shobrun.footballleague.utils.AbsentLiveData
import id.shobrun.footballleague.utils.wrapEspressoIdlingResource
import javax.inject.Inject

@OpenForTesting
class SearchEventsViewModel @Inject constructor(val repository: EventRepository) : ViewModel() {
    val eventLiveData: LiveData<Resource<List<Event>>>
    val filterLiveData: MutableLiveData<String> = MutableLiveData()


    init {
        eventLiveData =
            filterLiveData.switchMap {
                filterLiveData.value?.let { wrapEspressoIdlingResource {repository.getSearchEvent(it) }}
                    ?: AbsentLiveData.create()
        }
    }

    fun postFilter(qry: String) {
        wrapEspressoIdlingResource {
            this.filterLiveData.postValue(qry)
        }
    }
}