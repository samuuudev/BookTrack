package es.maestre.booktrack.conection

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import es.maestre.booktrack.model.Favorito
import es.maestre.booktrack.model.Leido
import es.maestre.booktrack.model.Libro
import es.maestre.booktrack.model.Pendiente
import es.maestre.booktrack.provider.LibrosProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// 1. Añadimos las nuevas entidades y subimos la versión a 10
@Database(entities = [Libro::class, Pendiente::class, Favorito::class, Leido::class], version = 10)
abstract class AppDatabase : RoomDatabase() {

    // 2. Declaramos todos los DAOs
    abstract fun librosDAO(): LibrosDAO
    abstract fun pendienteDAO(): PendienteDAO
    abstract fun favoritoDAO(): FavoritoDAO
    abstract fun leidoDAO(): LeidoDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "libros.db3"
                )
                .fallbackToDestructiveMigration()
                .addCallback(AppDatabaseCallback(context.applicationContext))
                .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(Dispatchers.IO).launch {
                INSTANCE?.let { database ->
                    val provider = LibrosProvider(context)
                    provider.populateDatabase(database.librosDAO())
                }
            }
        }
    }
}
