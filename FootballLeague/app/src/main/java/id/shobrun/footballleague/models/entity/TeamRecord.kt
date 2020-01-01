package id.shobrun.footballleague.models.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class TeamRecord(
    var idTeamRecord : Int,
    var idLeague : Int?,
    @SerializedName("name") var teamName: String?,
    @SerializedName("teamid")var idTeam : Int?,
    var played : Int,
    @SerializedName("goalsfor")var goalsFor : Int?,
    @SerializedName("goalsagainst")var goalsAgainst : Int?,
    @SerializedName("goalsdifference")var goalsDifference : Int?,
    var win : Int?,
    var draw : Int?,
    var loss : Int?,
    var total : Int?

):Parcelable