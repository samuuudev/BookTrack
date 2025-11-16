package es.maestre.booktrack.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "pendiente",
    foreignKeys = [ForeignKey(
        entity = Libro::class,
        parentColumns = ["id"],
        childColumns = ["libroId"],
        onDelete = ForeignKey.CASCADE
    )],
    // Hacemos que libroId sea único para que un libro solo pueda estar pendiente una vez.
    indices = [Index(value = ["libroId"], unique = true)]
)
data class Pendiente(
    @PrimaryKey(autoGenerate = true)
    val idPendiente: Long = 0,

    @ColumnInfo(name = "libroId")
    val libroId: Long
)
