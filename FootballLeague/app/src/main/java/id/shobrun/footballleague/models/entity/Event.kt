package id.shobrun.footballleague.models.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import id.shobrun.footballleague.room.AppDatabase.Companion.TABLE_EVENT
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_EVENT)
data class Event(
    @PrimaryKey val idEvent : Int,
    @field:SerializedName("strEvent") val eventName : String,
    @field:SerializedName("strSport") val sportCategory : String,
    val idLeague : Int,
    @field:SerializedName("strLeague") val leagueName : String,
    @field:SerializedName("strHomeTeam") val homeTeam : String,
    @field:SerializedName("strAwayTeam") val awayTeam : String,
    @field:SerializedName("intHomeScore") val homeScore : Int,
    @field:SerializedName("intAwayScore") val awayScore : Int,
    @field:SerializedName("strHomeGoalDetails") val homeGoalDetails : String,
    @field:SerializedName("strHomeRedCards") val homeRedCards : String,
    @field:SerializedName("strHomeYellowCards") val homeYellowCards : String,
    @field:SerializedName("strAwayGoalDetails") val awayGoalDetails : String,
    @field:SerializedName("strAwayRedCards") val awayRedCards : String,
    @field:SerializedName("strAwayYellowCards") val awayYellowCards : String,
    val dateEvent : String,
    @field:SerializedName("strTime") val timeEvent : String,
    val idHomeTeam : Int,
    val idAwayTeam : Int,
    val tags : String
):Parcelable