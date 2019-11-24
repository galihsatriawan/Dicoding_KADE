package id.shobrun.footballleague.views.leagues.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.shobrun.footballleague.R
import id.shobrun.footballleague.databinding.FragmentFootballLeagueBinding
import id.shobrun.footballleague.models.League
import id.shobrun.footballleague.views.leagues.DetailLeaguesActivity
import id.shobrun.footballleague.views.adapters.RecyclerLeaguesAdapter
import id.shobrun.footballleague.views.iviews.IFootballLeaguesFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.intentFor


class FootballLeaguesFragment : Fragment(), IFootballLeaguesFragment,AnkoLogger {
    private lateinit var leaguesAdapter: RecyclerLeaguesAdapter
    private lateinit var binding: FragmentFootballLeagueBinding
    companion object {
        fun getInstance() = FootballLeaguesFragment()
    }

    private val viewModel by lazy {
         ViewModelProviders.of(this).get(FootballLeaguesViewModel::class.java)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        leaguesAdapter = RecyclerLeaguesAdapter(ArrayList())
        leaguesAdapter.setItemListener { league ->
            val detail = intentFor<DetailLeaguesActivity>(
                DetailLeaguesActivity.EXTRA_LEAGUE to league)
            startActivity(detail)
        }
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_football_league,container,false)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.leagues.observe(this.viewLifecycleOwner, Observer { items ->
            if(items!=null){
                showListLeagues(items)
            }

        })
    }

    override fun showListLeagues(leagues: List<League>) {
        leaguesAdapter.setItems(leagues)
    }

}
