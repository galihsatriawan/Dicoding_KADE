package id.shobrun.footballleague.ui.events.favorite.previous

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment
import id.shobrun.footballleague.databinding.FavoritePreviousEventFragmentBinding
import id.shobrun.footballleague.databinding.PreviousEventFragmentBinding
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.ui.adapters.RecyclerEventsAdapter
import id.shobrun.footballleague.ui.events.detail.DetailEventActivity
import id.shobrun.footballleague.ui.events.previous.PreviousEventViewModel
import org.jetbrains.anko.support.v4.intentFor

class FavoritePreviousEventFragment : ViewModelFragment() {
    private lateinit var eventsAdapter: RecyclerEventsAdapter
    companion object {
        const val EXTRA_EVENT = "extra_event"
        fun newInstance() = FavoritePreviousEventFragment()
    }

    private  val viewModel by viewModel<FavoritePreviousEventViewModel>()
    private lateinit var binding : FavoritePreviousEventFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.favorite_previous_event_fragment,container)
        with(binding){
            lifecycleOwner = this@FavoritePreviousEventFragment
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
        if(requireArguments().getParcelable<League>(EXTRA_EVENT) != null){
            league = requireArguments().getParcelable(EXTRA_EVENT)
        }

        viewModel.postLeagueId(league?.idLeague?:-1)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.rvPreviousEvent.addItemDecoration(dividerItemDecoration)
        binding.rvPreviousEvent.adapter = eventsAdapter
    }
}
