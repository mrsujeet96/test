package com.example.test.ui.viewmodel

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.respository.ApiServices
import com.example.test.respository.RetrofitHelper
import com.example.test.ui.MainActivity
import com.example.test.ui.model.UserResponseModel
import com.example.test.utils.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    var activity: MainActivity? = null

    private var apiServices: ApiServices? = null
    var userList: MutableLiveData<UserResponseModel> = MutableLiveData()

    init {
        apiServices = RetrofitHelper.getRetrofit().create(ApiServices::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun fetchUserdata() {
        if (NetworkUtil.getInstance(activity!!).isOnline()) {
            viewModelScope.launch {
                withContext(Dispatchers.Main) {
                    val response = apiServices?.getUserList(1)
                    if (response?.isSuccessful == true && response.body() != null) {
                        userList.postValue(response.body())
                    } else {
                        Toast.makeText(activity!!, "Something went wrong", Toast.LENGTH_LONG)

                    }

                }

            }
        } else {
            Toast.makeText(activity!!, "Check you inter connection!!", Toast.LENGTH_LONG)

        }


    }

    fun setActivityInstance(activity: MainActivity) {
        this.activity = activity
    }
}