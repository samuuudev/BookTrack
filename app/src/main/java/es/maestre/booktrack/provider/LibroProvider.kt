package es.maestre.booktrack.provider

import es.maestre.booktrack.model.Libro

class LibroProvider {
    companion object {
        fun getInitialBooks(): List<Libro> {
            return listOf(
                Libro(
                    titulo = "Cien años de soledad",
                    autor = "Gabriel García Márquez",
                    descripcion = "La novela narra la historia de la familia Buendía a lo largo de siete generaciones en el pueblo ficticio de Macondo.",
                    genero = "Realismo mágico",
                    imagen = null // No puedo descargar imágenes, se deja como null
                ),
                Libro(
                    titulo = "Don Quijote de la Mancha",
                    autor = "Miguel de Cervantes",
                    descripcion = "Narra las aventuras de un hidalgo de la Mancha que, tras leer demasiados libros de caballerías, decide convertirse en caballero andante.",
                    genero = "Novela de caballerías",
                    imagen = null
                ),
                Libro(
                    titulo = "El Señor de los Anillos",
                    autor = "J.R.R. Tolkien",
                    descripcion = "Una novela de fantasía épica que narra el viaje del hobbit Frodo Bolsón para destruir el Anillo Único y derrotar al Señor Oscuro Sauron.",
                    genero = "Fantasía",
                    imagen = null
                ),
                Libro(
                    titulo = "1984",
                    autor = "George Orwell",
                    descripcion = "Una novela distópica sobre una sociedad totalitaria donde el pensamiento y la individualidad son perseguidos por el Partido y su líder, el Gran Hermano.",
                    genero = "Distopía",
                    imagen = null
                ),
                Libro(
                    titulo = "Un mundo feliz",
                    autor = "Aldous Huxley",
                    descripcion = "Imagina una sociedad futura donde la tecnología ha eliminado la pobreza, la guerra y la enfermedad, pero a costa de la libertad, el arte y la familia.",
                    genero = "Ciencia ficción",
                    imagen = null
                )
            )
        }
    }
}
