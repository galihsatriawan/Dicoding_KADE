package id.shobrun.footballleague.repository.utils

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.models.entity.Team

interface ITeamLocalDB {
    fun insertTeamInDB(team: Team)
    fun getAllFavoriteTeams(): LiveData<List<Team>>
    fun getTeamById(id: Int): LiveData<Team>
    fun updateTeamInDB(team: Team): Int
}