package id.shobrun.footballleague.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commitNow
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import id.shobrun.footballleague.R
import id.shobrun.footballleague.views.leagues.fragments.FootballLeaguesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val mClubFragment = FootballLeaguesFragment.getInstance()
        supportFragmentManager.commitNow(allowStateLoss = true){
            add(R.id.frame_container,mClubFragment,FootballLeaguesFragment::class.java.simpleName)
        }
    }
}
