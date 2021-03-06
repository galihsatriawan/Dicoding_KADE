package id.shobrun.footballleague.ui.events.next

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.repository.EventRepository
import id.shobrun.footballleague.utils.AbsentLiveData
import javax.inject.Inject

class NextEventViewModel @Inject constructor(val repository: EventRepository) : ViewModel() {
    // TODO: Implement the ViewModel

    private val leagueIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val nextEventLiveData: LiveData<Resource<List<Event>>>

    init {
        nextEventLiveData = leagueIdLiveData.switchMap {
            leagueIdLiveData.value?.let { repository.getNextEvents(it) }
                ?: AbsentLiveData.create()
        }
    }

    fun postLeagueId(idLeague: Int) {
        leagueIdLiveData.postValue(idLeague)
    }

}
