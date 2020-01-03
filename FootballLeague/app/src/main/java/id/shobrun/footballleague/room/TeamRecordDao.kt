package id.shobrun.footballleague.room

import androidx.lifecycle.LiveData
import androidx.room.*
import id.shobrun.footballleague.models.entity.TeamRecord
import id.shobrun.footballleague.room.AppDatabase.Companion.ID_LEAGUE
import id.shobrun.footballleague.room.AppDatabase.Companion.TABLE_TEAM_RECORD

@Dao
interface TeamRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(teamRecord: List<TeamRecord>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(teamRecord: TeamRecord)

    @Query("SELECT * FROM $TABLE_TEAM_RECORD WHERE $ID_LEAGUE = :id")
    fun getStandingByIdLeague(id: Int): LiveData<List<TeamRecord>>

    @Update
    fun update(teamRecord: TeamRecord): Int

    @Query("DELETE FROM $TABLE_TEAM_RECORD WHERE $ID_LEAGUE = :id")
    fun deleteByIdLeague(id: Int)
}