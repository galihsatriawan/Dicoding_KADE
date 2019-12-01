package id.shobrun.footballleague.views.leagues.fragments

import android.util.Log
import androidx.lifecycle.*
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.repositories.LeagueRepository
import javax.inject.Inject

class FootballLeaguesViewModel @Inject constructor(private val repository: LeagueRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    companion object{
        val TAG = FootballLeaguesViewModel.javaClass.name
    }

    private var _leagues = MutableLiveData<List<League>>()
    var leagues:LiveData<List<League>> = _leagues
    init {
        val mLeagues = repository.getResourceLeagues()
        _leagues.value = mLeagues
        Log.d(TAG, "VM work"+mLeagues.size)
    }
    val empty:LiveData<Boolean> = Transformations.map(leagues){
        Log.d(TAG,"${it.isEmpty()}")
        it.isEmpty()
    }

}
