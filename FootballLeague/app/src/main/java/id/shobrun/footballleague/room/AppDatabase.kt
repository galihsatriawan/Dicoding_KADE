package id.shobrun.footballleague.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.shobrun.footballleague.models.League

@Database(entities = [League::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    companion object{
        const val DB_FOOTBALL = "football-db"

        const val TABLE_LEAGUE = "league_table"
        const val ID_LEAGUE = "league_id"

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database.
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DB_FOOTBALL)
                .build()
        }
    }
    abstract fun leagueDao() : LeagueDao


}