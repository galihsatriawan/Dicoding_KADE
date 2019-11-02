package id.shobrun.footballleague.views.fragments


import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

import id.shobrun.footballleague.R
import id.shobrun.footballleague.models.Club
import id.shobrun.footballleague.viewmodels.FootballClubsViewModel
import id.shobrun.footballleague.views.iviews.IFootballClubsFragment
import id.shobrun.footballleague.views.adapters.RecyclerClubsAdapter
import kotlinx.android.synthetic.main.football_clubs_fragment.*

class FootballClubsFragment : Fragment(), IFootballClubsFragment {
    lateinit var clubsAdapter: RecyclerClubsAdapter
    companion object {
        private var INSTANCE : FootballClubsFragment? = null
        private var instance = {  ->
            INSTANCE =FootballClubsFragment()
            INSTANCE
        }
        fun getInstance() = (INSTANCE ?: instance() ) as FootballClubsFragment
    }

    private lateinit var viewModel: FootballClubsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.football_clubs_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FootballClubsViewModel::class.java)
        viewModel.setAppView(requireContext(),this)
        viewModel.setClubs()
        viewModel.getClubs().observe(this, Observer { items ->
            showListClubs(items)
        })

        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun showListClubs(clubs: List<Club>) {
        recycler_club_list.layoutManager = LinearLayoutManager(context)
        clubsAdapter = RecyclerClubsAdapter(requireContext(),clubs)
        clubsAdapter.notifyDataSetChanged()
        clubsAdapter.setItemListener { club ->
            Snackbar.make(requireView(),"This is ${club.name}",Snackbar.LENGTH_SHORT)
                .show() }
        recycler_club_list.adapter = clubsAdapter
    }
    override fun showLoading(visible: Boolean) {
        if(visible){
            showContent(false)
            showMessageLayout(false,null)
        }else progressBar.visibility = View.GONE

    }

    override fun showMessageLayout(visible: Boolean, text: String?) {
        if(visible) {
            container_message.visibility = View.VISIBLE
            text_message.text = text
            showLoading(false)
            showContent(false)
        }
        else container_message.visibility = View.GONE

    }

    override fun showContent(visible: Boolean) {
        if(visible) {
            container_content.visibility = View.VISIBLE
            showLoading(false)
            showMessageLayout(false,null)
        }else container_content.visibility = View.GONE
    }
}
