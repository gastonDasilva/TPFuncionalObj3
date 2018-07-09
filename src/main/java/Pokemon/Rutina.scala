

package Pokemon

class Rutina[T <: Actividad](val nombreRutina: String, var rutina: Array[T]) {
  
  def aplicarAlPokemon(pok: Pokemon): Pokemon = {
    var resPok :Pokemon = pok;
    rutina.foreach((actividad) => 
      resPok = actividad.aplicarActividad(resPok)
      );
    
    return resPok;
    }
  }
