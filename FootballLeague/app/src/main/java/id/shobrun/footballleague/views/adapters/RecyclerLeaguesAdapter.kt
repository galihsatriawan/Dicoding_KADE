package id.shobrun.footballleague.views.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.shobrun.footballleague.R
import id.shobrun.footballleague.models.League
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class RecyclerLeaguesAdapter(private val context: Context, private var items: List<League>) :
    RecyclerView.Adapter<RecyclerLeaguesAdapter.LeagueViewHolder>() {
    companion object {
        val _BANNER_LEAGUE = R.id.banner_league
        val _LEAGUE_NAME = R.id.tv_league_name
        val _LEAGUE_DESC = R.id.tv_league_desc
    }

    private lateinit var itemListener: (League) -> Unit
    fun setItemListener(listener: (League) -> Unit) {
        this.itemListener = listener
    }

    fun setItems(items: List<League>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LeagueViewHolder(ItemViewUI().createView(AnkoContext.create(parent.context, parent)))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bind(items[position], itemListener)
    }


    class LeagueViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        val imgLeague = containerView.find<ImageView>(_BANNER_LEAGUE)
        val tvName = containerView.find<TextView>(_LEAGUE_NAME)
        val tvDesc = containerView.find<TextView>(_LEAGUE_DESC)
        fun bind(league: League, itemListener: (League) -> Unit) {
            tvName.text = league.name
            tvDesc.text = league.description
            Glide.with(containerView.context)
                .load(league.banner)
                .apply(RequestOptions().override(300, 300))
                .into(imgLeague)

            containerView.setOnClickListener { itemListener(league) }
        }
    }

    class ItemViewUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            return cardView {
                useCompatPadding = true
                preventCornerOverlap = true
                lparams(matchParent, wrapContent)
                relativeLayout {
                    lparams(matchParent, wrapContent)
                    padding = dip(resources.getDimension(R.dimen.activity_padding_horizontal))
                    isClickable = true
                    background =
                        context.obtainStyledAttributes(arrayOf(R.attr.selectableItemBackground).toIntArray())
                            .getDrawable(0)

                    imageView {
                        id = R.id.banner_league;
                    }.lparams(width = dip(50), height = dip(50)) {
                        rightMargin =
                            (dip(resources.getDimension(R.dimen.adapter_horizontal_margin)))
                    }

                    textView {
                        id = _LEAGUE_NAME

                    }.lparams(width = wrapContent, height = wrapContent) {
                        bottomMargin =
                            (dip(resources.getDimension(R.dimen.adapter_vertical_margin)))
                        rightOf(_BANNER_LEAGUE)
                    }

                    textView {
                        id = _LEAGUE_DESC
                    }.lparams(width = wrapContent, height = wrapContent) {
                        rightOf(_BANNER_LEAGUE)
                        below(_LEAGUE_NAME)
                    }
                }
            }
        }

    }

}