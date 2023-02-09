package com.example.belajarroomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.belajarroomdatabase.adapter.DataSiswaAdapter
import com.example.belajarroomdatabase.database.BelajarRoomDB
import com.example.belajarroomdatabase.database.Siswa
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val db by lazy { BelajarRoomDB(this) }
    private lateinit var dataSiswaAdapter: DataSiswaAdapter

    private lateinit var inputNIS: EditText
    private lateinit var inputNama: EditText
    private lateinit var inputAlamat: EditText
    private lateinit var inputJK: EditText
    private lateinit var simpanData: Button
    private lateinit var dataDetail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputNIS = findViewById(R.id.editNIS)
        inputNama = findViewById(R.id.editNamaLengkap)
        inputAlamat = findViewById(R.id.editAlamat)
        inputJK = findViewById(R.id.editJK)
        simpanData = findViewById(R.id.btnSimpan)
        dataDetail = findViewById(R.id.btnLihatData)
        this.simpanDataSiswa()
        dataDetail.setOnClickListener {
            startActivity(Intent(applicationContext, DataSiswaActivity::class.java))
        }
    }

    private fun simpanDataSiswa(){
        simpanData.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.SiswaDAO().insertDataSiswa(
                    Siswa(
                        inputNIS.text.toString().toInt(),
                        inputNama.text.toString(),
                        inputAlamat.text.toString(),
                        inputJK.text.toString(),
                    )
                )
                //val nilai = db.SiswaDAO().getAllDataSiswa()
                //Log.d("MainActivity", "dbSiswa: $nilai" )
            }
            startActivity(Intent(applicationContext, DataSiswaActivity::class.java))
        }
    }
}