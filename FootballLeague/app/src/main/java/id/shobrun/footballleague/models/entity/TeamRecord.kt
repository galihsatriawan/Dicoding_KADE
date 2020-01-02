package id.shobrun.footballleague.models.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import id.shobrun.footballleague.room.AppDatabase.Companion.TABLE_TEAM_RECORD
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = TABLE_TEAM_RECORD)
data class TeamRecord(
    var idLeague : Int?,
    @SerializedName("name") var teamName: String?,
    @PrimaryKey
    @SerializedName("teamid")var idTeam : Int?,
    var played : Int,
    @SerializedName("goalsfor")var goalsFor : Int?,
    @SerializedName("goalsagainst")var goalsAgainst : Int?,
    @SerializedName("goalsdifference")var goalsDifference : Int?,
    var win : Int?,
    var draw : Int?,
    var loss : Int?,
    var total : Int?,
    var rank : Int?

):Parcelable