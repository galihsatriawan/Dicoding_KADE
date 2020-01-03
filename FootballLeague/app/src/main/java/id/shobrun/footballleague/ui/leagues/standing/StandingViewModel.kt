package id.shobrun.footballleague.ui.leagues.standing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.TeamRecord
import id.shobrun.footballleague.repository.TeamRecordRepository
import id.shobrun.footballleague.utils.AbsentLiveData
import timber.log.Timber
import javax.inject.Inject

class StandingViewModel @Inject constructor(repository: TeamRecordRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    companion object {
        val TAG = this.javaClass.name
    }

    private val idLeague = MutableLiveData<Int>()
    val standingLiveData: LiveData<Resource<List<TeamRecord>>>
    private val season = 1920

    init {
        standingLiveData = idLeague.switchMap {
            idLeague.value?.let {
                Timber.d("$TAG get team records")
                repository.getTeamRecordsByIdLeague(it, season)
            } ?: AbsentLiveData.create()
        }
    }

    fun postLeagueId(id: Int) {
        idLeague.value = id
    }
}
