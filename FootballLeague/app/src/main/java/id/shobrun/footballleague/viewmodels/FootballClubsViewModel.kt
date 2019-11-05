package id.shobrun.footballleague.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.shobrun.footballleague.R
import id.shobrun.footballleague.models.Club
import id.shobrun.footballleague.views.iviews.IFootballClubsFragment

class FootballClubsViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var clubs = MutableLiveData<ArrayList<Club>>()
    lateinit var context: Context
    lateinit var view : IFootballClubsFragment

    fun setAppView(context: Context, view:IFootballClubsFragment){
        this.context = context
        this.view = view
    }
    internal fun setClubs(){
        view.showLoading(true)
        var clubs: ArrayList<Club> = ArrayList()
        val name = context.resources.getStringArray(R.array.club_name)
        val image = context.resources.obtainTypedArray(R.array.club_image)

        for(i in name.indices){
            clubs.add(Club(i,name[i],image.getResourceId(i,0),"-"))
        }
        image.recycle()
        this.clubs.postValue(clubs)
        view.showLoading(false)
        view.showContent(true)

    }


    internal fun getClubs() : LiveData<ArrayList<Club>> = clubs

}
