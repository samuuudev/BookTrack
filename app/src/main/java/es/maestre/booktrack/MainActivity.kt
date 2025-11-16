package es.maestre.booktrack

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import es.maestre.booktrack.adapter.LibroAdapter
import es.maestre.booktrack.databinding.ActivityMainBinding
import es.maestre.booktrack.model.Libro
import es.maestre.booktrack.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var recomendacionesAdapter: LibroAdapter
    private lateinit var pendientesAdapter: LibroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWindowInsets()
        setupRecyclerViews()
        observeViewModel()
        setupNavigationListeners()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
    }

    private fun setupRecyclerViews() {
        val onBookClick: (Libro) -> Unit = { libro ->
            val intent = Intent(this, DetallesLibroActivity::class.java).apply {
                putExtra(DetallesLibroActivity.EXTRA_LIBRO_ID, libro.id)
            }
            startActivity(intent)
        }

        recomendacionesAdapter = LibroAdapter(onBookClick)
        pendientesAdapter = LibroAdapter(onBookClick)

        binding.rvRecomendaciones.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = recomendacionesAdapter
        }

        binding.rvPendientes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = pendientesAdapter
        }
    }

    private fun observeViewModel() {
        mainViewModel.recomendaciones.observe(this) { libros ->
            libros?.let { recomendacionesAdapter.setData(it) }
        }

        mainViewModel.librosPendientes.observe(this) { libros ->
            libros?.let { pendientesAdapter.setData(it) }
        }
    }

    private fun setupNavigationListeners() {
        binding.iconHome.setOnClickListener { /* Ya estamos en Home */ }
        binding.iconBooks.setOnClickListener { startActivity(Intent(this, DescubrirActivity::class.java)) }
        binding.iconMap.setOnClickListener { startActivity(Intent(this, MapActivity::class.java)) }
        binding.iconSearch.setOnClickListener { startActivity(Intent(this, AboutUsActivity::class.java)) }
    }
}
