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
import id.shobrun.footballleague.databinding.FragmentFootballLeagueBinding
import id.shobrun.footballleague.ui.leagues.detail.DetailLeagueActivity
import id.shobrun.footballleague.views.adapters.RecyclerLeaguesAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.intentFor
import javax.inject.Inject


class FootballLeaguesFragment : DaggerFragment(),AnkoLogger {

    private lateinit var leaguesAdapter: RecyclerLeaguesAdapter
    private lateinit var binding: FragmentFootballLeagueBinding
    companion object {
        fun getInstance() = FootballLeaguesFragment()
    }

    @Inject
    lateinit var viewModelProvider : ViewModelProvider.Factory
    private val viewModel by lazy {
         ViewModelProviders.of(this,viewModelProvider).get(FootballLeaguesViewModel::class.java)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        leaguesAdapter = RecyclerLeaguesAdapter(ArrayList())
        leaguesAdapter.setItemListener { league ->
            val detail = intentFor<DetailLeagueActivity>(
                DetailLeagueActivity.EXTRA_LEAGUE to league)
            startActivity(detail)
        }

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_football_league,container,false)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.rvLeagues.adapter = leaguesAdapter
    }

}
