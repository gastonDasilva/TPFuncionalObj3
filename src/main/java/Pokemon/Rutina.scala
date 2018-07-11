

package Pokemon

class Rutina[T <: Actividad](val nombreRutina: String, var rutina: Array[T]) {
  
  /*def aplicarAlPokemon(pok: Pokemon): Pokemon = {
    /*Ejecuta las series de actividades que posee una rutina a un pokemon determinado */
    var resPok :Pokemon = pok;
    rutina.foreach((actividad) => 
      resPok = actividad.aplicarActividad(resPok)
      );
    
    return resPok;
    }*/
  }
