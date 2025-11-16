package es.maestre.booktrack.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "libro")
data class Libro(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "titulo")
    var titulo: String,

    @ColumnInfo(name = "autor")
    var autor: String,

    @ColumnInfo(name = "descripcion")
    var descripcion: String,

    @ColumnInfo(name = "genero")
    var genero: String,

    @ColumnInfo(name = "imagen")
    var imagen: ByteArray?
) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Libro

        if (id != other.id) return false
        if (titulo != other.titulo) return false
        if (autor != other.autor) return false
        if (descripcion != other.descripcion) return false
        if (genero != other.genero) return false
        if (imagen != null) {
            if (other.imagen == null) return false
            if (!imagen.contentEquals(other.imagen)) return false
        } else if (other.imagen != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + titulo.hashCode()
        result = 31 * result + autor.hashCode()
        result = 31 * result + descripcion.hashCode()
        result = 31 * result + genero.hashCode()
        result = 31 * result + (imagen?.contentHashCode() ?: 0)
        return result
    }
}
