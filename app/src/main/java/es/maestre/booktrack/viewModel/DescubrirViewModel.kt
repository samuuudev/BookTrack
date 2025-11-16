package es.maestre.booktrack.viewModel

import android.app.Application // Necesario para AndroidViewModel
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import es.maestre.booktrack.conection.AppDatabase // <-- IMPORTA TU CLASE DE BBDD
import es.maestre.booktrack.model.Libro
import kotlinx.coroutines.launch

// Usamos AndroidViewModel para tener acceso al contexto de la aplicación de forma segura
class DescubrirViewModel(application: Application) : AndroidViewModel(application) {

    // LiveData que contendrá la lista de TODOS los libros
    private val _todosLosLibros = MutableLiveData<List<Libro>>()
    val todosLosLibros: LiveData<List<Libro>> get() = _todosLosLibros

    // LiveData para el estado de carga
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    // Instancia del DAO para acceder a la base de datos
    private val libroDao = AppDatabase.getDatabase(application).librosDAO()

    /**
     * Inicia la carga de todos los libros desde la base de datos Room.
     */
    fun cargarTodosLosLibros() {
        viewModelScope.launch {
            _isLoading.value = true
            // Llamamos a la función del DAO para obtener todos los libros
            val libros = libroDao.getAllLibros() // librodao contiene un metodo getAllLibros() que devuelve List<Libro>

            // Actualizamos el LiveData con los resultados.
            _todosLosLibros.postValue(libros as List<Libro>?)

            _isLoading.value = false
        }
    }
}
