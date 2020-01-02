package id.shobrun.footballleague.room

import androidx.lifecycle.LiveData
import androidx.room.*
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.room.AppDatabase.Companion.ID_EVENT
import id.shobrun.footballleague.room.AppDatabase.Companion.ID_LEAGUE
import id.shobrun.footballleague.room.AppDatabase.Companion.TABLE_EVENT
import id.shobrun.footballleague.room.AppDatabase.Companion.TAG_NEXT_MATCH
import id.shobrun.footballleague.room.AppDatabase.Companion.TAG_PAST_MATCH

@Dao
interface EventDao {
    @Query("SELECT * FROM $TABLE_EVENT WHERE tags LIKE '%$TAG_NEXT_MATCH%' AND $ID_LEAGUE = :idLeague")
    fun getNextEvents(idLeague: Int): LiveData<List<Event>>

    @Query("SELECT * FROM $TABLE_EVENT WHERE tags LIKE '%$TAG_PAST_MATCH%' AND $ID_LEAGUE = :idLeague")
    fun getPastEvents(idLeague: Int): LiveData<List<Event>>

    @Query("SELECT * FROM $TABLE_EVENT WHERE tags LIKE '%$TAG_NEXT_MATCH%' AND $ID_LEAGUE = :idLeague AND isFavorite=:favorite")
    fun getFavoriteNextEvents(idLeague: Int, favorite: Int): LiveData<List<Event>>

    @Query("SELECT * FROM $TABLE_EVENT WHERE tags LIKE '%$TAG_PAST_MATCH%' AND $ID_LEAGUE = :idLeague AND isFavorite=:favorite")
    fun getFavoritePastEvents(idLeague: Int, favorite: Int): LiveData<List<Event>>

    @Query("SELECT * FROM $TABLE_EVENT WHERE tags LIKE :qry ")
    fun getSearchEvent(qry: String): LiveData<List<Event>>


    @Query("SELECT * FROM $TABLE_EVENT WHERE $ID_EVENT = :idEvent")
    fun getEventById(idEvent: Int): LiveData<Event>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(event: List<Event>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: Event)

    @Update
    fun update(event: Event): Int

    @Query("DELETE FROM $TABLE_EVENT WHERE idEvent = :idEvent")
    fun deleteEvent(idEvent: Int)

    @Query("DELETE FROM $TABLE_EVENT")
    fun deleteEvents()
}