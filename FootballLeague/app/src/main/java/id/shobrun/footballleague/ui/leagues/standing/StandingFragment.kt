package id.shobrun.footballleague.ui.leagues.standing

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment

class StandingFragment : ViewModelFragment() {

    companion object {
        fun newInstance() = StandingFragment()
    }

    private val viewModel by viewModel<StandingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_standing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
