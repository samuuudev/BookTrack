package es.maestre.booktrack.conection

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.maestre.booktrack.model.Libro
import es.maestre.booktrack.model.Pendiente

@Dao
interface PendienteDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pendiente: Pendiente)

    @Query("DELETE FROM pendiente WHERE libroId = :libroId")
    suspend fun deleteByLibroId(libroId: Long)

    @Query("SELECT COUNT(*) FROM pendiente WHERE libroId = :libroId")
    suspend fun isPending(libroId: Long): Int

    @Query("SELECT * FROM pendiente")
    fun getAll(): LiveData<List<Pendiente>>

    @Query("SELECT l.* FROM libro l INNER JOIN pendiente p ON l.id = p.libroId")
    fun getPendientesWithDetails(): LiveData<List<Libro>>
}
