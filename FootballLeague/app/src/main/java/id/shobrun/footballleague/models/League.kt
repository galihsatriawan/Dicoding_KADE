package id.shobrun.footballleague.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(var id:Int, var name:String, var banner:Int, var description:String) :Parcelable