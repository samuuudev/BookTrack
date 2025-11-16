package es.maestre.booktrack

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels // ¡Importante! para usar by viewModels()
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import es.maestre.booktrack.adapter.LibroAdapter
import es.maestre.booktrack.databinding.ActivityDescubrirBinding

// Importa tu ViewModel
import es.maestre.booktrack.viewModel.DescubrirViewModel

class DescubrirActivity : AppCompatActivity() {

    companion object {
        // Esta clave la usarás para pasar el libro a la pantalla de detalles
        const val EXTRA_LIBRO = "EXTRA_LIBRO"
    }

    private lateinit var binding: ActivityDescubrirBinding

    // 1. INICIALIZA EL VIEWMODEL DE FORMA CORRECTA
    // Usa el delegate "by viewModels()" para que Android gestione el ciclo de vida del ViewModel.
    private val viewModel: DescubrirViewModel by viewModels()

    // 2. DEFINE EL ADAPTER COMO UNA PROPIEDAD
    private lateinit var libroAdapter: LibroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDescubrirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. LLAMA A LA INICIALIZACIÓN DEL RECYCLERVIEW
        initRecyclerView()

        // 4. OBSERVA LOS DATOS DEL VIEWMODEL
        // Este bloque se ejecutará automáticamente cada vez que los datos en la BBDD cambien.
        viewModel.todosLosLibros.observe(this) { libros ->
            // Cuando la lista de libros llega, se la pasamos al adapter.
            // El 'let' es una forma segura de comprobar que la lista no sea nula.
            libros?.let {
                libroAdapter.setData(it)
            }
        }

        // No es necesario llamar a viewModel.cargarTodosLosLibros() si el ViewModel carga los datos en su `init`.

        navigateToActivity()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun initRecyclerView() {
        // Define la acción de clic para el adapter.
        // Cuando un usuario pulse en un libro, se lanzará el código que pongas aquí.
        libroAdapter = LibroAdapter { libro ->
            // TODO: Crear una Activity de detalles (p. ej. DetalleLibroActivity)
            // y navegar a ella pasándole el libro seleccionado.
            // val intent = Intent(this, DetalleLibroActivity::class.java)
            // intent.putExtra(EXTRA_LIBRO, libro) // Usamos la clave definida en el companion object
            // startActivity(intent)
        }

        val manager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Asigna el adapter y el layoutManager al RecyclerView
        binding.recyclerViewCategorias.layoutManager = manager
        binding.recyclerViewCategorias.adapter = libroAdapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewCategorias)
    }

    private fun navigateToActivity() {
        binding.iconHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.iconBooks.setOnClickListener {
            // No hacemos nada, ya estamos en esta pantalla.
        }
        binding.iconMap.setOnClickListener {
            // TODO: Crear e iniciar MapActivity
            // val intent = Intent(this, MapActivity::class.java)
            // startActivity(intent)
        }
        binding.iconSearch.setOnClickListener {
            // TODO: Crear e iniciar SearchActivity
            // val intent = Intent(this, SearchActivity::class.java)
            // startActivity(intent)
        }
    }
}
