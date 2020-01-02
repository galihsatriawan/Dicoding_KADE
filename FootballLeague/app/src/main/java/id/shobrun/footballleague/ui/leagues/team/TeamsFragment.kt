package id.shobrun.footballleague.ui.leagues.team

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment
import id.shobrun.footballleague.databinding.FragmentTeamsBinding
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.ui.adapters.RecyclerTeamsAdapter
import id.shobrun.footballleague.ui.events.EventsActivity.Companion.EXTRA_LEAGUE
import id.shobrun.footballleague.ui.leagues.team.detail.DetailTeamActivity
import org.jetbrains.anko.support.v4.intentFor

class TeamsFragment : ViewModelFragment() {

    companion object {
        fun newInstance() = TeamsFragment()
    }

    private val viewModel by viewModel<TeamsViewModel>()
    private lateinit var binding : FragmentTeamsBinding
    private lateinit var teamsAdapter: RecyclerTeamsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater,R.layout.fragment_teams,container)
        with(binding){
            lifecycleOwner = this@TeamsFragment
            vm = viewModel
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        var league: League? = null
        if (requireArguments().getParcelable<League>(EXTRA_LEAGUE) != null) {
            league = requireArguments().getParcelable(EXTRA_LEAGUE)
        }

        viewModel.postLeagueId(league?.idLeague ?: -1)
    }

    private fun initRecycler() {
        teamsAdapter = RecyclerTeamsAdapter(ArrayList())
        teamsAdapter.setItemListener { team ->
            val detail = intentFor<DetailTeamActivity>(
                DetailTeamActivity.EXTRA_TEAM to team
            )
            startActivity(detail)
        }
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.rvTeams.addItemDecoration(dividerItemDecoration)
        binding.rvTeams.adapter = teamsAdapter
    }


}
