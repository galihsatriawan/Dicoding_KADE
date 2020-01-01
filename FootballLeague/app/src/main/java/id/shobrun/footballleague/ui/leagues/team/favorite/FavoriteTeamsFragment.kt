package id.shobrun.footballleague.ui.leagues.team.favorite

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment

class FavoriteTeamsFragment : ViewModelFragment() {

    companion object {
        fun newInstance() = FavoriteTeamsFragment()
    }

    private val viewModel by viewModel<FavoriteTeamsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_teams_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
