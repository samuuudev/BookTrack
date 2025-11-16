package es.maestre.booktrack

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.maestre.booktrack.databinding.ActivityDetallesLibroBinding
import es.maestre.booktrack.model.Libro
import es.maestre.booktrack.viewModel.DetallesLibroViewModel

class DetallesLibroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesLibroBinding
    private val viewModel: DetallesLibroViewModel by viewModels()
    private var libroId: Long = -1L

    companion object {
        const val EXTRA_LIBRO_ID = "es.maestre.booktrack.LIBRO_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetallesLibroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        libroId = intent.getLongExtra(EXTRA_LIBRO_ID, -1L)

        if (libroId == -1L) {
            finish()
            return
        }

        setupWindowInsets()
        setupNavigationListeners()
        setupButtonClickListeners()
        observeViewModel()

        // Cargamos el estado inicial de todos los botones
        viewModel.loadAllStatus(libroId)
    }

    private fun observeViewModel() {
        viewModel.getLibroById(libroId).observe(this) { libro ->
            libro?.let { updateBookDetailsUI(it) }
        }

        viewModel.isPendiente.observe(this) { updatePendienteButton(it) }
        viewModel.isFavorito.observe(this) { updateFavoritoButton(it) }
        viewModel.isLeido.observe(this) { updateLeidoButton(it) }
    }

    private fun setupButtonClickListeners() {
        binding.btnAAdirPendientes.setOnClickListener { viewModel.togglePendienteStatus(libroId) }
        binding.btnValorar.setOnClickListener { viewModel.toggleFavoritoStatus(libroId) }
        binding.btnMarcarLeido.setOnClickListener { viewModel.toggleLeidoStatus(libroId) }
    }

    private fun updateBookDetailsUI(libro: Libro) {
        binding.txtTituloLibro.text = libro.titulo
        binding.txtDescripcion.text = libro.descripcion
        libro.imagen?.let { byteArray ->
            if (byteArray.isNotEmpty()) {
                binding.imagenPortada.setImageBitmap(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size))
            }
        } ?: binding.imagenPortada.setImageResource(R.drawable.ic_launcher_background)
    }

    // --- Métodos para actualizar la UI de los botones ---

    private fun updatePendienteButton(isPendiente: Boolean) {
        val text = if (isPendiente) "Quitar de Pendientes" else "Añadir a Pendientes"
        binding.btnAAdirPendientes.text = text
    }

    private fun updateFavoritoButton(isFavorito: Boolean) {
        val text = if (isFavorito) "Quitar Favorito" else "Marcar Favorito"
        binding.btnValorar.text = text
    }

    private fun updateLeidoButton(isLeido: Boolean) {
        val text = if (isLeido) "Marcar como NO Leído" else "Marcar como Leído"
        binding.btnMarcarLeido.text = text
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupNavigationListeners() {
        binding.iconHome.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        binding.iconBooks.setOnClickListener { startActivity(Intent(this, DescubrirActivity::class.java)) }
        binding.iconMap.setOnClickListener { startActivity(Intent(this, MapActivity::class.java)) }
        binding.iconSearch.setOnClickListener { startActivity(Intent(this, AboutUsActivity::class.java)) }
    }
}
