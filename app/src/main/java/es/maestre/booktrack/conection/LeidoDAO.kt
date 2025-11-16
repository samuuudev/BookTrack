package es.maestre.booktrack.conection

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.maestre.booktrack.model.Leido

@Dao
interface LeidoDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(leido: Leido)

    @Query("DELETE FROM leido WHERE libroId = :libroId")
    suspend fun deleteByLibroId(libroId: Long)

    @Query("SELECT COUNT(*) FROM leido WHERE libroId = :libroId")
    suspend fun isLeido(libroId: Long): Int
}
