package id.shobrun.footballleague.views.fragments


import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import id.shobrun.footballleague.R
import id.shobrun.footballleague.models.League
import id.shobrun.footballleague.viewmodels.FootballLeaguesViewModel
import id.shobrun.footballleague.views.iviews.IFootballLeaguesFragment
import id.shobrun.footballleague.views.adapters.RecyclerLeaguesAdapter
import kotlinx.android.synthetic.main.football_leagues_fragment.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.nestedScrollView
import org.jetbrains.anko.support.v4.toast


class FootballLeaguesFragment : Fragment(), IFootballLeaguesFragment {
    lateinit var leaguesAdapter: RecyclerLeaguesAdapter

    companion object {
        const val _RV_LEAGUE = R.id.recycler_league
        private var INSTANCE : FootballLeaguesFragment? = null
        private lateinit var ankoContext : AnkoContext<FootballLeaguesFragment>
        private var instance = {  ->
            INSTANCE =FootballLeaguesFragment()
            INSTANCE
        }

        fun getInstance() = (INSTANCE ?: instance() ) as FootballLeaguesFragment
    }

    private lateinit var viewModel: FootballLeaguesViewModel
    class FootballLeaguesUI(val mAdapter : RecyclerLeaguesAdapter) : AnkoComponent<FootballLeaguesFragment>{

        override fun createView(ui: AnkoContext<FootballLeaguesFragment>): View  = with(ui){
            ankoContext = ui
            return relativeLayout{
                lparams(width = matchParent, height = matchParent)

                val cont_list =
                    nestedScrollView{
                    lparams(width = matchParent,height =  wrapContent)
                    val list = recyclerView{
                        id = _RV_LEAGUE
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                        adapter = mAdapter
                    }
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        leaguesAdapter = RecyclerLeaguesAdapter(requireContext(),ArrayList<League>())
        leaguesAdapter.setItemListener {

        }
        return FootballLeaguesUI(leaguesAdapter).createView(AnkoContext.create(inflater.context,this,false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FootballLeaguesViewModel::class.java)
        viewModel.setAppView(requireContext(),this)
        viewModel.setLeagues()
        viewModel.getLeagues().observe(this, Observer { items ->
            showListLeagues(items)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun showListLeagues(leagues: List<League>) {

        leaguesAdapter.setItems(leagues)
        leaguesAdapter.setItemListener { league ->
            toast(league?.name)
        }
    }

}
