package id.shobrun.footballleague.ui.events.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelActivity
import id.shobrun.footballleague.databinding.ActivityEventDetailBinding
import id.shobrun.footballleague.extensions.simpleToolbarWithHome
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.room.AppDatabase
import kotlinx.android.synthetic.main.activity_event_detail.*
import org.jetbrains.anko.design.snackbar
import timber.log.Timber

class DetailEventActivity : ViewModelActivity() {
    private var menuItem: Menu? = null


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
        favoriteState()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_detail_league,menu)
        menuItem = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.add_to_favorite ->{
                viewModel.onClickedFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    private fun favoriteState(){
        viewModel.isFavorite.observe(this, Observer {
            if(it!=null) runOnUiThread{
                setFavorite(it)

            }
        })
    }

    private fun setFavorite(state : Boolean){
        if(state){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,R.drawable.ic_added_to_favorite)
        }

        else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,R.drawable.ic_add_to_favorite)
        }

    }
}
