package id.shobrun.footballleague.ui.search.team

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment

class SearchTeamsFragment : ViewModelFragment() {

    companion object {
        fun newInstance() = SearchTeamsFragment()
    }

    private val viewModel by viewModel<SearchTeamsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_teams, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
