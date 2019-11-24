package id.shobrun.footballleague.views.leagues.fragments

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import id.shobrun.footballleague.R
import id.shobrun.footballleague.models.League
import id.shobrun.footballleague.repositories.LeagueRepository
import id.shobrun.footballleague.views.iviews.IFootballLeaguesFragment

class FootballLeaguesViewModel(val leagueRepository:LeagueRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    private var _leagues = MutableLiveData<ArrayList<League>>()
    var leagues:LiveData<ArrayList<League>> = _leagues

    val empty:LiveData<Boolean> = Transformations.map(_leagues){
        it.isEmpty()
    }
    init {

    }
}
