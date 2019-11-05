package id.shobrun.footballleague.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.commitNow
import id.shobrun.footballleague.R
import id.shobrun.footballleague.views.fragments.FootballLeaguesFragment
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {
    companion object{
        const val _MAIN_FRAME = R.id.main_frame
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
        val mClubFragment = FootballLeaguesFragment.getInstance()
        supportFragmentManager.commitNow(allowStateLoss = true){  ->
            add(_MAIN_FRAME,mClubFragment,FootballLeaguesFragment::class.java.simpleName)
        }
    }
    class MainActivityUI : AnkoComponent<MainActivity>{
        override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
            return frameLayout {
                id = _MAIN_FRAME
                lparams(matchParent, matchParent)
            }
        }

    }

}
