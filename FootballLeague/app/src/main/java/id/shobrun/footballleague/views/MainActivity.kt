package id.shobrun.footballleague.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import id.shobrun.footballleague.R
import id.shobrun.footballleague.views.fragments.FootballClubsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mClubFragment = FootballClubsFragment.getInstance()
        supportFragmentManager.commitNow(allowStateLoss = true){  ->
            add(R.id.frame_container,mClubFragment,FootballClubsFragment::class.java.simpleName)
        }

    }

}
