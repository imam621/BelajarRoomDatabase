package com.example.belajarroomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.belajarroomdatabase.adapter.DataSiswaAdapter
import com.example.belajarroomdatabase.database.BelajarRoomDB
import com.example.belajarroomdatabase.database.Siswa
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataSiswaActivity : AppCompatActivity() {

    private val db by lazy { BelajarRoomDB(this) }
    private lateinit var dataSiswaAdapter: DataSiswaAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_siswa)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        recyclerView = findViewById(R.id.listDataSiswa)
        dataSiswaAdapter = DataSiswaAdapter(arrayListOf(), object : DataSiswaAdapter.onAdapterListener{
            override fun onClick(siswa: Siswa) {
                startActivity(
                    Intent(
                        applicationContext, DetailSiswaActivity::class.java
                    ).putExtra("nisID", siswa.nis)
                )
            }

        })
        recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = dataSiswaAdapter
        }
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.SiswaDAO().getAllDataSiswa()
            withContext(Dispatchers.Main){
                dataSiswaAdapter.setData(data)
            }
        }
    }
}