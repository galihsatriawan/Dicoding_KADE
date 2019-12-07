package id.shobrun.footballleague.database

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import id.shobrun.footballleague.database.Favorite.Companion.AWAY_GOAL_DETAILS
import id.shobrun.footballleague.database.Favorite.Companion.AWAY_ID
import id.shobrun.footballleague.database.Favorite.Companion.AWAY_RED_CARDS
import id.shobrun.footballleague.database.Favorite.Companion.AWAY_SCORE
import id.shobrun.footballleague.database.Favorite.Companion.AWAY_TEAM
import id.shobrun.footballleague.database.Favorite.Companion.AWAY_YELLOW_CARDS
import id.shobrun.footballleague.database.Favorite.Companion.EVENT_DATE
import id.shobrun.footballleague.database.Favorite.Companion.EVENT_ID
import id.shobrun.footballleague.database.Favorite.Companion.EVENT_NAME
import id.shobrun.footballleague.database.Favorite.Companion.EVENT_TIME
import id.shobrun.footballleague.database.Favorite.Companion.HOME_GOAL_DETAILS
import id.shobrun.footballleague.database.Favorite.Companion.HOME_ID
import id.shobrun.footballleague.database.Favorite.Companion.HOME_RED_CARDS
import id.shobrun.footballleague.database.Favorite.Companion.HOME_SCORE
import id.shobrun.footballleague.database.Favorite.Companion.HOME_TEAM
import id.shobrun.footballleague.database.Favorite.Companion.HOME_YELLOW_CARDS
import id.shobrun.footballleague.database.Favorite.Companion.ID_
import id.shobrun.footballleague.database.Favorite.Companion.IS_FAVORITE
import id.shobrun.footballleague.database.Favorite.Companion.LEAGUE_ID
import id.shobrun.footballleague.database.Favorite.Companion.LEAGUE_NAME
import id.shobrun.footballleague.database.Favorite.Companion.SPORT_CATEGORY
import id.shobrun.footballleague.database.Favorite.Companion.TABLE_FAVORITE
import id.shobrun.footballleague.database.Favorite.Companion.TAGS
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.room.AppDatabase
import id.shobrun.footballleague.room.AppDatabase.Companion.TAG_NEXT_MATCH

import org.jetbrains.anko.db.*
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class MyDatabaseOpenHelper @Inject constructor (val application: Application) : ManagedSQLiteOpenHelper(application, "FavoriteEvent.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null
        private val TAG = MyDatabaseOpenHelper::class.java.name
        @Synchronized
        fun getInstance(application: Application): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(application)
            }
            return instance!!
        }
    }
    fun removeFromFavorite(id : Int){
        try {
            getInstance(application).use {
                delete(TABLE_FAVORITE, "(EVENT_ID = {id})",
                    "id" to id)
            }
        } catch (e: Exception){
            Timber.d("$TAG ${e.localizedMessage}")
        }
    }
    fun getFavorite(id : Int) : Boolean{
//        var favorite  = Event(-1,-1,-1,"",null,
//            null, null,null,null,null,
//            null,null, null, null,
//            null,null,null, "", -1, -1,
//            0,null)
        var isFavorite = false
        try {
            getInstance(application).use {
                val res = select(TABLE_FAVORITE)
                    .whereArgs( "(EVENT_ID = {id})",
                        "id" to id)
                val event = res.parseList(classParser<Event>())
                if(!event.isEmpty()) isFavorite = true
            }
        } catch (e: Exception){
            Timber.d("$TAG ${e.localizedMessage}")
        }
        Timber.d("$TAG isFavorite ${isFavorite}")
        return isFavorite
    }
    fun getNextFavorite(id : Int) : List<Event>{
        var events : List<Event> = ArrayList()
        try {
            getInstance(application).use {
                val res = select(TABLE_FAVORITE)
                    .whereArgs( "(LEAGUE_ID = {id}) AND (TAGS ={tag})",
                        "id" to id,
                                "tag" to TAG_NEXT_MATCH
                        )
                events = res.parseList(classParser())
            }
        } catch (e: Exception){
            Timber.d("$TAG ${e.localizedMessage}")
        }
        return events
    }
    fun getPrevFavorite(id : Int) : List<Event>{
        var events : List<Event> = ArrayList()
        try {
            getInstance(application).use {
                val res = select(TABLE_FAVORITE)
                    .whereArgs( "(LEAGUE_ID = {id}) AND (TAGS ={tag})",
                        "id" to id,
                        "tag" to AppDatabase.TAG_PAST_MATCH
                    )
                events = res.parseList(classParser())
            }
        } catch (e: Exception){
            Timber.d("$TAG ${e.localizedMessage}")
        }
        return events
    }
    fun addToFavorite(event: Event){
        try {
            Timber.d("$TAG Date Event ${event.dateEvent}")
            getInstance(application).use {
                insert(TABLE_FAVORITE,
                    EVENT_ID to event.idEvent,
                    LEAGUE_ID to event.idLeague,
                    LEAGUE_NAME to event.leagueName,
                    SPORT_CATEGORY to event.sportCategory,
                    HOME_TEAM to event.homeTeam,
                    HOME_SCORE to event.homeScore,
                    HOME_GOAL_DETAILS to event.homeGoalDetails,
                    HOME_RED_CARDS to event.homeRedCards,
                    HOME_YELLOW_CARDS to event.homeYellowCards,
                    AWAY_TEAM to event.awayTeam,
                    AWAY_SCORE to event.awayScore,
                    AWAY_GOAL_DETAILS to event.awayGoalDetails,
                    AWAY_RED_CARDS to event.awayRedCards,
                    AWAY_YELLOW_CARDS to event.awayYellowCards,
                    EVENT_DATE to event.dateEvent,
                    EVENT_NAME to event.eventName,
                    EVENT_TIME to event.timeEvent,
                    HOME_ID to event.idHomeTeam,
                    AWAY_ID to event.idAwayTeam,
                    TAGS to event.tags)
            }

        } catch (e: SQLiteConstraintException){
            Timber.d("$TAG ${e.localizedMessage}")
        }
    }
    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables

        db.createTable(TABLE_FAVORITE, true,

            ID_ to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            EVENT_ID to INTEGER + UNIQUE,
            LEAGUE_ID to INTEGER ,
            LEAGUE_NAME to TEXT,
            SPORT_CATEGORY to TEXT,
            HOME_TEAM to TEXT,
            HOME_SCORE to INTEGER,

            HOME_GOAL_DETAILS to TEXT,
            HOME_RED_CARDS to TEXT,
            HOME_YELLOW_CARDS to TEXT,
            AWAY_TEAM to TEXT,
            AWAY_SCORE to INTEGER,
            AWAY_GOAL_DETAILS to TEXT,
            AWAY_RED_CARDS to TEXT,
            AWAY_YELLOW_CARDS to TEXT,

            EVENT_NAME to TEXT,
            EVENT_DATE to TEXT,
            EVENT_TIME to TEXT,

            HOME_ID to INTEGER,
            AWAY_ID to INTEGER,
            IS_FAVORITE to TEXT,
            TAGS to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable("TABLE_FAVORITE", true)
    }

}
