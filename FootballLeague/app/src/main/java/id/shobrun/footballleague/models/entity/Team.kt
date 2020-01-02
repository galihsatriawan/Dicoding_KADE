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
    @SerializedName("strTeam") var teamName: String,
    @SerializedName("strTeamShort") var teamShortName: String?,
    @SerializedName("strTeamBadge") var teamBadge: String?,
    @SerializedName("strAlternate")var alternateName : String?,
    @SerializedName("intFormedYear") var formedYear: Int?,
    @SerializedName("strSport") var categorySport: String?,
    @SerializedName("strLeague") var leagueName: String?,
    @SerializedName("strManager") var managerName: String?,
    @SerializedName("strStadium") var stadiumName: String?,
    @SerializedName("strStadiumThumb") var imgStadium: String?,
    @SerializedName("strStadiumLocation") var stadiumLocation: String?,
    @SerializedName("intStadiumCapacity") var stadiumCapacity: Int?,
    @SerializedName("strDescriptionEN") var descriptionEN: String?,
    @SerializedName("strCountry") var teamCountry: String?,
    @SerializedName("strTeamJersey") var teamJersey: String?,
    var isFavorite: Int=0,
    var tags : String?
) : Parcelable