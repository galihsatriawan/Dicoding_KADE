package id.shobrun.footballleague.mapper

import id.shobrun.footballleague.models.network.EventSearchResponse

class EventSearchResponseMapper : NetworkResponseMapper<EventSearchResponse>{
    override fun onLastPage(response: EventSearchResponse): Boolean {
        return true
    }
}