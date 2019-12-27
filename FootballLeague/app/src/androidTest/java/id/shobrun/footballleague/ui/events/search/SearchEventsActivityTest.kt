package id.shobrun.footballleague.ui.events.search


import android.view.KeyEvent
import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import id.shobrun.footballleague.R
import id.shobrun.footballleague.utils.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
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
    var testRule = CountingTaskExecutorRule()

    @get:Rule
    val dataBindingIdlingResource = DataBindingIdlingResourceRule(mActivityTestRule)

    lateinit var viewModel: SearchEventsViewModel

    @Before
    fun setUp() {
        viewModel = mActivityTestRule.activity.viewModel
        viewModel.repository.appExecutors = countingAppExecutors.appExecutors
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
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
        val qry = "porto"
        onView(allOf(withId(R.id.action_search), withEffectiveVisibility(Visibility.VISIBLE))).perform(
            click()
        )
        onView(withId(R.id.search_src_text))
            .perform(
                typeText(qry),
                pressKey(KeyEvent.KEYCODE_ENTER)
            )
        val recyclerView = mActivityTestRule.activity.findViewById<RecyclerView>(R.id.recycler_search_event)
        waitForAdapterChange(recyclerView)
        onView(listMatcher().atPosition(0)).check(matches(isDisplayed()))
    }

    /**
     * Scenario
     * 1. Input data yang dipastikan tidak akan menemukan suatu event
     * 2. cek apakah container message di tampilkan
     */
    @Test
    fun message(){
        val qry = "sip kosong"
        onView(allOf(withId(R.id.action_search), withEffectiveVisibility(Visibility.VISIBLE))).perform(
            click()
        )
        onView(withId(R.id.search_src_text))
            .perform(
                typeText(qry),
                pressKey(KeyEvent.KEYCODE_ENTER)
            )
        val recyclerView = mActivityTestRule.activity.findViewById<RecyclerView>(R.id.recycler_search_event)
        waitForAdapterChange(recyclerView)
        onView(withId(R.id.container_message)).check(matches(isDisplayed()))
    }

    private fun listMatcher(): RecyclerViewMatcher{
        return RecyclerViewMatcher(R.id.recycler_search_event)
    }
    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }
    private fun waitForAdapterChange(recyclerView: RecyclerView) {
        val latch = CountDownLatch(1)
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            recyclerView.adapter?.registerAdapterDataObserver(
                object : RecyclerView.AdapterDataObserver() {
                    override fun onChanged() {
                        latch.countDown()
                    }

                    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                        latch.countDown()
                    }
                })
        }
        testRule.drainTasks(1, TimeUnit.SECONDS)
        if (recyclerView.adapter?.itemCount ?: 0 > 0) {
            return
        }
        assertThat(latch.await(10, TimeUnit.SECONDS), `is`(true))
    }

}