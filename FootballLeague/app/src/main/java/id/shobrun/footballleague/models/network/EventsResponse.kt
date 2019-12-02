package id.shobrun.footballleague.models.network

import id.shobrun.footballleague.models.NetworkResponseModel
import id.shobrun.footballleague.models.entity.Event

class EventsResponse (
    val events : List<Event>
) : NetworkResponseModel