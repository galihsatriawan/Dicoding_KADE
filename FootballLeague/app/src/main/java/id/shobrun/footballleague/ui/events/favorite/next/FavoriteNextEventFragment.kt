package id.shobrun.footballleague.ui.events.favorite.next

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment
import id.shobrun.footballleague.databinding.FavoriteNextEventFragmentBinding
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.ui.adapters.RecyclerEventsAdapter
import id.shobrun.footballleague.ui.events.EventsActivity.Companion.EXTRA_LEAGUE
import id.shobrun.footballleague.ui.events.detail.DetailEventActivity
import org.jetbrains.anko.support.v4.intentFor

class FavoriteNextEventFragment : ViewModelFragment() {
    private lateinit var eventRecyclerAdapter: RecyclerEventsAdapter
    companion object {
        fun newInstance() = FavoriteNextEventFragment()
    }

    private val viewModel by viewModel<FavoriteNextEventViewModel>()
    private lateinit var binding : FavoriteNextEventFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.favorite_next_event_fragment,container)

        with(binding){
            lifecycleOwner = this@FavoriteNextEventFragment
            vm = viewModel
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        var league : League? = null
        if(requireArguments().getParcelable<League>(EXTRA_LEAGUE) != null){
            league = requireArguments().getParcelable<League>(EXTRA_LEAGUE)
        }


        viewModel.postLeagueId(league?.idLeague?:-1)
    }
    private fun initRecycler(){
        eventRecyclerAdapter = RecyclerEventsAdapter(ArrayList())
        eventRecyclerAdapter.setItemListener {event ->
            val detail = intentFor<DetailEventActivity>(
                DetailEventActivity.EXTRA_EVENT to event
            )
            startActivity(detail)
        }
        /**
         * Decoration
         */
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.recyclerNextEvent.addItemDecoration(dividerItemDecoration)
        binding.recyclerNextEvent.adapter = eventRecyclerAdapter
    }

}
