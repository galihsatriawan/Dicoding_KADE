package id.shobrun.footballleague.utils

import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.models.entity.Team


class MockTestUtil {
    companion object {
        fun mockTeam() = Team(
            1, 1, "team", "team", "badge", "team", 1992, "soccer",
            "team", "team", "team", "team", "team", 21, "team", "team"
            , "team", 0, ""
        )

        fun mockLeague() = League(1, "league", "banner", "logo", 1, "desc")
        fun mockEvent() = Event(
            1, "event", "Soccer", 1, "event", "event", "event",
            1, 1, "event", "event", "event", "event", "event", "event",
            "event", "event", 1, 1, 1, "[qry=event]"
        )

        fun mockLeagueList(): List<League> {
            val leagues = ArrayList<League>()
            leagues.add(mockLeague())
            leagues.add(mockLeague())
            leagues.add(mockLeague())
            return leagues
        }

        fun mockEventList(): List<Event> {
            val events = ArrayList<Event>()
            events.add(mockEvent())
            events.add(mockEvent())
            events.add(mockEvent())
            return events
        }
    }
}