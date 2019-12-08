package id.shobrun.footballleague.ui.events.previous

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment
import id.shobrun.footballleague.databinding.PreviousEventFragmentBinding
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.ui.adapters.RecyclerEventsAdapter
import id.shobrun.footballleague.ui.events.EventsActivity.Companion.EXTRA_LEAGUE
import id.shobrun.footballleague.ui.events.detail.DetailEventActivity
import org.jetbrains.anko.support.v4.intentFor

class PreviousEventFragment : ViewModelFragment() {
    private lateinit var eventsAdapter: RecyclerEventsAdapter
    companion object {
        const val EXTRA_EVENT = "extra_event"
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
        eventsAdapter = RecyclerEventsAdapter(ArrayList())
        eventsAdapter.setItemListener { event ->
            val detail = intentFor<DetailEventActivity>(
                DetailEventActivity.EXTRA_EVENT to event
            )
            startActivity(detail)
        }
        var league : League? = null
        if(requireArguments().getParcelable<League>(EXTRA_LEAGUE) != null){
            league = requireArguments().getParcelable(EXTRA_LEAGUE)
        }

        viewModel.postLeagueId(league?.idLeague?:-1)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val dividerItemDecoration = DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL)
        binding.rvPreviousEvent.addItemDecoration(dividerItemDecoration)
        binding.rvPreviousEvent.adapter = eventsAdapter
    }
}
