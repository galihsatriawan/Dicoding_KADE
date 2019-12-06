package id.shobrun.footballleague.ui.events.next

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment

import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment
import id.shobrun.footballleague.databinding.NextEventFragmentBinding
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.ui.adapters.RecyclerEventsAdapter
import id.shobrun.footballleague.ui.events.detail.DetailEventActivity
import id.shobrun.footballleague.ui.events.previous.PreviousEventFragment
import id.shobrun.footballleague.ui.leagues.detail.DetailLeagueActivity
import org.jetbrains.anko.support.v4.intentFor

class NextEventFragment : ViewModelFragment() {
    private lateinit var eventRecyclerAdapter: RecyclerEventsAdapter
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

        eventRecyclerAdapter = RecyclerEventsAdapter(ArrayList())
        eventRecyclerAdapter.setItemListener {event ->
            val detail = intentFor<DetailEventActivity>(
                DetailEventActivity.EXTRA_EVENT to event
            )
            startActivity(detail)
        }
        var league : League? = null
        if(requireArguments().getParcelable<League>(PreviousEventFragment.EXTRA_EVENT) != null){
            league = requireArguments().getParcelable<League>(PreviousEventFragment.EXTRA_EVENT)
        }


        viewModel.postLeagueId(league?.idLeague?:-1)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val dividerItemDecoration = DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL)
        binding.recyclerNextEvent.addItemDecoration(dividerItemDecoration)
        binding.recyclerNextEvent.adapter = eventRecyclerAdapter
    }

}
