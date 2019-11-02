package id.shobrun.footballleague.views.iviews

import id.shobrun.footballleague.models.Club

interface IFootballClubsFragment {
    fun showListClubs(club:List<Club>)
    fun showLoading(visible:Boolean)
    fun showMessageLayout(visible: Boolean,text:String?)
    fun showContent(visible: Boolean)
}