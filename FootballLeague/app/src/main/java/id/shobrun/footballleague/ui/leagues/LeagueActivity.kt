package id.shobrun.footballleague.views

import android.os.Bundle
import androidx.fragment.app.commitNow
import dagger.android.support.DaggerAppCompatActivity
import id.shobrun.footballleague.R
import id.shobrun.footballleague.views.leagues.fragments.FootballLeaguesFragment


class LeagueActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leagues)

        val mClubFragment = FootballLeaguesFragment.getInstance()
        supportFragmentManager.commitNow(allowStateLoss = true){
            add(R.id.frame_container,mClubFragment,FootballLeaguesFragment::class.java.simpleName)
        }
    }

}
