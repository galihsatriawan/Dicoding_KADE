package id.shobrun.footballleague.models.network

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.api.ApiResponse
import id.shobrun.footballleague.models.NetworkResponseModel
import id.shobrun.footballleague.models.entity.Event

class EventsResponse (
    val events : LiveData<ApiResponse<List<Event>>>
) : NetworkResponseModel