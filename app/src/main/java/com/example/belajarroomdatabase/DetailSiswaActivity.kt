package com.example.belajarroomdatabase

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.belajarroomdatabase.database.BelajarRoomDB
import com.example.belajarroomdatabase.database.Siswa
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailSiswaActivity : AppCompatActivity() {

    private val db by lazy { BelajarRoomDB(this) }
    private var siswaID: Int = 0

    private lateinit var detailNIS: TextView
    private lateinit var detailNama: TextView
    private lateinit var detailAlamat: TextView
    private lateinit var detailJK: TextView
    private lateinit var hapusData: Button
    private lateinit var ubahData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_siswa2)
        detailNIS = findViewById(R.id.detailNIS)
        detailNama = findViewById(R.id.detailNama)
        detailAlamat = findViewById(R.id.detailAlamat)
        detailJK = findViewById(R.id.detailJK)
        hapusData = findViewById(R.id.btnHapusData)
        ubahData = findViewById(R.id.btnUbahData)
        this.setDataSiswa()
        hapusData.setOnClickListener {
            this.hapusDataSiswa()
        }
        ubahData.setOnClickListener {
            val build = AlertDialog.Builder(this)
            build.setTitle("Ubah Data Siswa")
            val nis = TextView(this)
            val editNama = EditText(this)
            val editAlamat = EditText(this)
            val editJK = EditText(this)
            val linearLayout = LinearLayout(this)
            linearLayout.orientation = LinearLayout.VERTICAL
            linearLayout.addView(nis)
            linearLayout.addView(editNama)
            linearLayout.addView(editAlamat)
            linearLayout.addView(editJK)
            linearLayout.setPadding(30, 10, 30, 10)
            build.setView(linearLayout)
            build.setPositiveButton("Ubah"){_dialogInterface, _i ->
                val nama = editNama.text.toString()
                val alamat = editAlamat.text.toString()
                val jenisKelamin = editJK.text.toString()
            }
            build.setNeutralButton("Batal"){dialogInterface, i ->
                dialogInterface.dismiss()
            }
            build.create().show()
        }
    }

    private fun setDataSiswa(){
        siswaID = intent.getIntExtra("nisID", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.SiswaDAO().getNISSiswa(siswaID)[0]
            detailNIS.setText(data.nis.toString())
            detailNama.setText(data.namasiswa)
            detailAlamat.setText(data.alamat)
            detailJK.setText(data.jenis_kelamin)
        }
    }

    private fun hapusDataSiswa(){
        CoroutineScope(Dispatchers.IO).launch {
            db.SiswaDAO().deleteDataSiswa(
                Siswa(
                    detailNIS.text.toString().toInt(),
                    detailNama.text.toString(),
                    detailAlamat.text.toString(),
                    detailJK.text.toString()
                )
            )
        }
    }

    private fun editDataSiswa(){
        CoroutineScope(Dispatchers.IO).launch {
            db.SiswaDAO().updateDataSiswa(
                Siswa(
                    detailNIS.text.toString().toInt(),
                    detailNama.text.toString(),
                    detailAlamat.text.toString(),
                    detailJK.text.toString()
                )
            )
        }
    }
}