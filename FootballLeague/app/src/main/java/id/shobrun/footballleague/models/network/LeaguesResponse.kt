package id.shobrun.footballleague.models.network

import id.shobrun.footballleague.models.NetworkResponseModel
import id.shobrun.footballleague.models.entity.League


data class LeaguesResponse(
    val leagues: List<League>
) : NetworkResponseModel