package id.shobrun.footballleague.models.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import id.shobrun.footballleague.room.AppDatabase.Companion.TABLE_LEAGUE
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_LEAGUE)
data class League(

    @PrimaryKey var idLeague:Int,
    @field:SerializedName("strLeague")var name:String,
    @field:SerializedName("strBanner") var bannerUrl : String,
    @field:SerializedName("strLogo") var logoUrl : String,
    var banner:Int,
    @field:SerializedName("strDescriptionEN")var description:String) : Parcelable