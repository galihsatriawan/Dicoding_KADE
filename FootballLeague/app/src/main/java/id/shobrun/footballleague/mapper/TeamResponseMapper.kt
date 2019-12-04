package id.shobrun.footballleague.mapper

import id.shobrun.footballleague.models.network.TeamsResponse

class TeamResponseMapper : NetworkResponseMapper<TeamsResponse> {
    override fun onLastPage(response: TeamsResponse): Boolean {
        return true
    }

}