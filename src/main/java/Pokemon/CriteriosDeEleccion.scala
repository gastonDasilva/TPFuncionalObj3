

package Pokemon

import com.sun.org.apache.xalan.internal.xsltc.compiler.ForEach

trait CriteriosDeEleccion {
  def detectarMejorRutina[T<: Actividad](pokemonesYRu: Array[(Rutina[T], Pokemon)],pok: Pokemon): Option[Rutina[T]]
  
  def mejorRutina[T<: Actividad](pokemonesYRu : Array[(Rutina[T], Pokemon)],pok: Pokemon, i: Int):Option[Rutina[T]] = {
    var bestEleccion : (Rutina[T],Pokemon) = pokemonesYRu.head
    
    pokemonesYRu.foreach(p =>{
                             if (this.determinarCondicionDeBusqueda(p._2, bestEleccion._2, i, ">")){
                                bestEleccion = p;
                                  }
                          })
    
    if(this.determinarCondicionDeBusqueda(bestEleccion._2, pok, i, "==")){
      new Error("NO SE ENCOTRO RUTINA QUE SATISFAGA DICHO CRITERIO")
      None
    }
    else{
        return new Some[Rutina[T]](bestEleccion._1)
        }
    
    
  }
  
  def determinarCondicionDeBusqueda(pok1:Pokemon, pok2: Pokemon, i: Int, operador: String):Boolean = {
    
    //Machea segun el tipo de i que vas a utilizar
    // * el 1 es para la condicion de la rutina que genera mas experiencia
    // * El 2 es para la rutina que genere mas energia
    // * El 3 es para la rutina que deja al pokemon menos pesado
    
    /* Si se quiere agregar otro critero para la eleccion de la rutina solo es necesario poner un nuevo caso*/
    i  match {
      case 1 =>if(operador == ">"){ pok1.experiencia > pok2.experiencia}
                else{pok1.experiencia == pok2.experiencia}
      
      case 2 => if(operador == ">"){pok1.energia > pok2.energia}
                else{pok1.energia == pok2.energia}
      
      case 3 => if(operador == ">"){pok1.velocidad > pok2.velocidad}
                else{pok1.velocidad == pok2.velocidad}
      
      case n => false
    }
  }
  
}

class CriterioDeSubirMasNivel()extends CriteriosDeEleccion{
  
  def detectarMejorRutina[T<: Actividad](pokemonesYRu : Array[(Rutina[T], Pokemon)],pok: Pokemon):Option[Rutina[T]] = {
    this.mejorRutina(pokemonesYRu, pok, 1)
  }
}

class CriterioDeMayorCantidadDeEnergia()extends CriteriosDeEleccion{
  def detectarMejorRutina[T<: Actividad](pokemonesYRu : Array[(Rutina[T], Pokemon)],pok: Pokemon): Option[Rutina[T]] = {
      this.mejorRutina(pokemonesYRu, pok, 2)
  }
}
  
  class CriterioDeMenosPesado()extends CriteriosDeEleccion{
    
    def detectarMejorRutina[T<: Actividad](pokemonesYRu : Array[(Rutina[T], Pokemon)],pok: Pokemon): Option[Rutina[T]] = {
        this.mejorRutina(pokemonesYRu, pok, 3)
      }
  }
    
