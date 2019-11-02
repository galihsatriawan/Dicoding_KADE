package id.shobrun.footballleague.views.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.shobrun.footballleague.R
import id.shobrun.footballleague.viewmodels.FootballTeamsViewModel

class FootballTeamsFragment : Fragment() {

    companion object {
        private var INSTANCE : FootballTeamsFragment? = null
        var instance = { _:Unit ->
            INSTANCE =FootballTeamsFragment()
            INSTANCE
        }
        fun getInstance() = (INSTANCE ?: instance )
    }

    private lateinit var viewModel: FootballTeamsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.football_teams_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FootballTeamsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
