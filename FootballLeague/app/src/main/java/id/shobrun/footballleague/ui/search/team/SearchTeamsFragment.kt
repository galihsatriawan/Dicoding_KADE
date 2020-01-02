package id.shobrun.footballleague.ui.search.team

import android.app.SearchManager
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment
import id.shobrun.footballleague.databinding.FragmentSearchTeamsBinding
import id.shobrun.footballleague.ui.adapters.RecyclerEventsAdapter
import id.shobrun.footballleague.ui.adapters.RecyclerTeamsAdapter
import id.shobrun.footballleague.ui.events.detail.DetailEventActivity
import id.shobrun.footballleague.ui.leagues.team.detail.DetailTeamActivity
import org.jetbrains.anko.support.v4.intentFor

class SearchTeamsFragment : ViewModelFragment() {

    companion object {
        fun newInstance() = SearchTeamsFragment()
    }

    private val viewModel by viewModel<SearchTeamsViewModel>()
    lateinit var binding : FragmentSearchTeamsBinding
    private lateinit var teamAdapter: RecyclerTeamsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater,R.layout.fragment_search_teams,container)
        with(binding) {
            lifecycleOwner = this@SearchTeamsFragment
            vm = viewModel
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }
    private fun initRecycler(){
        teamAdapter = RecyclerTeamsAdapter(ArrayList())
        teamAdapter.setItemListener { team ->
            val detail = intentFor<DetailTeamActivity>(
                DetailTeamActivity.EXTRA_TEAM to team
            )
            startActivity(detail)
        }
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.rvTeams.addItemDecoration(dividerItemDecoration)
        binding.rvTeams.adapter = teamAdapter
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_events, menu)

        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /*
            Gunakan method ini ketika search selesai atau OK
             */
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.postFilter(query)
                return true
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
             */
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.postFilter(newText)
                return false
            }
        })
    }

}
