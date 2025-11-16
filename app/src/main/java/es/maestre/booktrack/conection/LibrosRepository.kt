package es.maestre.booktrack.conection

import androidx.lifecycle.LiveData
import es.maestre.booktrack.model.Favorito
import es.maestre.booktrack.model.Leido
import es.maestre.booktrack.model.Libro
import es.maestre.booktrack.model.Pendiente

class LibrosRepository(
    private val librosDAO: LibrosDAO,
    private val pendienteDAO: PendienteDAO,
    private val favoritoDAO: FavoritoDAO,
    private val leidoDAO: LeidoDAO
) {

    // --- Métodos para Libros ---
    fun getAllLibros(): LiveData<List<Libro>> = librosDAO.getAllLibros()
    fun getLibroById(id: Long): LiveData<Libro> = librosDAO.getLibroById(id)
    suspend fun countBooks(): Int = librosDAO.countBooks()

    // --- Métodos para Pendientes ---
    fun getLibrosPendientes(): LiveData<List<Libro>> = pendienteDAO.getPendientesWithDetails()
    suspend fun addPendiente(libroId: Long) = pendienteDAO.insert(Pendiente(libroId = libroId))
    suspend fun removePendiente(libroId: Long) = pendienteDAO.deleteByLibroId(libroId)
    suspend fun isPendiente(libroId: Long): Boolean = pendienteDAO.isPending(libroId) > 0

    // --- Métodos para Favoritos ---
    suspend fun addFavorito(libroId: Long) = favoritoDAO.insert(Favorito(libroId = libroId))
    suspend fun removeFavorito(libroId: Long) = favoritoDAO.deleteByLibroId(libroId)
    suspend fun isFavorito(libroId: Long): Boolean = favoritoDAO.isFavorito(libroId) > 0

    // --- Métodos para Leídos ---
    suspend fun addLeido(libroId: Long) = leidoDAO.insert(Leido(libroId = libroId))
    suspend fun removeLeido(libroId: Long) = leidoDAO.deleteByLibroId(libroId)
    suspend fun isLeido(libroId: Long): Boolean = leidoDAO.isLeido(libroId) > 0
}
