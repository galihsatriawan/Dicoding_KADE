package id.shobrun.footballleague.views.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import id.shobrun.footballleague.R
import id.shobrun.footballleague.models.League
import id.shobrun.footballleague.viewmodels.FootballLeaguesViewModel
import id.shobrun.footballleague.views.DetailLeaguesActivity
import id.shobrun.footballleague.views.adapters.RecyclerLeaguesAdapter
import id.shobrun.footballleague.views.iviews.IFootballLeaguesFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.nestedScrollView


class FootballLeaguesFragment : Fragment(), IFootballLeaguesFragment,AnkoLogger {
    lateinit var leaguesAdapter: RecyclerLeaguesAdapter

    companion object {
        const val ID_RV_LEAGUE = R.id.recycler_league
        private var INSTANCE : FootballLeaguesFragment? = null
        private lateinit var ankoContext : AnkoContext<FootballLeaguesFragment>
        private var instance = {
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

                nestedScrollView{
                lparams(width = matchParent,height =  wrapContent)
                val list = recyclerView{
                    id = ID_RV_LEAGUE
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = GridLayoutManager(ctx,2)
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
        leaguesAdapter = RecyclerLeaguesAdapter(ArrayList())
        leaguesAdapter.setItemListener { league ->
            val detail = intentFor<DetailLeaguesActivity>(DetailLeaguesActivity.EXTRA_LEAGUE to league)
            startActivity(detail)
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

    override fun showListLeagues(leagues: List<League>) {
        leaguesAdapter.setItems(leagues)
    }

}
