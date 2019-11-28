package id.shobrun.footballleague.ui.adapters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.shobrun.footballleague.models.League

class LeagueViewModel: ViewModel(){
    var _img_league  : MutableLiveData<Int> = MutableLiveData()
    var img_league : LiveData<Int> = _img_league

    var _tv_league_name : MutableLiveData<String> = MutableLiveData()
    var tv_league_name :LiveData<String> = _tv_league_name

    fun bind(league : League){
        _img_league.value = league.banner
        _tv_league_name.value = league.name
    }

}