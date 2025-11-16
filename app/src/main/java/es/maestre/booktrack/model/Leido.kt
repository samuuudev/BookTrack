package es.maestre.booktrack.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "leido",
    foreignKeys = [ForeignKey(
        entity = Libro::class,
        parentColumns = ["id"],
        childColumns = ["libroId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["libroId"], unique = true)]
)
data class Leido(
    @PrimaryKey(autoGenerate = true)
    val idLeido: Long = 0,

    @ColumnInfo(name = "libroId")
    val libroId: Long
)
