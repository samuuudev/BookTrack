package es.maestre.booktrack.conection

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import es.maestre.booktrack.model.Libro

/**
 * DAO para la entidad Libro.
 * Se encarga únicamente de las operaciones relacionadas con la tabla 'libro'.
 */
@Dao
interface LibrosDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(libro: Libro)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(libros: List<Libro>)

    @Query("SELECT * FROM libro ORDER BY titulo ASC")
    fun getAllLibros(): LiveData<List<Libro>>


    @Query("SELECT * FROM libro WHERE id = :id")
    fun getLibroById(id: Long): LiveData<Libro>

    @Query("SELECT * FROM libro WHERE genero = :genero")
    fun getLibrosByGenero(genero: String): LiveData<List<Libro>>


    @Query("SELECT COUNT(id) FROM libro")
    suspend fun countBooks(): Int

    @Update
    suspend fun update(libro: Libro)

    @Delete
    suspend fun delete(libro: Libro)
}
