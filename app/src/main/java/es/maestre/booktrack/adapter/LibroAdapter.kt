package es.maestre.booktrack.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.maestre.booktrack.R
import es.maestre.booktrack.model.Libro

class LibroAdapter(private val onLibroClick: (Libro) -> Unit) :
    RecyclerView.Adapter<LibrosViewHolder>() {

    private var data: List<Libro> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibrosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LibrosViewHolder(layoutInflater.inflate(R.layout.item_libro_portada, parent, false))
    }

    override fun onBindViewHolder(holder: LibrosViewHolder, position: Int) {
        val libroActual = data[position]

        holder.itemView.setOnClickListener { onLibroClick(libroActual) }

        val portadaArray = libroActual.imagen
        
        // 1. Intentamos decodificar el ByteArray
        val bitmapPortada = if (portadaArray != null && portadaArray.isNotEmpty()) {
            BitmapFactory.decodeByteArray(portadaArray, 0, portadaArray.size)
        } else {
            null
        }

        // 2. Si el Bitmap es válido, lo mostramos. Si no, ponemos el placeholder.
        if (bitmapPortada != null) {
            holder.imagenView.setImageBitmap(bitmapPortada)
        } else {
            holder.imagenView.setImageResource(R.drawable.ic_launcher_background)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(libros: List<Libro>) {
        data = libros
        notifyDataSetChanged()
    }
}
