package id.shobrun.footballleague.views.iviews

import id.shobrun.footballleague.models.League

interface IFootballLeaguesFragment {
    fun showListLeagues(league:List<League>)
}