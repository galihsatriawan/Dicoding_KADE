package id.shobrun.footballleague.ui.events.next

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.shobrun.footballleague.R

class NextEventFragment : Fragment() {

    companion object {
        fun newInstance() = NextEventFragment()
    }

    private lateinit var viewModel: NextEventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.next_event_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NextEventViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
