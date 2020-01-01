package id.shobrun.footballleague.models.network

import id.shobrun.footballleague.models.NetworkResponseModel
import id.shobrun.footballleague.models.entity.TeamRecord

data class TeamRecordsResponse (
    val table : List<TeamRecord>
):NetworkResponseModel