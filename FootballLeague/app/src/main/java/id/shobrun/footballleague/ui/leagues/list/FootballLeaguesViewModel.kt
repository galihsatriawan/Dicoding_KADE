package id.shobrun.footballleague.ui.leagues.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.repository.LeagueRepository
import timber.log.Timber
import javax.inject.Inject

class FootballLeaguesViewModel @Inject constructor(repository: LeagueRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    companion object {
        val TAG = FootballLeaguesViewModel.javaClass.name
    }

    private var _leagues = MutableLiveData<List<League>>()
    var leagues: LiveData<List<League>> = _leagues

    init {
        val mLeagues = repository.getResourceLeagues()
        _leagues.value = mLeagues
        Timber.d(TAG + " VM work" + mLeagues.size)
    }

}
