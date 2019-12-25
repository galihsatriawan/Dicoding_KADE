package id.shobrun.footballleague.ui.events.search


import android.view.KeyEvent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import id.shobrun.footballleague.R
import id.shobrun.footballleague.utils.*
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class SearchEventsActivityTest {
    @get:Rule
    var mActivityTestRule = ActivityTestRule(SearchEventsActivity::class.java,true,true)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val executorRule  = TaskExecutorWithIdlingResourceRule()

    @get:Rule
    val countingAppExecutors = CountingAppExecutorsRule()

    @get:Rule
    val dataBindingIdlingResource = DataBindingIdlingResourceRule(mActivityTestRule)

    lateinit var viewModel: SearchEventsViewModel

    @Before
    fun setUp() {
        viewModel = mActivityTestRule.activity.viewModel
        viewModel.repository.appExecutors = countingAppExecutors.appExecutors

        EspressoTestUtil.disableProgressBarAnimations(mActivityTestRule)

    }

    /**
     * Scenario
     * 1. Click Search Action
     * 2. Mengetik keyword
     * 3. Recycler View Menampilkan hasil keyword
     */

    @Test
    fun search(){
        val qry = "city"
        onView(allOf(withId(R.id.action_search), withEffectiveVisibility(Visibility.VISIBLE))).perform(
            click()
        )
        onView(withId(R.id.search_src_text))
            .perform(
                typeText(qry),
                pressKey(KeyEvent.KEYCODE_ENTER)
            )
        onView(listMatcher().atPosition(0)).check(matches(isDisplayed()))
    }

    /**
     * Scenario
     * 1. Input data yang dipastikan tidak akan menemukan suatu event
     * 2. cek apakah container message di tampilkan
     */
    @Test
    fun message(){
        val qry = "kosong"
        onView(allOf(withId(R.id.action_search), withEffectiveVisibility(Visibility.VISIBLE))).perform(
            click()
        )
        onView(withId(R.id.search_src_text))
            .perform(
                typeText(qry),
                pressKey(KeyEvent.KEYCODE_ENTER)
            )
        onView(withId(R.id.container_message)).check(matches(isDisplayed()))
    }

    private fun listMatcher(): RecyclerViewMatcher{
        return RecyclerViewMatcher(R.id.recycler_search_event)
    }
    @After
    fun tearDown(){

    }

}