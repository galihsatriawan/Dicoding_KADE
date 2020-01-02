package id.shobrun.footballleague.ui.search.event


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment
import id.shobrun.footballleague.databinding.FragmentSearchEventsBinding
import id.shobrun.footballleague.ui.adapters.RecyclerEventsAdapter
import id.shobrun.footballleague.ui.events.detail.DetailEventActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.intentFor

/**
 * A simple [Fragment] subclass.
 */
class SearchEventsFragment : ViewModelFragment() {
    val viewModel by viewModel<SearchEventsViewModel>()
    lateinit var binding : FragmentSearchEventsBinding
    private lateinit var eventAdapter: RecyclerEventsAdapter
    companion object{
      fun newInstance() = SearchEventsFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater,R.layout.fragment_search_events,container)
        with(binding) {
            lifecycleOwner = this@SearchEventsFragment
            vm = viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.recyclerSearchEvent.addItemDecoration(dividerItemDecoration)
        binding.recyclerSearchEvent.adapter = eventAdapter
    }
    private fun initRecycler(){
        eventAdapter = RecyclerEventsAdapter(ArrayList())
        eventAdapter.setItemListener { event ->
            val detail = intentFor<DetailEventActivity>(
                DetailEventActivity.EXTRA_EVENT to event
            )
            startActivity(detail)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_events, menu)

        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /*
            Gunakan method ini ketika search selesai atau OK
             */
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.postFilter(query)
                return true
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
             */
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.postFilter(newText)
                return false
            }
        })
    }

}
