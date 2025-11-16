package es.maestre.booktrack.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import es.maestre.booktrack.databinding.ItemLibroPortadaBinding

class LibrosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemLibroPortadaBinding.bind(itemView)
    val imagenView: ImageView = binding.idImagenView
}