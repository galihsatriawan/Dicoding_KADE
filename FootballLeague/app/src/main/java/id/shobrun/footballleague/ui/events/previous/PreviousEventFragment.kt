package id.shobrun.footballleague.ui.events.previous

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment
import id.shobrun.footballleague.databinding.PreviousEventFragmentBinding
import id.shobrun.footballleague.ui.adapters.RecyclerEventsAdapter
import id.shobrun.footballleague.ui.leagues.detail.DetailLeagueActivity
import org.jetbrains.anko.support.v4.intentFor

class PreviousEventFragment : ViewModelFragment() {
    lateinit var eventsAdapter: RecyclerEventsAdapter
    companion object {
        fun newInstance() = PreviousEventFragment()
    }

    private  val viewModel by viewModel<PreviousEventViewModel>()
    private lateinit var binding : PreviousEventFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater,R.layout.previous_event_fragment,container)
        with(binding){
            lifecycleOwner = this@PreviousEventFragment
            vm = viewModel
        }
        eventsAdapter.setItems(ArrayList())
        eventsAdapter.setItemListener { event ->
            val detail = intentFor<DetailLeagueActivity>()
            startActivity(detail)
        }
        viewModel.postLeagueId(1)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.rvPreviousEvent.adapter = eventsAdapter
        // TODO: Use the ViewModel
    }

}
