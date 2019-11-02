package id.shobrun.footballleague.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Club(var id:Int,var name:String?, var image:Int) :Parcelable