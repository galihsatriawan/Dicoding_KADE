package id.shobrun.footballleague.ui.events.next

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment
import id.shobrun.footballleague.databinding.NextEventFragmentBinding
import id.shobrun.footballleague.ui.adapters.RecyclerEventsAdapter
import id.shobrun.footballleague.ui.leagues.detail.DetailLeagueActivity
import org.jetbrains.anko.support.v4.intentFor

class NextEventFragment : ViewModelFragment() {
    lateinit var eventRecyclerAdapter: RecyclerEventsAdapter
    companion object {
        fun newInstance() = NextEventFragment()
    }

    private val viewModel by viewModel<NextEventViewModel>()
    private lateinit var binding : NextEventFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater,R.layout.next_event_fragment,container)

        with(binding){
            lifecycleOwner = this@NextEventFragment
            vm = viewModel
        }
        eventRecyclerAdapter.setItems(ArrayList())
        eventRecyclerAdapter.setItemListener {event ->
            val detail = intentFor<DetailLeagueActivity>()
            startActivity(detail)
        }

        viewModel.postLeagueId(1)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        binding.recyclerNextEvent.adapter = eventRecyclerAdapter
    }

}
