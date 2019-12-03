package id.shobrun.footballleague.ui.events.detail

import android.os.Bundle
import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelActivity
import id.shobrun.footballleague.databinding.ActivityEventDetailBinding
import id.shobrun.footballleague.extensions.simpleToolbarWithHome
import id.shobrun.footballleague.models.entity.Event
import kotlinx.android.synthetic.main.activity_event_detail.*

class DetailEventActivity : ViewModelActivity() {
    private val viewModel by viewModel<DetailEventViewModel>()
    private val binding by binding<ActivityEventDetailBinding>(R.layout.activity_event_detail)
    companion object{
        val TAG = DetailEventActivity.javaClass.name
        const val EXTRA_EVENT = "extra_event"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            lifecycleOwner = this@DetailEventActivity
            vm = viewModel
        }
        var event : Event? = null
        if(intent.getParcelableExtra<Event>(EXTRA_EVENT) !=null ){
            event = intent.getParcelableExtra<Event>(EXTRA_EVENT)
            viewModel.postEventId(event.idEvent)
        }
        simpleToolbarWithHome(toolbar,event?.eventName?:"Detail Event")
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
