package id.shobrun.footballleague.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import id.shobrun.footballleague.R
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject
import javax.inject.Named

class AuthActivity : DaggerAppCompatActivity() {
    companion object{
        val TAG = this.javaClass.name
    }
    @Inject
    lateinit var str : String

    @JvmField
    @field:[Inject Named("isAppNull")]
    var isAppNull : Boolean = true

    @Inject
    lateinit var logo:Drawable

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    lateinit var viewModel : AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(AuthViewModel::class.java)
        Log.d(TAG,str)
        Log.d(TAG,"${isAppNull}")
        setLogo()
    }
    fun setLogo(){
        requestManager.load(logo)
            .into(login_logo)
    }
}
