package id.shobrun.footballleague.ui.events.previous

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.repository.EventRepository
import id.shobrun.footballleague.utils.AbsentLiveData
import javax.inject.Inject

class PreviousEventViewModel @Inject constructor(repository: EventRepository) : ViewModel() {
    private val leagueIdLiveData: MutableLiveData<Int> = MutableLiveData()
    var previousEventLiveData: LiveData<Resource<List<Event>>>
    private val _leagueIdLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        previousEventLiveData = leagueIdLiveData.switchMap {
            leagueIdLiveData.value?.let { repository.getPreviousEvents(it) }
                ?: AbsentLiveData.create()
        }
    }

    fun postLeagueId(idLeague: Int) {
        this.leagueIdLiveData.postValue(idLeague)
    }

    fun post_LeagueId(idLeague: String) {
        this._leagueIdLiveData.value = idLeague
    }
}
