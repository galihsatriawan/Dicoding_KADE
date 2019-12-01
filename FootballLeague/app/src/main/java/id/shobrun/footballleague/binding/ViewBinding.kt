package id.shobrun.footballleague.binding

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import id.shobrun.footballleague.extensions.bindResource
import id.shobrun.footballleague.extensions.visible
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.League
import timber.log.Timber


@BindingAdapter("visibilityByResource")
fun bindVisibilityByResource(view: View, resource: Resource<List<Any>>?) {
    view.bindResource(resource) {
        if (it.data != null) it.data.let {
            view.visible()
        }
    }
}

@BindingAdapter("descriptionLeague")
fun bindDescription(view: TextView, resource: Resource<League>?) {
    Timber.d("Resource ${resource}")

    view.bindResource(resource) {
        view.text = it.data?.description
    }


}