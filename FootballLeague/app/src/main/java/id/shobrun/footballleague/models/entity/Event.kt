package id.shobrun.footballleague.models.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import id.shobrun.footballleague.room.AppDatabase.Companion.TABLE_EVENT
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_EVENT)
data class Event(
    @PrimaryKey val idEvent : Int,
    @field:SerializedName("strEvent") var eventName : String,
    @field:SerializedName("strSport") var sportCategory : String?,
    val idLeague : Int,
    @field:SerializedName("strLeague") var leagueName : String?,
    @field:SerializedName("strHomeTeam") var homeTeam : String?,
    @field:SerializedName("strAwayTeam") var awayTeam : String?,
    @field:SerializedName("intHomeScore") var homeScore : Int?,
    @field:SerializedName("intAwayScore") var awayScore : Int?,
    @field:SerializedName("strHomeGoalDetails") var homeGoalDetails : String?,
    @field:SerializedName("strHomeRedCards") var homeRedCards : String?,
    @field:SerializedName("strHomeYellowCards") var homeYellowCards : String?,
    @field:SerializedName("strAwayGoalDetails") var awayGoalDetails : String?,
    @field:SerializedName("strAwayRedCards") var awayRedCards : String?,
    @field:SerializedName("strAwayYellowCards") var awayYellowCards : String?,
    var dateEvent : String?,
    @field:SerializedName("strTime") val timeEvent : String?,
    var idHomeTeam : Int,
    var idAwayTeam : Int,
    var isFavorite : Int = 0,
    var tags : String?
):Parcelable