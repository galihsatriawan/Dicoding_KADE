package id.shobrun.footballleague.ui.search.event


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment

/**
 * A simple [Fragment] subclass.
 */
class SearchEventsFragment : ViewModelFragment() {
    private val viewModel by viewModel<SearchEventsViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TextView(activity).apply {
            setText(R.string.hello_blank_fragment)
        }
    }


}
