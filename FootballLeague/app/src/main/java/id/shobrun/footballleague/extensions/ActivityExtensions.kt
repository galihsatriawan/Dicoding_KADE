package id.shobrun.footballleague.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import id.shobrun.footballleague.R

fun AppCompatActivity.simpleToolbarWithHome(toolbar: Toolbar, title_: String = "") {
    setSupportActionBar(toolbar)
    supportActionBar?.run {
        setDisplayHomeAsUpEnabled(true)
        setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        title = title_
    }
}