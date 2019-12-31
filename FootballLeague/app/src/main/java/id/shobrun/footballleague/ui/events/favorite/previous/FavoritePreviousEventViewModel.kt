package id.shobrun.footballleague.ui.events.favorite.previous

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.repository.EventRepository
import id.shobrun.footballleague.utils.AbsentLiveData
import javax.inject.Inject

class FavoritePreviousEventViewModel @Inject constructor(repository: EventRepository) :
    ViewModel() {
    private val leagueIdLiveData: MutableLiveData<Int> = MutableLiveData()
    var previousEventLiveData: LiveData<List<Event>>

    init {
        previousEventLiveData = leagueIdLiveData.switchMap {
            leagueIdLiveData.value?.let { repository.getAllFavoritePrevEventInDb(it) }
                ?: AbsentLiveData.create()
        }

    }

    fun postLeagueId(idLeague: Int) {
        this.leagueIdLiveData.postValue(idLeague)
    }
}
