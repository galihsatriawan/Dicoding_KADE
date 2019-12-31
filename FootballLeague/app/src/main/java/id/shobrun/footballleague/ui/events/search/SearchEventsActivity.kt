package id.shobrun.footballleague.ui.events.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelActivity
import id.shobrun.footballleague.databinding.ActivitySearchEventsBinding
import id.shobrun.footballleague.extensions.simpleToolbarWithHome
import id.shobrun.footballleague.testing.OpenForTesting
import id.shobrun.footballleague.ui.adapters.RecyclerEventsAdapter
import id.shobrun.footballleague.ui.events.detail.DetailEventActivity
import kotlinx.android.synthetic.main.activity_event_detail.*
import org.jetbrains.anko.intentFor

@OpenForTesting
class SearchEventsActivity : ViewModelActivity() {

    val viewModel by viewModel<SearchEventsViewModel>()
    val binding by binding<ActivitySearchEventsBinding>(R.layout.activity_search_events)
    private lateinit var eventAdapter: RecyclerEventsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            lifecycleOwner = this@SearchEventsActivity
            vm = viewModel
        }

        eventAdapter = RecyclerEventsAdapter(ArrayList())
        eventAdapter.setItemListener { event ->
            val detail = intentFor<DetailEventActivity>(
                DetailEventActivity.EXTRA_EVENT to event
            )
            startActivity(detail)
        }
        val dividerItemDecoration =
            DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        binding.recyclerSearchEvent.addItemDecoration(dividerItemDecoration)
        binding.recyclerSearchEvent.adapter = eventAdapter
        simpleToolbarWithHome(toolbar, "Search Event")

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_events, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
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
//                viewModel.postFilter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}
