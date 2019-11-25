package id.shobrun.footballleague.views.leagues

import android.os.Bundle
import id.shobrun.footballleague.R
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import id.shobrun.footballleague.models.League
import id.shobrun.footballleague.views.iviews.IDetailLeagueActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.support.v4.nestedScrollView
import javax.inject.Inject

class DetailLeaguesActivity : DaggerAppCompatActivity(),IDetailLeagueActivity {

    override fun showDetail(league: League?) {
        val nameLeague = find<TextView>(ID_LEAGUE_NAME)
        val descLeague = find<TextView>(ID_LEAGUE_DESC)
        val bannerLeague = find<ImageView>(ID_LEAGUE_BANNER)
        nameLeague.text = league?.name
        descLeague.text = league?.description
        bannerLeague.setImageResource(league?.banner?:0)
    }

    companion object{
        const val EXTRA_LEAGUE = "extra_league"
        const val ID_LEAGUE_BANNER = R.id.banner_league
        const val ID_LEAGUE_NAME = R.id.tv_league_name
        const val ID_LEAGUE_DESC = R.id.tv_league_desc
    }


    private lateinit var viewModel : DetailLeagueViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailLeagueUI()
            .setContentView(this)

        var league:League? = null
        if(intent.getParcelableExtra<League>(EXTRA_LEAGUE) != null)
        { league =  intent.getParcelableExtra(EXTRA_LEAGUE)}

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.detail_league)
        actionBar?.setDisplayHomeAsUpEnabled(true)


        viewModel = ViewModelProviders.of(this).get(DetailLeagueViewModel::class.java)

        viewModel.setLeague(league)
        viewModel.getLeague().observe(this, Observer {
            showDetail(league)
        })
    }

    class DetailLeagueUI : AnkoComponent<DetailLeaguesActivity>{
        override fun createView(ui: AnkoContext<DetailLeaguesActivity>): View = with(ui){
            return nestedScrollView {
                lparams(width = matchParent, height = wrapContent)
                relativeLayout {

                    val cont = cardView {
                        id = R.id.container_banner
                        relativeLayout {
                            val banner = imageView{
                                id =
                                    ID_LEAGUE_BANNER
                                padding =dip(resources.getDimension(R.dimen.activity_padding_horizontal))
                            }.lparams(width = dip(110),height = dip(110)){
                                centerHorizontally()
                            }
                            val title = textView {
                                id =
                                    ID_LEAGUE_NAME
                                textAppearance = R.style.TextAppearance_AppCompat_Headline
                            }.lparams(width = wrapContent,height = wrapContent){
                                centerHorizontally()
                                margin = dip(resources.getDimension(R.dimen.activity_vertical_margin_8dp))
                                below(banner)
                            }
                        }

                    }.lparams(width = wrapContent,height = wrapContent){
                        margin = dip(resources.getDimension(R.dimen.activity_vertical_margin_8dp))
                        centerHorizontally()

                    }
                    val titleDesc = textView(resources.getString(R.string.description)){
                        id = R.id.tv_title_league_desc
                        textAppearance = R.style.TextAppearance_AppCompat_Title

                    }.lparams(width = wrapContent,height = wrapContent){
                        margin = dip(resources.getDimension(R.dimen.activity_vertical_margin_8dp))
                        below(cont)
                    }
                    val desc = textView{
                        id =
                            ID_LEAGUE_DESC

                    }.lparams(width = wrapContent,height = wrapContent){
                        margin = dip(resources.getDimension(R.dimen.activity_vertical_margin_8dp))
                        below(titleDesc)
                    }
                }.lparams(width = matchParent,height = wrapContent)
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()

    }
}