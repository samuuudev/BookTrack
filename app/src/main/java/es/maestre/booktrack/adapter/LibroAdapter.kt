package es.maestre.booktrack.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.maestre.booktrack.R
import es.maestre.booktrack.model.Libro

class LibroAdapter(mutableListOf: Unit) :
    RecyclerView.Adapter<LibrosViewHolder>() {
    private var data: List<Libro> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibrosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LibrosViewHolder(layoutInflater.inflate(R.layout.item_libro_portada, parent, false))
    }

    override fun onBindViewHolder(holder: LibrosViewHolder, position: Int) {
        val libroActual = data[position]

        holder.itemView.setOnClickListener {  }

        val portadaArray = libroActual.imagen
        if (portadaArray != null) {
            val bitmapPortada = BitmapFactory.decodeByteArray(portadaArray, 0, portadaArray.size)
            holder.imagenView.setImageBitmap(bitmapPortada)
        } else {
            holder.imagenView.setImageResource(R.drawable.ic_launcher_background) // Puedes cambiar esto por un ícono de libro
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(newData: List<Libro>) {
        this.data = newData
        notifyItemChanged(0, data.size - 1)
        notifyDataSetChanged()
    }

    fun setData(libros: List<Libro>) {
        data = libros
        notifyDataSetChanged()
    }
}
