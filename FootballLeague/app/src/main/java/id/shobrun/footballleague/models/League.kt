package id.shobrun.footballleague.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import id.shobrun.footballleague.room.AppDatabase.Companion.ID_LEAGUE
import id.shobrun.footballleague.room.AppDatabase.Companion.TABLE_LEAGUE
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_LEAGUE)
class League(

    @PrimaryKey @SerializedName("idLeague") @ColumnInfo(name = ID_LEAGUE) var id:Int,
    @SerializedName("strLeague")var name:String,
    @SerializedName("strBanner") var bannerUrl : String,
    var banner:Int,
    @SerializedName("strDescriptionEN")var description:String) :Parcelable