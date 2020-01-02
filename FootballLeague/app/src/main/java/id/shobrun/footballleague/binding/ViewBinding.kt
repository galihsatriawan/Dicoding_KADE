package id.shobrun.footballleague.binding

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import id.shobrun.footballleague.R
import id.shobrun.footballleague.extensions.bindResource
import id.shobrun.footballleague.extensions.gone
import id.shobrun.footballleague.extensions.visible
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.Status
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.models.entity.Team
import timber.log.Timber


@BindingAdapter("visibilityByResource")
fun bindVisibilityByResource(view: View, resource: Resource<Any>?) {
    view.bindResource(resource) {
        if (it.data != null) it.data.let {
            view.visible()
        }
    }
}

@BindingAdapter("isError")
fun bindIsError(view: LinearLayout, resource: Resource<Any>?) {
    Timber.d("Error")
    view.bindResource(resource) {
        if (it.data != null) it.data.let {
            view.visible()
        }

    }
}

@BindingAdapter("bannerLeague")
fun bindBanner(view: ImageView, resource: Resource<League>?) {
    view.bindResource(resource) {
        Glide.with(view.context)
            .load(it.data?.bannerUrl ?: "")
            .error(R.drawable.ic_error_black_24dp)
            .into(view)
    }
}

@BindingAdapter("nameLeague")
fun bindName(view: TextView, resource: Resource<League>?) {
    view.bindResource(resource) {
        view.text = it.data?.name
    }
}

@BindingAdapter("descriptionLeague")
fun bindDescription(view: TextView, resource: Resource<League>?) {
    view.bindResource(resource) {
        view.text = it.data?.description
    }
}


/**
 * Binding Detail Event
 *
 */
@BindingAdapter("eventName")
fun bindEventName(view: TextView, resource: Resource<Event>?) {
    view.bindResource(resource) {
        view.text = it.data?.eventName
    }
}

@BindingAdapter("eventDate")
fun bindEventDate(view: TextView, resource: Resource<Event>?) {
    view.bindResource(resource) {
        view.text = it.data?.dateEvent
    }
}

@BindingAdapter("leagueNameInEvent")
fun bindNameLeague(view: TextView, resource: Resource<Event>?) {
    view.bindResource(resource) {
        view.text = it.data?.leagueName
    }
}

/**
 * Home Segment
 */

@BindingAdapter("homeName")
fun bindHomeName(view: TextView, resource: Resource<Event>?) {
    view.bindResource(resource) {
        view.text = it.data?.homeTeam
    }
}

@BindingAdapter("homeScore")
fun bindHomeScore(view: TextView, resource: Resource<Event>?) {
    view.bindResource(resource) {
        view.text = "${it.data?.homeScore ?: ""}"
    }
}

@BindingAdapter("homeGoals")
fun bindHomeGoals(view: TextView, resource: Resource<Event>?) {
    view.bindResource(resource) {
        view.text = it.data?.homeGoalDetails
    }
}

@BindingAdapter("homeYellowCards")
fun bindHomeYellowCards(view: TextView, resource: Resource<Event>?) {
    view.bindResource(resource) {
        view.text = it.data?.homeYellowCards
    }
}

@BindingAdapter("homeRedCards")
fun bindHomeRedCards(view: TextView, resource: Resource<Event>?) {
    view.bindResource(resource) {
        view.text = it.data?.homeRedCards
    }
}

@BindingAdapter("homeBadge")
fun bindHomeBadge(view: ImageView, resource: Resource<Team>?) {
    view.bindResource(resource) {
        Glide.with(view.context)
            .load(it.data?.teamBadge ?: "")
            .into(view)
    }
}

/**
 * Away Segment
 */
@BindingAdapter("awayName")
fun bindAwayName(view: TextView, resource: Resource<Event>?) {
    view.bindResource(resource) {
        view.text = it.data?.awayTeam
    }
}

@BindingAdapter("awayScore")
fun bindAwayScore(view: TextView, resource: Resource<Event>?) {
    view.bindResource(resource) {
        view.text = "${it.data?.homeScore ?: ""}"
    }
}

