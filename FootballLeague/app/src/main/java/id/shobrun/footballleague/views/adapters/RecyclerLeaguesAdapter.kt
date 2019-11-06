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



class RecyclerLeaguesAdapter(private var items: List<League>) :
    RecyclerView.Adapter<RecyclerLeaguesAdapter.LeagueViewHolder>(),AnkoLogger{
    companion object {
        const val ID_LEAGUE_BANNER = R.id.banner_league
        const val ID_LEAGUE_NAME = R.id.tv_league_name
        const val ID_LEAGUE_DESC = R.id.tv_league_desc
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
        holder.bind(items[position])
        holder.listen { position->
            itemListener(items[position])
        }
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition)
        }
        return this
    }
    class LeagueViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer,AnkoLogger  {
        val imgLeague = containerView.find<ImageView>(ID_LEAGUE_BANNER)
        val tvName = containerView.find<TextView>(ID_LEAGUE_NAME)
        val tvDesc = containerView.find<TextView>(ID_LEAGUE_DESC)
        fun bind(league: League) {
            tvName.text = league.name
            tvDesc.text = "${league.description.subSequence(0,10)} [...]"
            Glide.with(containerView.context)
                .load(league.banner)
                .apply(RequestOptions().override(110, 110))
                .into(imgLeague)

        }
    }

    class ItemViewUI : AnkoComponent<ViewGroup>,AnkoLogger {
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            return cardView {
                useCompatPadding = true
                preventCornerOverlap = true
                isClickable = true
                lparams(matchParent, wrapContent)
                relativeLayout {
                    lparams(matchParent, wrapContent)
                    padding = dip(resources.getDimension(R.dimen.activity_padding_horizontal))
                    background =
                        context.obtainStyledAttributes(arrayOf(R.attr.selectableItemBackground).toIntArray())
                            .getDrawable(0)

                    imageView {
                        id = R.id.banner_league
                    }.lparams(width = dip(50), height = dip(80)) {
                        centerHorizontally()
                    }

                    textView {
                        id = ID_LEAGUE_NAME

                    }.lparams(width = wrapContent, height = wrapContent) {
                        bottomMargin =
                            (dip(resources.getDimension(R.dimen.adapter_vertical_margin)))
                        below(ID_LEAGUE_BANNER)
                        centerHorizontally()
                    }

                    textView {
                        id = ID_LEAGUE_DESC
                    }.lparams(width = wrapContent, height = wrapContent) {
                        below(ID_LEAGUE_NAME)
                        centerHorizontally()
                    }
                }
            }
        }

    }

}