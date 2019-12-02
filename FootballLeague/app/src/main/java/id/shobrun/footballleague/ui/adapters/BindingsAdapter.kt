package id.shobrun.footballleague.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.shobrun.footballleague.extensions.bindResource
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.models.entity.League
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


@BindingAdapter("eventItems")
fun bindEvents(view : RecyclerView, resource: Resource<List<Event>>?){
    when(view.adapter){
        is RecyclerEventsAdapter ->{
            view.bindResource(resource){
                (view.adapter as RecyclerEventsAdapter).setItems(it?.data)
            }
        }
    }

}

