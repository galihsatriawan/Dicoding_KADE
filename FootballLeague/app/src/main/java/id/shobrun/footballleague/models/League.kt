package id.shobrun.footballleague.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    @SerializedName("idLeague") var id:Int,
    @SerializedName("strLeague")var name:String,
    @SerializedName("strBanner") var bannerUrl : String,
    var banner:Int,
    @SerializedName("strDescriptionEN")var description:String) :Parcelable