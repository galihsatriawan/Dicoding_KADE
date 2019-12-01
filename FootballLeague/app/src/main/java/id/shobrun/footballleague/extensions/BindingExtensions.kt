package id.shobrun.footballleague.extensions

import android.view.View
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.Status
import org.jetbrains.anko.toast

inline fun <reified T> View.bindResource(resource: Resource<T>?, onSuccess: (Resource<T>) -> Unit) {
    if (resource != null) {
        when (resource.status) {
            Status.LOADING -> Unit
            Status.SUCCESS -> onSuccess(resource)
            Status.ERROR -> this.context.toast(resource.errorEnvelope?.status_message.toString())
        }
    }
}
