package com.example.kosassignment.view.activity
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.demoproject.view.baseview.BaseActivity
import com.example.kosassignment.R
import com.example.kosassignment.view.adapter.UserAdapter
import com.example.kosassignment.viewModal.UserViewModelFactory
import com.example.navia.viewModal.HealthDataViewModal

class MainActivity : BaseActivity() {
    lateinit var recyclerView:RecyclerView
    lateinit var helthDataViewModal: HealthDataViewModal
    lateinit var lav_main: LottieAnimationView
    private lateinit var adapter : UserAdapter

    override fun initActivity() {
        recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        lav_main = findViewById(R.id.lav_main) as LottieAnimationView
        initRecyclerView()
        recyclerView.layoutManager = LinearLayoutManager(this)

/*
     //   helthDataViewModal = ViewModelProviders.of(this).get(HealthDataViewModal::class.java)

        helthDataViewModal = AndroidViewModelFactory(application).create(HealthDataViewModal::class.java)

*/

        //  val userViewModel = ViewModelProvider(this, Helth(applicationContext,repository)).get(HealthDataViewModal::class.java)

        helthDataViewModal = ViewModelProvider(this, UserViewModelFactory(applicationContext)).get(HealthDataViewModal::class.java)

        helthDataViewModal.userPagedList.observe(this, Observer {
            lav_main.visibility= View.GONE
            adapter.submitList(it)
        })
        recyclerView.adapter = adapter
    }

    private fun initRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(this)
        adapter = UserAdapter(this)
        recyclerView.adapter = adapter
    }

    override fun getContectView(): Int {
        return R.layout.activity_main }
}