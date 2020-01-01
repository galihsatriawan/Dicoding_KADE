package id.shobrun.footballleague.models.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import id.shobrun.footballleague.room.AppDatabase.Companion.TABLE_TEAM
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_TEAM)
data class Team(
    @PrimaryKey val idTeam: Int,
    val idLeague : Int?,
    @field:SerializedName("strTeam") var teamName: String,
    @field:SerializedName("strTeamShort") var teamShortName: String?,
    @field:SerializedName("strTeamBadge") var teamBadge: String?,
    var tags : String?
) : Parcelable