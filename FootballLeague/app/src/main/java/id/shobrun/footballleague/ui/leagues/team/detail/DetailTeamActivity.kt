package id.shobrun.footballleague.ui.leagues.team.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelActivity

class DetailTeamActivity : ViewModelActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
    }
}
