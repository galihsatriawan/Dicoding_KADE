package id.shobrun.footballleague.api

import id.shobrun.footballleague.utils.LiveDataTestUtil
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LeagueApiTest : ApiAbstract<LeagueApi>() {

    private lateinit var service: LeagueApi

    @Before
    fun initService() {
        this.service = createService(LeagueApi::class.java)
    }

    /**
     * Scenario
     * 1. check id league
     * 2. check league name
     */
    @Test
    fun getLeagueById() {
        enqueueResponse("/league_detail.json")
        val idLeague = 4332
        val response = LiveDataTestUtil.getValue(service.getLeagueById(idLeague))
        assertThat(response.body?.leagues?.get(0)?.idLeague, `is`(idLeague))
        assertThat(response.body?.leagues?.get(0)?.name, `is`("Italian Serie A"))
    }
}