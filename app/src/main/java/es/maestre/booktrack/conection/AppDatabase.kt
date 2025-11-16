package es.maestre.booktrack.conection

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import es.maestre.booktrack.model.Libro
import es.maestre.booktrack.provider.LibroProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(version = 1, entities = [Libro::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun librosDAO(): LibrosDAO

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "libros.db3"
                )
                .addCallback(AppDatabaseCallback()) // Añadimos el callback aquí
                .build()
                INSTANCE = instance
                instance
            }
        }

        private class AppDatabaseCallback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(database.librosDAO())
                    }
                }
            }

            suspend fun populateDatabase(librosDAO: LibrosDAO) {
                // Obtenemos la lista de libros de nuestro provider
                val initialBooks = LibroProvider.getInitialBooks()
                // Insertamos cada libro en la base de datos
                initialBooks.forEach {
                    librosDAO.insert(it)
                }
            }
        }
    }
}
