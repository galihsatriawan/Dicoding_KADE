package id.shobrun.footballleague.ui.leagues.team.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.Status
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.models.entity.Team
import id.shobrun.footballleague.repository.TeamRepository
import id.shobrun.footballleague.utils.AbsentLiveData
import javax.inject.Inject

class DetailTeamViewModel @Inject constructor(private val repository: TeamRepository) : ViewModel(){
    private val idTeam= MutableLiveData<Int>()
    val teamLiveData : LiveData<Resource<Team>>
    val teamInDb : LiveData<Team>
    val loading: LiveData<Boolean>
    private var _isFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val isFavorite: LiveData<Boolean>

    init {
        teamLiveData = idTeam.switchMap {
            idTeam.value?.let {
                repository.loadTeamDetailById(it)
            }?:AbsentLiveData.create()
        }
        teamInDb = idTeam.switchMap {
            idTeam.value?.let {
                repository.getTeamById(it)
            }?:AbsentLiveData.create()
        }
        loading = teamLiveData.switchMap {
            val mutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
            mutableLiveData.postValue(it.status == Status.LOADING)
            mutableLiveData
        }
        isFavorite = teamInDb.switchMap {
            it.isFavorite.let {
                postTeamIsFavorite(it == 1)
                _isFavorite
            }
        }
    }


    private fun postTeamIsFavorite(state: Boolean) {
        _isFavorite.postValue(state)
    }

    fun onClickedFavorite() {
        teamInDb.value?.isFavorite.let {
            val currentFavorite: Boolean = it == 1
            if (currentFavorite) {
                removeToFavorite()
            } else {
                addToFavorite()
            }
            postTeamIsFavorite(!currentFavorite)
        }
    }

    private fun addToFavorite() {
        val team: Team? = teamInDb.value
        team?.isFavorite = 1
        if (team!= null) {
            repository.updateTeamInDB(team)
        }
    }

    private fun removeToFavorite() {
        val team: Team? = teamInDb.value
        team?.isFavorite = 0
        if (team!= null) {
            repository.updateTeamInDB(team)
        }
    }
    fun postTeamId(idTeam: Int){
        this.idTeam.value = idTeam
    }
}