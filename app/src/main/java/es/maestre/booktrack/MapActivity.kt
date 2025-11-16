package es.maestre.booktrack

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Intenta abrir una app de mapas genérica con la búsqueda.
        val gmmIntentUri = Uri.parse("geo:0,0?q=librerías")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)

        // Comprueba si hay ALGUNA app que pueda manejar la petición de mapa.
        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        } else {
            // 2. Si no hay app de mapas, abre la búsqueda en el navegador web como plan B.
            val webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=librerías")
            val webIntent = Intent(Intent.ACTION_VIEW, webUri)
            
            // Comprueba si hay un navegador (casi siempre lo habrá).
            if (webIntent.resolveActivity(packageManager) != null) {
                 startActivity(webIntent)
            } else {
                // 3. Caso extremo: no hay ni app de mapas ni navegador.
                Toast.makeText(this, "No se encontró una aplicación para abrir mapas.", Toast.LENGTH_LONG).show()
            }
        }

        // 4. Cierra esta Activity inmediatamente para no dejar una pantalla en blanco.
        finish()
    }
}
