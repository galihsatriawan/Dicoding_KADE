package id.shobrun.footballleague.ui.leagues.detail

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import id.shobrun.footballleague.R
import id.shobrun.footballleague.databinding.ActivityDetailLeagueBinding
import id.shobrun.footballleague.models.League
import id.shobrun.footballleague.views.leagues.DetailLeagueViewModel
import kotlinx.android.synthetic.main.activity_detail_league.*
import javax.inject.Inject

class DetailLeagueActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel: DetailLeagueViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(DetailLeagueViewModel::class.java)
    }
    companion object{
        const val EXTRA_LEAGUE = "extra_league"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityDetailLeagueBinding>(
            this,
            R.layout.activity_detail_league
        )

        setSupportActionBar(toolbar)

        var league: League? = null
        if (intent.getParcelableExtra<League>(EXTRA_LEAGUE) != null) {
            league = intent.getParcelableExtra(EXTRA_LEAGUE)
            viewModel.setLeague(league)
        }


        binding.lifecycleOwner = this
        binding.vm = viewModel

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
