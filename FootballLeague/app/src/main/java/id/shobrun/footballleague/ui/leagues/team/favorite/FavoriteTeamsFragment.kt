package id.shobrun.footballleague.ui.leagues.team.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment
import id.shobrun.footballleague.databinding.FavoriteTeamsFragmentBinding
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.ui.adapters.RecyclerTeamsAdapter
import id.shobrun.footballleague.ui.events.EventsActivity
import id.shobrun.footballleague.ui.leagues.team.detail.DetailTeamActivity
import org.jetbrains.anko.support.v4.intentFor

class FavoriteTeamsFragment : ViewModelFragment() {

    companion object {
        fun newInstance() = FavoriteTeamsFragment()
    }

    private val viewModel by viewModel<FavoriteTeamsViewModel>()
    private lateinit var binding: FavoriteTeamsFragmentBinding
    private lateinit var teamsAdapter: RecyclerTeamsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.favorite_teams_fragment, container)
        with(binding) {
            lifecycleOwner = this@FavoriteTeamsFragment
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        var league: League? = null
        if (requireArguments().getParcelable<League>(EventsActivity.EXTRA_LEAGUE) != null) {
            league = requireArguments().getParcelable(EventsActivity.EXTRA_LEAGUE)
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
        binding.recyclerTeams.addItemDecoration(dividerItemDecoration)
        binding.recyclerTeams.adapter = teamsAdapter
    }

}
