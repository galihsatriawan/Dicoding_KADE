package id.shobrun.footballleague.room

import androidx.room.*
import id.shobrun.footballleague.models.League
import id.shobrun.footballleague.room.AppDatabase.Companion.ID_LEAGUE
import id.shobrun.footballleague.room.AppDatabase.Companion.TABLE_LEAGUE

@Dao
interface LeagueDao {
    @Query("SELECT * FROM $TABLE_LEAGUE")
    suspend fun getLeagues() : List<League>

    @Query("SELECT * FROM $TABLE_LEAGUE WHERE $ID_LEAGUE = :id")
    suspend fun getLeagueById(id : Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(league: League)

    @Update
    suspend fun updateLeague(league: League) : Int

    @Query("DELETE FROM  $TABLE_LEAGUE WHERE $ID_LEAGUE = :id")
    suspend fun deleteLeague(id : Int)

    @Query("DELETE FROM $TABLE_LEAGUE ")
    suspend fun deleteLeagues()
}