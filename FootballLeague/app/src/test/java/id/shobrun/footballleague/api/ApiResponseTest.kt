package id.shobrun.footballleague.api

import org.junit.Assert.*


import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class ApiResponseTest {

    /**
     * Exception (When error happen)
     * Scenario :
     * 1. ApiResponse is not successful
     * 2. Null body value
     * 3. Code 500
     * 4. Message is like what is given
     */
    @Test
    fun exception() {
        val exception = Exception("foo")
        val apiResponse = ApiResponse<String>(exception)
        assertThat(apiResponse.isSuccessful, `is`(false))
        assertThat(apiResponse.body,CoreMatchers.nullValue())
        assertThat(apiResponse.code, `is`(500))
        assertThat(apiResponse.message, `is`("foo"))
    }
    /**
     * Success (When success get data)
     * 1. ApiResponse Is Successful
     * 2. Code 200
     * 3. The Body is like what is given
     * 4. Message is null
     */
    @Test
    fun success() {
        val apiResponse = ApiResponse(Response.success("foo"))
        assertThat(apiResponse.isSuccessful, `is`(true))
        assertThat(apiResponse.code, `is`(200))
        assertThat(apiResponse.body, `is`("foo"))
        assertThat(apiResponse.message, CoreMatchers.nullValue())
    }
}