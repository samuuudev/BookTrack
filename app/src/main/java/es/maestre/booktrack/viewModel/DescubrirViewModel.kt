package es.maestre.booktrack.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import es.maestre.booktrack.conection.AppDatabase
import es.maestre.booktrack.conection.LibrosRepository
import es.maestre.booktrack.model.Libro

class DescubrirViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LibrosRepository
    val todosLosLibros: LiveData<List<Libro>>

    init {
        val db = AppDatabase.getDatabase(application)
        val librosDAO = db.librosDAO()
        val pendienteDAO = db.pendienteDAO()
        val favoritoDAO = db.favoritoDAO()
        val leidoDAO = db.leidoDAO()

        repository = LibrosRepository(librosDAO, pendienteDAO, favoritoDAO, leidoDAO)

        todosLosLibros = repository.getAllLibros()
    }
}
