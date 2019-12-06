package id.shobrun.footballleague.repository.utils

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.models.entity.Event

interface IEventLocalDB {
    /**
     * Local Database
     */
    fun insertEventToDb(event: Event)

    fun getAllFavoriteNextEventInDb(idLeague:Int) : LiveData<List<Event>>

    fun getAllFavoritePrevEventInDb(idLeague:Int) : LiveData<List<Event>>

    fun getEventByIdInDb(idEvent: Int) : LiveData<Event>

    fun updateEventInDb(event : Event) : Int
}