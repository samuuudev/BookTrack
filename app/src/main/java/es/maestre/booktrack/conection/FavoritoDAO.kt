package es.maestre.booktrack.conection

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.maestre.booktrack.model.Favorito

@Dao
interface FavoritoDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favorito: Favorito)

    @Query("DELETE FROM favorito WHERE libroId = :libroId")
    suspend fun deleteByLibroId(libroId: Long)

    @Query("SELECT COUNT(*) FROM favorito WHERE libroId = :libroId")
    suspend fun isFavorito(libroId: Long): Int
}
