package id.shobrun.footballleague.ui.adapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.Resource
import id.shobrun.footballleague.models.League
import id.shobrun.footballleague.views.adapters.RecyclerLeaguesAdapter

@BindingAdapter("loadImage")
fun bindLoadImage(view : ImageView, imageUrl : Any){
    Glide.with(view.context)
        .load(imageUrl)
        .into(view)
}

@BindingAdapter("items")
fun bindAdapter(view : RecyclerView, items : List<League>){
    when(view.adapter){
        is RecyclerLeaguesAdapter ->
        {
            if(items != null){
                (view.adapter as RecyclerLeaguesAdapter).setItems(items)
            }

        }
    }

}

