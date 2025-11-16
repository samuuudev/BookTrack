package es.maestre.booktrack.conection

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.maestre.booktrack.model.Libro

@Dao
interface LibrosDAO {
    @Insert
    suspend fun insert(libro: Libro)

    @Query("SELECT * FROM libro")
    fun getAllLibros(): LiveData<List<Libro>>

    @Query("SELECT * FROM libro WHERE id_libro = :id")
    fun getLibroById(id: Int): LiveData<Libro>

    @Query("SELECT * FROM libro WHERE genero = :genero")
    fun getLibrosByGenero(genero: String): LiveData<List<Libro>>

    @Update
    suspend fun update(libro: Libro)

    @Delete
    suspend fun delete(libro: Libro)
}