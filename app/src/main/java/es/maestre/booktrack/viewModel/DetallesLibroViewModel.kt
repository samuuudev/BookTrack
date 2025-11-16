package es.maestre.booktrack.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import es.maestre.booktrack.conection.AppDatabase
import es.maestre.booktrack.conection.LibrosRepository
import es.maestre.booktrack.model.Libro
import kotlinx.coroutines.launch

class DetallesLibroViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LibrosRepository

    // LiveData para observar los estados
    private val _isPendiente = MutableLiveData<Boolean>()
    val isPendiente: LiveData<Boolean> get() = _isPendiente

    private val _isFavorito = MutableLiveData<Boolean>()
    val isFavorito: LiveData<Boolean> get() = _isFavorito

    private val _isLeido = MutableLiveData<Boolean>()
    val isLeido: LiveData<Boolean> get() = _isLeido

    init {
        val db = AppDatabase.getDatabase(application)
        repository = LibrosRepository(db.librosDAO(), db.pendienteDAO(), db.favoritoDAO(), db.leidoDAO())
    }

    fun getLibroById(id: Long): LiveData<Libro> {
        return repository.getLibroById(id)
    }

    /**
     * Carga el estado inicial de los tres botones para un libro.
     */
    fun loadAllStatus(libroId: Long) {
        viewModelScope.launch {
            _isPendiente.value = repository.isPendiente(libroId)
            _isFavorito.value = repository.isFavorito(libroId)
            _isLeido.value = repository.isLeido(libroId)
        }
    }

    // --- Métodos Toggle para cambiar el estado ---

    fun togglePendienteStatus(libroId: Long) {
        viewModelScope.launch {
            val currentState = _isPendiente.value ?: false
            if (currentState) repository.removePendiente(libroId) else repository.addPendiente(libroId)
            _isPendiente.value = !currentState
        }
    }

    fun toggleFavoritoStatus(libroId: Long) {
        viewModelScope.launch {
            val currentState = _isFavorito.value ?: false
            if (currentState) repository.removeFavorito(libroId) else repository.addFavorito(libroId)
            _isFavorito.value = !currentState
        }
    }

    fun toggleLeidoStatus(libroId: Long) {
        viewModelScope.launch {
            val currentState = _isLeido.value ?: false
            if (currentState) repository.removeLeido(libroId) else repository.addLeido(libroId)
            _isLeido.value = !currentState
        }
    }
}