@BindingAdapter("awayGoals")
fun bindAwayGoals(view: TextView, resource: Resource<Event>?) {
    view.bindResource(resource) {
        view.text = it.data?.homeGoalDetails
    }
}

@BindingAdapter("awayYellowCards")
fun bindAwayYellowCards(view: TextView, resource: Resource<Event>?) {
    view.bindResource(resource) {
        view.text = it.data?.homeYellowCards
    }
}

@BindingAdapter("awayRedCards")
fun bindAwayRedCards(view: TextView, resource: Resource<Event>?) {
    view.bindResource(resource) {
        view.text = it.data?.homeRedCards
    }
}

@BindingAdapter("awayBadge")
fun bindAwayBadge(view: ImageView, resource: Resource<Team>?) {
    view.bindResource(resource) {
        Glide.with(view.context)
            .load(it.data?.teamBadge ?: "")
            .into(view)
    }
}

/*
    Search Segment
 */

@BindingAdapter("loadingEvents")
fun bindVisibleLoadingEvents(view: ProgressBar, resource: Resource<List<Event>>?) {
    if (resource != null) {
        if (resource.status == Status.LOADING) view.visible()
        else view.gone()
    }
}

@BindingAdapter("visibleMessage")
fun bindVisibleMessage(view: LinearLayout, resource: Resource<List<Any>>?) {
    view.bindResource(resource) {
        if (it.data?.isNullOrEmpty() != false) {
            view.visible()
        } else {
            view.gone()
        }
    }
}

@BindingAdapter("messageEvents")
fun bindMessage(view: TextView, resource: Resource<List<Any>>?) {
    view.bindResource(resource) {
        if (it.status == Status.ERROR) {
            view.text = it.message
        } else {
            if (it.data.isNullOrEmpty()) {
                view.text = view.context.getString(R.string.empty_data)
            }
        }

    }
}

/*
Favorite Segment
 */
@BindingAdapter("visibleMessageFavorite")
fun bindVisibleMessageFavorite(view: LinearLayout, resource: List<Any>?) {
    if (resource?.isNullOrEmpty() != false) {
        view.visible()
    } else {
        view.gone()
    }
}

@BindingAdapter("messageEventsFavorite")
fun<T> bindMessageFavorite(view: TextView, resource: List<Any>?) {

    if (resource.isNullOrEmpty()) {
        view.text = view.context.getString(R.string.empty_data)
    }
}
/**
 * Detail Team
 */
@BindingAdapter("logoTeam")
fun bindLogoTeam(view: ImageView, resource: Resource<Team>?){
    view.bindResource(resource) {
        Glide.with(view.context)
            .load(it.data?.teamBadge?:"")
            .error(R.drawable.ic_error_black_24dp)
            .into(view)
    }
}
@BindingAdapter("nameTeam")
fun bindNameTeam(view: TextView, resource: Resource<Team>?){
    view.bindResource(resource){
        view.text = "${it?.data?.teamName ?:""}"
    }
}
@BindingAdapter("alternateNameTeam")
fun bindAlternateNameTeam(view: TextView, resource: Resource<Team>?){
    view.bindResource(resource){
        view.text = "${it?.data?.alternateName ?:""}"
    }
}

@BindingAdapter("descriptionTeam")
fun bindDescriptionTeam(view: TextView, resource: Resource<Team>?){
    view.bindResource(resource){
        view.text = "${it?.data?.descriptionEN?:""}"
    }
}
@BindingAdapter("imgStadiumTeam")
fun bindImgStadiumTeam(view: ImageView, resource: Resource<Team>?){
    view.bindResource(resource) {
        Glide.with(view.context)
            .load(it.data?.imgStadium?:"")
            .error(R.drawable.ic_error_black_24dp)
            .into(view)
    }
}
@BindingAdapter("nameStadium")
fun bindNameStadium(view: TextView, resource: Resource<Team>?){
    view.bindResource(resource){
        view.text = "${it?.data?.stadiumName?:""}"
    }
}
