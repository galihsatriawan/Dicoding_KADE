package id.shobrun.footballleague.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import id.shobrun.footballleague.R
import id.shobrun.footballleague.views.leagues.fragments.FootballLeaguesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mClubFragment = FootballLeaguesFragment.getInstance()
        supportFragmentManager.commitNow(allowStateLoss = true){
            add(R.id.frame_container,mClubFragment,FootballLeaguesFragment::class.java.simpleName)
        }
    }

}
