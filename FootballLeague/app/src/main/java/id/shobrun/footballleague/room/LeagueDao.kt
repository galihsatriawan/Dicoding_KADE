package id.shobrun.footballleague.room

import androidx.lifecycle.LiveData
import androidx.room.*
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.room.AppDatabase.Companion.ID_LEAGUE
import id.shobrun.footballleague.room.AppDatabase.Companion.TABLE_LEAGUE

@Dao
interface LeagueDao {
    @Query("SELECT * FROM $TABLE_LEAGUE")
    fun getLeagues() : LiveData<List<League>>

    @Query("SELECT * FROM $TABLE_LEAGUE WHERE $ID_LEAGUE = :id")
    fun getLeagueById(id : Int) : LiveData<League>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(league: League)

    @Update
    fun updateLeague(league: League) : Int

    @Query("DELETE FROM  $TABLE_LEAGUE WHERE $ID_LEAGUE = :id")
    fun deleteLeague(id : Int)

    @Query("DELETE FROM $TABLE_LEAGUE ")
    fun deleteLeagues()
}