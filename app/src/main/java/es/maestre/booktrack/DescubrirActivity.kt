package es.maestre.booktrack

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import es.maestre.booktrack.adapter.LibroAdapter
import es.maestre.booktrack.databinding.ActivityDescubrirBinding
import es.maestre.booktrack.model.Libro
import es.maestre.booktrack.viewModel.DescubrirViewModel

class DescubrirActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LIBRO: String = "EXTRA_LIBRO"
    }

    private lateinit var binding: ActivityDescubrirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDescubrirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToActivity()
        initRecyclerView()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun initRecyclerView() {
        val manager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewCategorias)

        // llamo a la clase viewModel para obtener los datos
        val viewModel = DescubrirViewModel(application)
        viewModel.cargarTodosLosLibros()

        binding.recyclerViewCategorias.adapter = LibroAdapter(viewModel.cargarTodosLosLibros())
    }

    private fun navigateToActivity() {
        binding.iconHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.iconBooks.setOnClickListener {
            val intent = Intent(this, DescubrirActivity::class.java)
            startActivity(intent)
        }
        binding.iconMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
        binding.iconSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }

}