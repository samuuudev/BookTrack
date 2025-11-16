package es.maestre.booktrack.provider

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import androidx.core.content.ContextCompat
import es.maestre.booktrack.R
import es.maestre.booktrack.conection.LibrosDAO
import es.maestre.booktrack.model.Libro
import java.io.ByteArrayOutputStream

/**
 * Clase dedicada a poblar la base de datos con datos iniciales.
 */
class LibrosProvider(private val context: Context) {

    /**
     * Inserta la lista de libros iniciales en la base de datos.
     */
    suspend fun populateDatabase(librosDAO: LibrosDAO) {
        val initialBooks = listOf(
            Libro(
                titulo = "Cien años de soledad",
                autor = "Gabriel García Márquez",
                descripcion = "La novela narra la historia de la familia Buendía a lo largo de siete generaciones en el pueblo ficticio de Macondo.",
                genero = "Realismo mágico",
                imagen = drawableToByteArray(R.drawable.cien_anos_de_soledad)
            ),
            Libro(
                titulo = "Don Quijote de la Mancha",
                autor = "Miguel de Cervantes",
                descripcion = "Narra las aventuras de un hidalgo de la Mancha que, tras leer demasiados libros de caballerías, decide convertirse en caballero andante.",
                genero = "Novela de caballerías",
                imagen = drawableToByteArray(R.drawable.don_quijote)
            ),
            Libro(titulo = "El Señor de los Anillos", autor = "J.R.R. Tolkien", descripcion = "Una novela de fantasía épica que narra el viaje del hobbit Frodo Bolsón para destruir el Anillo Único.", genero = "Fantasía", imagen = null),
            Libro(titulo = "1984", autor = "George Orwell", descripcion = "Una novela distópica sobre una sociedad totalitaria donde el pensamiento y la individualidad son perseguidos.", genero = "Distopía", imagen = null)
        )
        librosDAO.insertAll(initialBooks)
    }

    /**
     * Convierte un recurso drawable a un ByteArray para guardarlo en la BD.
     */
    private fun drawableToByteArray(drawableId: Int): ByteArray? {
        val drawable = ContextCompat.getDrawable(context, drawableId) ?: return null
        val bitmap: Bitmap = if (drawable is BitmapDrawable) {
            drawable.bitmap
        } else {
            if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) return null
            val bmp = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bmp)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bmp
        }
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        return stream.toByteArray()
    }
}
