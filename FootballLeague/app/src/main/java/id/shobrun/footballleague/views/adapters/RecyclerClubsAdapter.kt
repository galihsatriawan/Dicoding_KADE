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
import id.shobrun.footballleague.models.Club
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.*


class RecyclerClubsAdapter(private val context: Context, private var items:List<Club>) :RecyclerView.Adapter<RecyclerClubsAdapter.ClubViewHolder>(){
    companion object{
        val _IMG_CLUB = R.id.img_club
        val _CLUB_NAME = R.id.tv_club_name
        val _CLUB_DESC = R.id.tv_club_desc
    }
    private lateinit var itemListener: (Club) -> Unit
    fun setItemListener(listener : (Club)->Unit){
        this.itemListener = listener
    }
    fun setItems(items : List<Club>){
        this.items = items
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ClubViewHolder(ItemViewUI().createView(AnkoContext.create(parent.context,parent)))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bind(items[position],itemListener)
    }


    class ClubViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),LayoutContainer {
        val imgClub = containerView.findViewById(_IMG_CLUB) as ImageView
        val tvName  = containerView.findViewById(_CLUB_NAME) as TextView
        val tvDesc = containerView.findViewById(_CLUB_DESC) as TextView
        fun bind(club : Club, itemListener:(Club)->Unit){
            tvName.text = club.name
            tvDesc.text = club.description
            Glide.with(containerView.context)
                .load(club.image)
                    .apply(RequestOptions().override(300,300))
                    .into(imgClub)

            containerView.setOnClickListener { itemListener(club) }
        }
    }
    class ItemViewUI: AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            return relativeLayout {
                lparams(matchParent, wrapContent)
                padding = dip(resources.getDimension(R.dimen.activity_padding_horizontal))
                imageView{
                    id = R.id.img_club;
                }.lparams(width = dip(50),height = dip(50)){
                    rightMargin =(dip(resources.getDimension(R.dimen.adapter_horizontal_margin)))
                }

                textView{
                    id = _CLUB_NAME

                }.lparams(width = wrapContent,height = wrapContent){
                    bottomMargin = (dip(resources.getDimension(R.dimen.adapter_vertical_margin)))
                    rightOf(_IMG_CLUB)
                }

                textView{
                    id = _CLUB_DESC
                }.lparams(width = wrapContent,height = wrapContent){
                    rightOf(_IMG_CLUB)
                    below(_CLUB_NAME)
                }
            }
        }

    }

}