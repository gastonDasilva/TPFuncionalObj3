

package Pokemon

class Gimnasio {
  
  def realizarActividad(activity: Actividad, pokemon: Pokemon): Pokemon ={
    pokemon.estado  match {
      case e : Paralizado => pokemon.copy(estado = new KO());
      
      case e : KO => {
                      println(s"El Pokemon ${ pokemon.nombrePokemon} se encuetra K.O, No puede hacer ninguna actividad");
                      return pokemon
      }
      
      case e : Dormido => {
                          if (e.cantDeLlamados == 3){
                            var res = pokemon.copy(estado = null); 
                            activity.aplicarActividad(res)
                          }else{
                            println(s"El pokemon ${pokemon.nombrePokemon} se encuentra Dormido, lleva ${ (e.cantDeLlamados + 1)} actividades ignoradas")
                            pokemon.copy(estado = new Dormido(e.cantDeLlamados + 1));}
      }
      
      case p => activity.aplicarActividad(pokemon)
    }
      
  }
  
  def realizarRutina[T<: Actividad](rutina: Rutina[T], pok: Pokemon): Pokemon = {
    /**/
    
    var resPok :Pokemon = pok;
    println(s"[ Inicio De La Rutina  ${rutina.nombreRutina} para ${ pok.nombrePokemon} ]" )
    rutina.rutina.foreach((actividad) => {
      println(s" * Realizando la actividad: ${actividad.nombreActividad} * ")
      resPok = this.realizarActividad(actividad, resPok)}//actividad.aplicarActividad(resPok)
      );
    println(s"[ Fin De La Rutina para ${pok.nombrePokemon} ] " )
    return resPok;
    
  }
  
  
  def detectarRutinaMasEficienteParaUnCriterio[T<: Actividad](pok:Pokemon, rutinas: Array[Rutina[T]], criterio : CriteriosDeEleccion): Rutina[T] = {
    /*Detecta la mejor rutina(Si es que existe) para un pokemon dependiendo de un criterio dado */
    var pokemonesAndRutinas: Array[(Rutina[T],Pokemon)] = Array[(Rutina[T],Pokemon)]();
    rutinas.foreach((rutina) => {
      
      var pokRes: Pokemon = this.realizarRutina(rutina, pok)
      var par = (rutina, pokRes);
      pokemonesAndRutinas= pokemonesAndRutinas.:+(par);
    });
    
    criterio.detectarMejorRutina(pokemonesAndRutinas , pok) match {
      case Some(s) => s 
      case None => {println(s"NO Se Ha Encontrado Ninguna Rutina Para El Pokemon ${pok.nombrePokemon} Que Satisfaga El Criterio Dado")
                    null;
                    }
      }
    
    }
    
  }
