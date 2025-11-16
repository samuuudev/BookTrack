package es.maestre.booktrack

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.maestre.booktrack.databinding.ActivityAboutUsBinding

class AboutUsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ¡CORREGIDO! Usamos binding.root para referirnos a la vista principal.
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Añadimos los listeners para la barra de navegación
        setupNavigationListeners()
    }

    private fun setupNavigationListeners() {
        // Asumimos que los IDs en tu layout son icon_home, icon_books, icon_map, icon_search
        // Si no lo son, este código necesitará ser ajustado.
        // Por ahora, lo comentamos para evitar un crash si los iconos no están en este layout.
        /*
        binding.iconHome.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        binding.iconBooks.setOnClickListener { startActivity(Intent(this, DescubrirActivity::class.java)) }
        binding.iconMap.setOnClickListener { startActivity(Intent(this, MapActivity::class.java)) }
        binding.iconSearch.setOnClickListener { /* Ya estamos aquí */ }
        */
    }
}
