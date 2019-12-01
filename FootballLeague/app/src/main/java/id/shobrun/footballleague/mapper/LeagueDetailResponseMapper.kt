package id.shobrun.footballleague.mapper

import id.shobrun.footballleague.models.NetworkResponseModel
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.models.network.LeaguesResponse

class LeagueDetailResponseMapper : NetworkResponseMapper<LeaguesResponse>{
    override fun onLastPage(response: LeaguesResponse): Boolean {
        return true
    }

}