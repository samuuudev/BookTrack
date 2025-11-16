package es.maestre.booktrack

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import es.maestre.booktrack.adapter.LibroAdapter
import es.maestre.booktrack.databinding.ActivityDescubrirBinding
import es.maestre.booktrack.viewModel.DescubrirViewModel

class DescubrirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescubrirBinding
    private val viewModel: DescubrirViewModel by viewModels()
    private lateinit var libroAdapter: LibroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityDescubrirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        viewModel.todosLosLibros.observe(this) { libros ->
            libros?.let { libroAdapter.setData(it) }
        }

        navigateToActivity()
        setupWindowInsets()
    }

    private fun initRecyclerView() {
        libroAdapter = LibroAdapter { libro ->
            val intent = Intent(this, DetallesLibroActivity::class.java).apply {
                putExtra(DetallesLibroActivity.EXTRA_LIBRO_ID, libro.id)
            }
            startActivity(intent)
        }

        binding.recyclerViewCategorias.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewCategorias.adapter = libroAdapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewCategorias)
    }

    private fun navigateToActivity() {
        binding.iconHome.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        binding.iconBooks.setOnClickListener { /* Ya estamos aquí */ }
        binding.iconMap.setOnClickListener { startActivity(Intent(this, MapActivity::class.java)) }
        // ¡CORREGIDO! El icono de la lupa ahora lleva a AboutUsActivity
        binding.iconSearch.setOnClickListener { startActivity(Intent(this, AboutUsActivity::class.java)) }
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
