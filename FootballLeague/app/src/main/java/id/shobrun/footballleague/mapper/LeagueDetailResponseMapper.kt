package id.shobrun.footballleague.mapper

import id.shobrun.footballleague.models.NetworkResponseModel
import id.shobrun.footballleague.models.entity.League

class LeagueDetailResponseMapper : NetworkResponseMapper<League>{
    override fun onLastPage(response: League): Boolean {
        return true
    }

}