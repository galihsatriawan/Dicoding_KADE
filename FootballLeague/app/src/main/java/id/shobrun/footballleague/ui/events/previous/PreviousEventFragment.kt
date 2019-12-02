package id.shobrun.footballleague.ui.events.previous

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.shobrun.footballleague.R

class PreviousEventFragment : Fragment() {

    companion object {
        fun newInstance() = PreviousEventFragment()
    }

    private lateinit var viewModel: PreviousEventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.previous_event_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PreviousEventViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
