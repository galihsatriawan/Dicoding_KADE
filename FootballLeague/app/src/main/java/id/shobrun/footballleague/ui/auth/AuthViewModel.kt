package id.shobrun.footballleague.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject


class AuthViewModel @Inject constructor() : ViewModel(){
    companion object{
        val TAG = this.javaClass.name
    }
    init {
        Log.d(TAG,"work")
//        if(authApi == null) Log.d(TAG,"api is null")
//        else Log.d(TAG,"api is not null")
    }
}