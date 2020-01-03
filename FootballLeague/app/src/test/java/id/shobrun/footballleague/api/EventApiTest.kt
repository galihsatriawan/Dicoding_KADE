package id.shobrun.footballleague.api

import id.shobrun.footballleague.utils.LiveDataTestUtil
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsString
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class EventApiTest : ApiAbstract<EventApi>() {
    private lateinit var service: EventApi

    @Before
    fun initService() {
        this.service = createService(EventApi::class.java)
    }

    /**
     * Scenario
     * Only check Id League
     */
    @Test
    fun getNextEvents() {
        enqueueResponse("/next_event.json")
        val idLeague = 4396
        val response = LiveDataTestUtil.getValue(service.getNextEvents(idLeague))
        assertThat(response.body?.events?.get(0)?.idLeague, `is`(idLeague))
    }

    /**
     * Scenario
     * Only check Id League
     */
    @Test
    fun getPastEvents() {
        enqueueResponse("/past_event.json")
        val idLeague = 4396
        val response = LiveDataTestUtil.getValue(service.getNextEvents(idLeague))
        assertThat(response.body?.events?.get(0)?.idLeague, `is`(idLeague))
    }

    /**
     * Scenario
     * check Id Event
     * check id League
     * check name Event
     */
    @Test
    fun getDetailEvents() {
        enqueueResponse("/event_detail.json")
        val idEvent = 441613
        val response = LiveDataTestUtil.getValue(service.getDetailEvents(idEvent))
        assertThat(response.body?.events?.get(0)?.idEvent, `is`(idEvent))
        assertThat(response.body?.events?.get(0)?.idLeague, `is`(4328))
        assertThat(response.body?.events?.get(0)?.eventName, `is`("Liverpool vs Swansea"))
    }

    /**
     * Scenario
     * Check event name contains the query or not
     */
    @Test
    fun getSearchEvents() {
        enqueueResponse("/event_search.json")
        val query = "sunderland"
        val response = LiveDataTestUtil.getValue(service.getSearchEvents(query))
        assertThat(response.body?.event?.get(0)?.eventName?.toLowerCase(), containsString(query))
    }
}