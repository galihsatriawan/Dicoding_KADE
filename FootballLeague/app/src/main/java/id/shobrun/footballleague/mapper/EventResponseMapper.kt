package id.shobrun.footballleague.mapper

import id.shobrun.footballleague.models.network.EventsResponse

class EventResponseMapper : NetworkResponseMapper<EventsResponse>{
    override fun onLastPage(response: EventsResponse): Boolean {
        return true
    }
}