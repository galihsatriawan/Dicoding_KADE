package id.shobrun.footballleague.models.network

import id.shobrun.footballleague.models.NetworkResponseModel
import id.shobrun.footballleague.models.entity.League
import kotlinx.android.parcel.Parcelize


data class LeaguesResponse(
    val leagues : List<League>
) : NetworkResponseModel