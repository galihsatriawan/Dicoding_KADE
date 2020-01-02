package id.shobrun.footballleague.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import id.shobrun.footballleague.models.entity.Team
import id.shobrun.footballleague.room.AppDatabase.Companion.ID_LEAGUE
import id.shobrun.footballleague.room.AppDatabase.Companion.ID_TEAM
import id.shobrun.footballleague.room.AppDatabase.Companion.TABLE_TEAM

@Dao
interface TeamDao {
    @Query("SELECT * FROM $TABLE_TEAM WHERE $ID_LEAGUE = :idLeague")
    fun getAllTeamsByLeagueId(idLeague : Int): LiveData<List<Team>>

    @Query("SELECT * FROM $TABLE_TEAM WHERE $ID_TEAM = :idTeam")
    fun getTeamById(idTeam: Int): LiveData<Team>

    @Query("SELECT * FROM $TABLE_TEAM WHERE tags LIKE :qry")
    fun getSearchTeams(qry: String) : LiveData<List<Team>>

    @Query("SELECT * FROM $TABLE_TEAM WHERE isFavorite = :favorite")
    fun getAllFavoriteTeam(favorite: Int) : LiveData<List<Team>>

    @Insert
    fun insert(team: Team)
    @Insert
    fun inserts(teams: List<Team>)
    @Update
    fun update(team: Team): Int

    @Query("DELETE FROM $TABLE_TEAM WHERE $ID_TEAM = :idTeam")
    fun delete(idTeam: Int)

    @Query("DELETE FROM $TABLE_TEAM")
    fun deletes()
}