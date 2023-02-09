package com.example.belajarroomdatabase.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface SiswaDAO {

    // menambah data
    @Insert(onConflict = REPLACE)
    suspend fun insertDataSiswa(Siswa: Siswa)

    // hapus data
    @Delete
    suspend fun deleteDataSiswa(Siswa: Siswa)

    // update data
    @Update
    suspend fun updateDataSiswa(Siswa: Siswa)

    // Menampilkan Data
    @Query("SELECT * FROM tbsiswa")
    suspend fun getAllDataSiswa(): List<Siswa>

    @Query("SELECT * FROM tbsiswa WHERE nis =:nis")
    suspend fun getNISSiswa(nis: Int): List<Siswa>

}