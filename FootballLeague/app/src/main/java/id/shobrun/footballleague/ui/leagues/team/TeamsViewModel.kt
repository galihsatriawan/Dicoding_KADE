package id.shobrun.footballleague.ui.leagues.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Team
import id.shobrun.footballleague.repository.TeamRepository
import id.shobrun.footballleague.utils.AbsentLiveData
import javax.inject.Inject

class TeamsViewModel @Inject constructor(private val repository: TeamRepository) : ViewModel() {
    private val leagueId = MutableLiveData<Int>()
    val teamsLiveData: LiveData<Resource<List<Team>>>

    init {
        teamsLiveData = leagueId.switchMap {
            leagueId.value?.let {
                repository.loadListTeamByLeagueId(it)
            } ?: AbsentLiveData.create()
        }
    }

    fun postLeagueId(id: Int) {
        leagueId.value = id
    }
}
