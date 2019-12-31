package id.shobrun.footballleague.models.network

import id.shobrun.footballleague.models.NetworkResponseModel
import id.shobrun.footballleague.models.entity.Team

data class TeamsResponse(
    val teams: List<Team>
) : NetworkResponseModel