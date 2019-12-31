package id.shobrun.footballleague.ui.leagues.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment
import id.shobrun.footballleague.databinding.FragmentFootballLeagueBinding
import id.shobrun.footballleague.ui.adapters.RecyclerLeaguesAdapter
import id.shobrun.footballleague.ui.events.EventsActivity
import id.shobrun.footballleague.ui.events.EventsActivity.Companion.EXTRA_LEAGUE
import id.shobrun.footballleague.ui.leagues.detail.DetailLeagueActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.intentFor
import timber.log.Timber


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

        with(binding){
            lifecycleOwner = this@FootballLeaguesFragment
            vm = viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }
    private fun initRecycler(){
        leaguesAdapter = RecyclerLeaguesAdapter(ArrayList())
        leaguesAdapter.setDetailListener { league ->
            val detail = intentFor<DetailLeagueActivity>(
                DetailLeagueActivity.EXTRA_LEAGUE to league)
            Timber.d("$TAG id = ${league.idLeague}")
            startActivity(detail)
        }
        leaguesAdapter.setMatchListener { league ->
            val match = intentFor<EventsActivity>(
                EXTRA_LEAGUE to league
            )
            startActivity(match)
        }
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.rvLeagues.addItemDecoration(dividerItemDecoration)
        binding.rvLeagues.adapter = leaguesAdapter
    }

}
