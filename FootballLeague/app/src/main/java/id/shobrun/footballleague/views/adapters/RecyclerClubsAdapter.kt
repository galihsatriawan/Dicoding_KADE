package id.shobrun.footballleague.views.adapters

import android.content.Context
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.shobrun.footballleague.R
import id.shobrun.footballleague.models.Club
import kotlinx.android.synthetic.main.item_club.view.*

class RecyclerClubsAdapter(private val context: Context, private var items:List<Club>) :RecyclerView.Adapter<RecyclerClubsAdapter.ClubViewHolder>(){

    private lateinit var itemListener: (Club) -> Unit
    fun setItemListener(listener : (Club)->Unit){
        this.itemListener = listener
    }
    fun setItems(items : List<Club>){
        this.items = items
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ClubViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.item_club,parent,false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bind(items[position],itemListener)
    }


    class ClubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(club : Club, itemListener:(Club)->Unit){
            with(itemView){
                tv_club_name.text = club.name
                Glide.with(itemView.context)
                    .load(club.image)
                    .apply(RequestOptions().override(300,300))
                    .into(img_club)
                setOnClickListener { v-> itemListener(club) }
            }
        }
    }


}