package id.shobrun.footballleague.ui.leagues.team.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.footballleague.models.entity.Team
import id.shobrun.footballleague.repository.TeamRepository
import id.shobrun.footballleague.utils.AbsentLiveData
import javax.inject.Inject

class FavoriteTeamsViewModel @Inject constructor(repository: TeamRepository): ViewModel() {
    // TODO: Implement the ViewModel
    private val idLeague = MutableLiveData<Int>()
    val teamsLiveData : LiveData<List<Team>>

    init {
        teamsLiveData = idLeague.switchMap {
            idLeague.value?.let {
                repository.getAllFavoriteTeams()
            }?:AbsentLiveData.create()
        }
    }
    fun postLeagueId(id: Int){
        idLeague.value = id
    }
}
