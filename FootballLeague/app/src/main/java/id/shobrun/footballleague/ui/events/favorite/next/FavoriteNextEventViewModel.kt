package id.shobrun.footballleague.ui.events.favorite.next

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.repository.EventRepository
import id.shobrun.footballleague.utils.AbsentLiveData
import javax.inject.Inject

class FavoriteNextEventViewModel @Inject constructor(val repository: EventRepository) :
    ViewModel() {
    // TODO: Implement the ViewModel

    private val leagueIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val nextEventLiveData: LiveData<List<Event>>

    init {
        nextEventLiveData = leagueIdLiveData.switchMap {
            leagueIdLiveData.value?.let { repository.getAllFavoriteNextEventInDb(it) }
                ?: AbsentLiveData.create()
        }
    }

    fun postLeagueId(idLeague: Int) {
        leagueIdLiveData.postValue(idLeague)
    }

}
