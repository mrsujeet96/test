package com.example.test.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.R
import com.example.test.databinding.ActivityMainBinding
import com.example.test.ui.adapter.UserListAdapter
import com.example.test.ui.viewmodel.MainViewModel

 class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    var adapter: UserListAdapter? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel.setActivityInstance(this)
        mainViewModel.fetchUserdata()
        setAdapter()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setAdapter() {

        mainViewModel.userList.observe(this) {
            if (it != null) {
                adapter = UserListAdapter(this)
                binding.rvUserList.layoutManager = LinearLayoutManager(this)
                adapter!!.setUserList(it.data)
            }

        }
        binding.swipe.setOnRefreshListener {
            mainViewModel.userList.value = null
            mainViewModel.fetchUserdata()
        }
    }


}