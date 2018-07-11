

package Pokemon

import java.io.Console

abstract class Actividad(val nombreActividad: String) {
  /*Realizo la logica correspodiente para cada actividad*/
  def aplicarActividad(pok: Pokemon): Pokemon
}

class ActividadDescansar(unNombreActividadDesc: String = "Descansar")
extends Actividad(unNombreActividadDesc) {
  
  def aplicarActividad(pok: Pokemon): Pokemon = {
    /*Verifico si el pokemon esta en condiciones de realizar la actividad */
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
     var res = this.cantDeKilos / pok.fuerza
     if(res > 10){
       pok.copy(
           energia = (pok.energia - 10).max(0),
           estado = new Paralizado());
     }
     else{
           this.machearSegunTipo(pok,pok.especie.tipoPrincipal);
         } 
  }
   
  def machearSegunTipo(pok: Pokemon,tipo: String):Pokemon = { 
    /*Segun el tipo correspondiente del pokemon realiazo la logica adecuada */
    
    tipo  match {
         case "Pelea" => 
                          println(s" ${pok.nombrePokemon} Sube ${this.cantDeKilos * 2} Puntos De Experiencia X2");
                           pok.copy(experiencia = (pok.experiencia + cantDeKilos)*2);
                          //res.especie.evolucionarSiPuede(res)
                           
         case "Fantasma"=> 
                           println(s"El Pokemon ${pok.nombrePokemon } No Puede Levantar Pesas Ya Que es de Tipo ${pok.especie.tipoPrincipal}");
                           return pok;
                           
         case p => if(pok.especie.tipoPrincipal != tipo || pok.especie.tipoSecundario == null){
                       println(s"${pok.nombrePokemon} Sube ${this.cantDeKilos} Puntos De Experiencia");
                       pok.copy(experiencia = pok.experiencia + this.cantDeKilos);     
                     }
                     else{// Si posee el pokemon posee un segundo tipo se procede a ejecutar la recursividad
                         this.machearSegunTipo(pok, pok.especie.tipoSecundario);                          
                          };
                   
    }
  }
  
  
  
}

class ActividadNadar( var cantMinutos: Int,unNombreActividadNad: String = "Nadar")
extends Actividad(unNombreActividadNad) {
  
  def aplicarActividad(pok: Pokemon): Pokemon = {
    val pokRes = pok.copy(
                  energia = (pok.energia - this.cantMinutos).max(0),
                  experiencia = pok.experiencia + (200 * this.cantMinutos )); 
    
    this.machearSegunTipo(pok, pokRes, pok.especie.tipoPrincipal);
  }
  
  def machearSegunTipo(pok:Pokemon,pokRes:Pokemon, tipo: String):Pokemon = { 
    /*Segun el tipo correspondiente del pokemon se ejecuta la logica adecuada */
    val cantPuntVelocidad = this.cantMinutos / 60;
    
    tipo  match {    
      case "Agua" => println(s"El Pokemon ${pok.nombrePokemon } Sube ${(200 * this.cantMinutos )} Puntos de Experiencia Y ${cantPuntVelocidad} Puntos De Velocidad Al Nadar"); 
                     pokRes.copy(velocidad = (pokRes.velocidad + cantPuntVelocidad).min(100));
      
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
                 
      case p => if(pok.especie.tipoPrincipal != tipo || pok.especie.tipoSecundario == null){
                   println(s"El Pokemon ${pok.nombrePokemon } Sube ${(200 * this.cantMinutos )} Puntos de Experiencia Al Nadar por ${this.cantMinutos} Minutos"); 
                    pokRes;
                  }
                  else{ // Si posee el pokemon posee un segundo tipo se procede a ejecutar la recursividad
                       this.machearSegunTipo(pok, pokRes, pok.especie.tipoSecundario);
                      };
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
      pok.especie.condicionParaEvolucionar  match {
        case e : CondicionUsoDePiedra => if(piedra.tipo == "Lunar"|| piedra.tipo == pok.especie.tipoPrincipal/*e.piedra.tipo*/){
                                          pok.especie.evolucionarSiPuede(pok);
                                          }
                                          else{
                                               println(s"El Pokemon ${pok.nombrePokemon} No Pudo Evolucionar A  ${pok.especie.especieAEvolucionar.nombreEspecie} Tras Ser Expuesto a la Radiacion De una PIEDRA ${piedra.tipo.toUpperCase()}  Ya Que Su Tipo Principal es ${pok.especie.tipoPrincipal.toUpperCase()}" )   
                                               pok;
                                           };
        
        case p =>  pok 
                    
        }
      }
    
  }
    
    
    
  
  


