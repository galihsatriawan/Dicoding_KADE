package id.shobrun.footballleague.ui.leagues.detail

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import dagger.android.support.DaggerAppCompatActivity
import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelActivity
import id.shobrun.footballleague.databinding.ActivityDetailLeagueBinding
import id.shobrun.footballleague.extensions.observeLiveData
import id.shobrun.footballleague.extensions.simpleToolbarWithHome
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.views.leagues.DetailLeagueViewModel
import kotlinx.android.synthetic.main.activity_detail_league.*
import timber.log.Timber
import javax.inject.Inject

class DetailLeagueActivity : ViewModelActivity() {

    private val vm by viewModel<DetailLeagueViewModel>()
    private val binding by binding<ActivityDetailLeagueBinding>(R.layout.activity_detail_league)
    companion object{
        const val EXTRA_LEAGUE = "extra_league"
        val TAG = DetailLeagueActivity.javaClass.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var league: League? = null
        if (intent.getParcelableExtra<League>(EXTRA_LEAGUE) != null) {
            league = intent.getParcelableExtra(EXTRA_LEAGUE)
            Timber.d("$TAG id: ${league?._id}")
            vm.postLeagueId(league._id)
        }

        with(binding){
            lifecycleOwner = this@DetailLeagueActivity
            viewModel = vm
        }

        simpleToolbarWithHome(toolbar,(league?.name ?: "Detail League" ))

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
