package es.maestre.booktrack.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import es.maestre.booktrack.conection.AppDatabase
import es.maestre.booktrack.conection.LibrosRepository
import es.maestre.booktrack.model.Libro
import es.maestre.booktrack.provider.LibrosProvider
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LibrosRepository

    val recomendaciones: LiveData<List<Libro>>
    val librosPendientes: LiveData<List<Libro>>

    init {

        val db = AppDatabase.getDatabase(application)
        repository = LibrosRepository(db.librosDAO(), db.pendienteDAO(), db.favoritoDAO(), db.leidoDAO())


        viewModelScope.launch {
            if (repository.countBooks() == 0) {
                val provider = LibrosProvider(getApplication())
                provider.populateDatabase(db.librosDAO())
            }
        }


        recomendaciones = repository.getAllLibros().map { it.shuffled().take(2) }
        librosPendientes = repository.getLibrosPendientes()
    }
}
