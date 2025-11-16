package es.maestre.booktrack.conection

import androidx.lifecycle.LiveData
import es.maestre.booktrack.model.Libro

class LibrosRepository(private val librosDAO: LibrosDAO) {

    fun getAllLibros(): LiveData<List<Libro>> {
        return librosDAO.getAllLibros()
    }
    suspend fun insert(libro: Libro) {
        librosDAO.insert(libro)
    }

    suspend fun update(libro: Libro) {
        librosDAO.update(libro)
    }

    suspend fun delete(libro: Libro) {
        librosDAO.delete(libro)
    }

    fun getLibroById(id: Int): LiveData<Libro> {
        return librosDAO.getLibroById(id)
    }

    fun getLibrosByGenero(genero: String): LiveData<List<Libro>> {
        return librosDAO.getLibrosByGenero(genero)
    }
}