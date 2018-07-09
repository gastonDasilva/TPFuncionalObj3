

package Pokemon

import java.io.Console

abstract class Actividad(val nombreActividad: String) {
  def aplicarActividad(pok: Pokemon): Pokemon
}

class ActividadDescansar(unNombreActividadDesc: String = "Descansar")
extends Actividad(unNombreActividadDesc) {
  
  def aplicarActividad(pok: Pokemon): Pokemon = {
    if(pok.estado == null && pok.energia < (pok.energiaMaxima/2)  ){
        pok.copy(energia = pok.energiaMaxima,
                 estado = new Dormido());
    }else {
      pok.copy(energia = pok.energiaMaxima)
    }
  }
  
}

class ActividadLevantarPesas(var cantDeKilos: Int,unNombreActividad: String = "Levantar Pesas")extends Actividad(unNombreActividad) {
  
   def aplicarActividad(pok: Pokemon): Pokemon = {
    // var maximoParaLevantar = 10 * pok.fuerza
     //var kilosPorFuerzaLevantos = this.cantDeKilos * pok.fuerza
     var res = this.cantDeKilos / pok.fuerza
     if(res > 10){
       pok.copy(
           energia = (pok.energia - 10).max(0),
           estado = new Paralizado())
     }else{
       pok.especie.tipoPrincipal  match {
         case "Pelea" => {
                          println(s" ${pok.nombrePokemon} Sube ${this.cantDeKilos * 2} Puntos De Experiencia");
                           pok.copy(experiencia = (pok.experiencia + cantDeKilos)*2)
                          //res.especie.evolucionarSiPuede(res)
                           }
         case "Fantasma"=> {
                           println(s"El Pokemon ${pok.nombrePokemon } No Puede Levantar Pesas Ya Que es de Tipo ${pok.especie.tipoPrincipal}")
                           return pok
                           }
         case p: String => {
                    println(s"${pok.nombrePokemon} Sube ${this.cantDeKilos} Puntos De Experiencia");
                    pok.copy(experiencia = pok.experiencia + this.cantDeKilos);
                    //res.especie.evolucionarSiPuede(res)
                    }
       }
       
     }
    
  }
}

class ActividadNadar( var cantMinutos: Int,unNombreActividadNad: String = "Nadar")
extends Actividad(unNombreActividadNad) {
  
  def aplicarActividad(pok: Pokemon): Pokemon = {
    val pokRes = pok.copy(
                  energia = (pok.energia - this.cantMinutos).max(0),
                  experiencia = pok.experiencia + (200 * this.cantMinutos ));
    
    val cantPuntVelocidad = this.cantMinutos / 60
    pok.especie.tipoPrincipal  match {
      case "Agua" =>  pokRes.copy(velocidad = (pokRes.velocidad + cantPuntVelocidad).min(100));
      
      case "Roca"=> {
                    println(s"El Pokemon ${pok.nombrePokemon } Pierde Contra el Agua por lo tanto queda K.O");  
                    return pok.copy(
                        energia = 0,
                        estado = new KO());}
      case "Fuego"=> {
                    println(s"El Pokemon  ${pok.nombrePokemon } Pierde Contra el Agua por lo tanto queda K.O");  
                    return pok.copy(
                        energia = 0,
                        estado = new KO());}
      case "Tierra"=> {
                    println(s"El Pokemon  ${pok.nombrePokemon } Pierde Contra el Agua por lo tanto queda K.O");  
                    return pok.copy(
                        energia = 0,
                        estado = new KO());}
                 
      case p => pokRes ;              
    };
  }
}
  

  class ActividadIntercambiar(unNombreActividadInt: String = "Intercambiar")extends Actividad(unNombreActividadInt){
    def aplicarActividad(pok: Pokemon): Pokemon = {
      
      pok.especie.condicionParaEvolucionar  match {
        case e: CondicionIntercambiar => pok.especie.evolucionarSiPuede(pok)
          
        case p => {
                    println(s"El Pokemon ${pok.nombrePokemon} Se puso Triste Al Ser Intercambiado Y Pierde Energia")
                    pok.copy(energia = (pok.energia -10).max(0))
                  }
      }
    }
  }
  
  class ActividadUsarPiedra(var piedra : PiedraEvolutiva ,unNombreActividadUsarPiedra: String = "UsarPiedra")extends Actividad(unNombreActividadUsarPiedra){
    
    def aplicarActividad(pok: Pokemon): Pokemon = {
      
      //pok.especie.evolucionarSiPuede(pok)
      
      pok.especie.condicionParaEvolucionar  match {
        case e : CondicionUsoDePiedra => if(piedra.tipo == "Lunar"|| piedra.tipo == e.piedra.tipo){
                                          pok.especie.evolucionarSiPuede(pok)
                                          }else{
                                         println(s"El Pokemon ${pok.nombrePokemon} No Pudo Evolucionar A  ${pok.especie.especieAEvolucionar.nombreEspecie} Tras Ser Expuesto a la Radiacion De una PIEDRA ${piedra.tipo.toUpperCase()}  Ya Que Su Tipo Principal es ${pok.especie.tipoPrincipal.toUpperCase()}" )   
                                            pok}
        
        case p =>  pok 
                    
        }
      }
    }
    
    
    
  
  


