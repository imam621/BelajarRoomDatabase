package com.example.belajarroomdatabase.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbguru")
data class Guru(
    @ColumnInfo(name = "namagr")var namaguru:String,
    @ColumnInfo(name = "alamat")var alamat:String,
    @ColumnInfo(name = "jekel")var jenis_kelamin:String,
    @ColumnInfo(name = "jbt")var jabatan:String,
    @PrimaryKey @ColumnInfo(name = "nik")var nik:Int=0
)
