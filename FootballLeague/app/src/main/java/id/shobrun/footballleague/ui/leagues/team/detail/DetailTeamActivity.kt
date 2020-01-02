package id.shobrun.footballleague.ui.leagues.team.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelActivity
import id.shobrun.footballleague.databinding.ActivityDetailTeamBinding
import id.shobrun.footballleague.extensions.simpleToolbarWithHome
import id.shobrun.footballleague.models.entity.Team
import kotlinx.android.synthetic.main.activity_event_detail.*
import org.jetbrains.anko.design.snackbar

class DetailTeamActivity : ViewModelActivity() {
    private var menuItem: Menu? = null

    private var count = 0
    companion object{
        const val EXTRA_TEAM = "extra_team"
    }
    private val viewModel by viewModel<DetailTeamViewModel>()
    private val binding by binding<ActivityDetailTeamBinding>(R.layout.activity_detail_team)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            lifecycleOwner = this@DetailTeamActivity
            vm = viewModel
        }
        var team: Team? = null
        if (intent.getParcelableExtra<Team>(EXTRA_TEAM) != null) {
            team= intent.getParcelableExtra<Team>(EXTRA_TEAM)
            viewModel.postTeamId(team.idTeam)

        }
        simpleToolbarWithHome(toolbar, team?.teamName?: "Detail Team")
        favoriteState()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_detail_league, menu)
        menuItem = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_to_favorite -> {
                viewModel.onClickedFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun favoriteState() {
        viewModel.isFavorite.observe(this, Observer {
            if (it != null) runOnUiThread {
                setFavorite(it)
                count++
            }
        })
    }

    private fun setFavorite(state: Boolean) {
        if (state) {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorite)
            if (count > 1) progressBar.snackbar(getString(R.string.add_team_success))
        } else {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorite)
            if (count > 1) progressBar.snackbar(getString(R.string.remove_team_success))
        }

    }
}
