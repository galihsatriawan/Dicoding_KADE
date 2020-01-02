package id.shobrun.footballleague.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.shobrun.footballleague.extensions.bindResource
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.models.entity.Team
import id.shobrun.footballleague.ui.adapters.RecyclerEventsAdapter
import id.shobrun.footballleague.ui.adapters.RecyclerLeaguesAdapter
import id.shobrun.footballleague.ui.adapters.RecyclerTeamsAdapter
import id.shobrun.footballleague.utils.AbsentLiveData

@BindingAdapter("loadImage")
fun bindLoadImage(view: ImageView, imageUrl: Any) {
    Glide.with(view.context)
        .load(imageUrl)
        .into(view)
}

@BindingAdapter("items")
fun bindAdapter(view: RecyclerView, items: List<League>) {
    when (view.adapter) {
        is RecyclerLeaguesAdapter -> {
            if (items != null) {
                (view.adapter as RecyclerLeaguesAdapter).setItems(items)
            }

        }
    }
}

@BindingAdapter("liveItems")
fun <T> bindAdapterLiveData(view: RecyclerView, items: LiveData<List<T>>?) {
    when (view.adapter) {
        is RecyclerEventsAdapter -> {
            items?.value.let {
                val data = it as List<Event>?
                (view.adapter as RecyclerEventsAdapter).setItems(data ?: ArrayList())
            }
        }
        is RecyclerTeamsAdapter ->{
            items?.value.let {
                val data = it as List<Team>?
                (view.adapter as RecyclerTeamsAdapter).setItems(data ?: ArrayList())
            }
        }
    }
}


@BindingAdapter("resourceItems")
fun<T> bindEvents(view: RecyclerView, resource: Resource<List<T>>?) {
    when (view.adapter) {
        is RecyclerEventsAdapter -> {
            view.bindResource(resource) {
                val data = it.data as List<Event>?
                (view.adapter as RecyclerEventsAdapter).setItems(data)
            }
        }
        is RecyclerTeamsAdapter ->{
            view.bindResource(resource){
                val data = it.data as List<Team>?
                (view.adapter as RecyclerTeamsAdapter).setItems(data)
            }
        }
    }
}



