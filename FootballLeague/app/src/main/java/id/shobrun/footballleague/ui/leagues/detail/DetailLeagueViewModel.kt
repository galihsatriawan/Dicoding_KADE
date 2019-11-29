package id.shobrun.footballleague.views.leagues

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.shobrun.footballleague.models.League
import id.shobrun.footballleague.models.Result
import id.shobrun.footballleague.repositories.LeagueRepository
import javax.inject.Inject


class DetailLeagueViewModel @Inject constructor(val repository: LeagueRepository) : ViewModel(){
    companion object{
        val TAG = this.javaClass.name
    }
    private var _league : MutableLiveData<Result<League>> = MutableLiveData()
    var league   = _league as LiveData<Result<League>>



}