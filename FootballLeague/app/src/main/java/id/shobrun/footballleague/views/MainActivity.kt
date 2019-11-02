package id.shobrun.footballleague.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import id.shobrun.footballleague.R
import id.shobrun.footballleague.views.fragments.FootballClubsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mClubFragment = FootballClubsFragment.getInstance()
        val mFragmentManager = supportFragmentManager as FragmentManager
        mFragmentManager.beginTransaction()
            .add(R.id.frame_container,mClubFragment,FootballClubsFragment::class.java.simpleName)
            .commit()

    }
}
