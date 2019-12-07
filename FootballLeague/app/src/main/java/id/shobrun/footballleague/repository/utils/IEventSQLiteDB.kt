package id.shobrun.footballleague.repository.utils

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.models.entity.Event

interface IEventSQLiteDB {
    /**
     * Local Database
     */
    fun insertEventToSqliteDb(event: Event)

    fun getAllFavoriteNextEventInSqliteDb(idLeague:Int) : LiveData<List<Event>>

    fun getAllFavoritePrevEventInSqliteDb(idLeague:Int) : LiveData<List<Event>>

    fun getEventByIdInSqliteDb(idEvent: Int) : LiveData<Boolean>

    fun deleteEventInSqliteDb(idEvent : Int)
}