package id.shobrun.footballleague.api

import id.shobrun.footballleague.utils.LiveDataTestUtil
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TeamApiTest : ApiAbstract<TeamApi>() {
    private lateinit var service : TeamApi

    @Before
    fun initService(){
        this.service = createService(TeamApi::class.java)
    }

    /**
     * Scenario
     * 1. Get Team with id = 133604
     * 2. Check the id team, team name, team short name
     */
    @Test
    fun getTeamDetail(){
        enqueueResponse("/team_detail.json")
        val idTeam = 133604
        val response =  LiveDataTestUtil.getValue(service.getTeamById(idTeam))
        assertThat(response.body?.teams?.get(0)?.idTeam,`is`(idTeam))
        assertThat(response.body?.teams?.get(0)?.teamName,`is`("Arsenal"))
        assertThat(response.body?.teams?.get(0)?.teamShortName,`is`("Ars"))
    }
}