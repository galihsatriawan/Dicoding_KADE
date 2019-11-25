package id.shobrun.footballleague.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import id.shobrun.footballleague.R
import id.shobrun.footballleague.views.leagues.fragments.FootballLeaguesFragment
import id.shobrun.footballleague.views.leagues.fragments.FootballLeaguesViewModel
import javax.inject.Inject

class LeagueActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    val viewModel:FootballLeaguesViewModel by lazy{
        ViewModelProviders.of(this,viewModelProvider).get(FootballLeaguesViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.empty
        val mClubFragment = FootballLeaguesFragment.getInstance()
        supportFragmentManager.commitNow(allowStateLoss = true){
            add(R.id.frame_container,mClubFragment,FootballLeaguesFragment::class.java.simpleName)
        }
    }

}
