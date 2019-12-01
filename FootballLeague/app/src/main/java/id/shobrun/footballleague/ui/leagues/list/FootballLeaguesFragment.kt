package id.shobrun.footballleague.views.leagues.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment
import id.shobrun.footballleague.databinding.FragmentFootballLeagueBinding
import id.shobrun.footballleague.ui.leagues.detail.DetailLeagueActivity
import id.shobrun.footballleague.views.adapters.RecyclerLeaguesAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.intentFor
import timber.log.Timber
import javax.inject.Inject


class FootballLeaguesFragment : ViewModelFragment(),AnkoLogger {

    private lateinit var leaguesAdapter: RecyclerLeaguesAdapter
    private lateinit var binding : FragmentFootballLeagueBinding
    companion object {
        val TAG = FootballLeaguesFragment.javaClass.name
        fun getInstance() = FootballLeaguesFragment()
    }

    private val viewModel by viewModel<FootballLeaguesViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater,R.layout.fragment_football_league,container)
        leaguesAdapter = RecyclerLeaguesAdapter(ArrayList())
        leaguesAdapter.setItemListener { league ->
            val detail = intentFor<DetailLeagueActivity>(
                DetailLeagueActivity.EXTRA_LEAGUE to league)
            Timber.d("$TAG id = ${league._id}")
            startActivity(detail)
        }
        binding.lifecycleOwner = this
        binding.vm = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.rvLeagues.adapter = leaguesAdapter
    }

}
