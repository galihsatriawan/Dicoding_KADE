package id.shobrun.footballleague.ui.events.search


import android.app.PendingIntent.getActivity
import android.view.KeyEvent
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import id.shobrun.footballleague.R
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.utils.RecyclerViewMatcher
import id.shobrun.footballleague.utils.ViewModelUtil
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.spy


@RunWith(AndroidJUnit4::class)
class SearchEventsActivityTest {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SearchEventsActivity::class.java)
    private lateinit var viewModel: SearchEventsViewModel
    private var result = MutableLiveData<Resource<List<Event>>>()
    private lateinit var searchEventsActivity: SearchEventsActivity

    @Before
    fun setUp() {
        viewModel = spy(SearchEventsViewModel::class.java)
        searchEventsActivity = mActivityTestRule.activity
        searchEventsActivity.viewModelFactory = ViewModelUtil.createFor(viewModel)
        `when`(viewModel.eventLiveData).thenReturn(result)
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
        onView(listMatcher().atPosition(0)).check(matches(isDisplayed()))

    }

    /**
     * Scenario
     * 1. Input data yang dipastikan tidak akan menemukan suatu event
     * 2. cek apakah container message di tampilkan
     */
    @Test
    fun message(){
        mActivityTestRule.activity.viewModel.postFilter("basketball")
        Thread.sleep(1000)
        onView(withId(R.id.container_message)).check(matches(isDisplayed()))
    }

    private fun listMatcher(): RecyclerViewMatcher{
        return RecyclerViewMatcher(R.id.recycler_search_event)
    }
}