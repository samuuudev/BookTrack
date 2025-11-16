package es.maestre.booktrack.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import es.maestre.booktrack.conection.AppDatabase
import es.maestre.booktrack.model.Libro

class DescubrirViewModel(application: Application) : AndroidViewModel(application) {

    // 1. Expón directamente el LiveData que te da el DAO.
    // Room se encargará de mantener estos datos actualizados automáticamente y de forma eficiente.
    val todosLosLibros: LiveData<List<Libro>>

    init {
        // 2. Obtén la instancia del DAO como hacías antes.
        val libroDao = AppDatabase.getDatabase(application).librosDAO()
        // 3. Asigna el LiveData del DAO a tu variable. No necesitas llamar a ninguna función `suspend`.
        todosLosLibros = libroDao.getAllLibros()
    }
}
