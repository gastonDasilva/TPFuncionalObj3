

package Pokemon



class Especie (
    val nombreEspecie : String,
    val experiencia: Int, 
    val energia: Int, 
    val energiaMaxima: Int, 
    val fuerza: Int, 
    val velocidad: Int,
    val tipoPrincipal: String,
    var especieAEvolucionar : Especie = null,
    var condicionParaEvolucionar : CondicionEvolucionar = null,
    var tipoSecundario: String = null
){
  
  def agregarSegundoTipoEspecie(str: String): Especie =  {
    this.tipoSecundario = str;
    this
  }
  
  def crearPokemon(incExp:Int, incEn:Int, incEnMax: Int, incFue: Int, incVel: Int, nombreP: String) : Pokemon = {
    /* Crea un pokemon En base a las caracteristicas de la especie sumandole las cantidades 
     *  respectivas recibidas 
     * 
     */
    val res : Pokemon = new Pokemon(
        this, 
        this.experiencia + incExp , 
        this.energia+ incEn, 
        this.energiaMaxima+ incEnMax,
        this.fuerza + incFue,
        this.velocidad+ incVel,
        nombreP);
   return res 
  }
  
  def evolucionarSiPuede(pok: Pokemon) : Pokemon = {
    /*
     * El pokemon realiza la evolucion segun la conficionDeEvolucion correspondiente a la Especie si es que tiene una.
     */
    
    this.condicionParaEvolucionar  match {
      case e : CondicionSubirExperiencia => {
                                              if(this.condicionParaEvolucionar.puedeEvolucionar(pok)){
                                                println(s"El Pokemon ${pok.nombrePokemon} Evoluciono a la Especie ${this.especieAEvolucionar.nombreEspecie} al llegar al ${ e.expNecesaria } De Experiencia Necesaria")
                                                this.especieAEvolucionar.crearPokemon(pok.experiencia, 0, 0, 0, 0, pok.nombrePokemon);
                                               
                                              }else{
                                                println(s"El Pokemon ${pok.nombrePokemon} Aun No Puede Evolucionar, No cumple la condicion De Igualar O Superar Los ${e.expNecesaria} Puntos De  Experiencia")
                                                 pok 
                                              }
                                            }
      
      case e : CondicionIntercambiar => {
                                          println(s"El Pokemon ${pok.nombrePokemon} Evoluciono a la Especie ${this.especieAEvolucionar.nombreEspecie} Al Ser Intercambiado")
                                          this.especieAEvolucionar.crearPokemon(pok.experiencia, 0, 0, 0, 0, pok.nombrePokemon);
                                         }
      
      case e : CondicionUsoDePiedra =>{  
                                          println(s"El Pokemon ${pok.nombrePokemon} Evoluciono A ${this.especieAEvolucionar.nombreEspecie} Tras Ser Expuesto a la Radiacion De una PIEDRA ${e.piedra.tipo.toUpperCase()} " )
                                           this.especieAEvolucionar.crearPokemon(pok.experiencia, 0, 0, 0, 0, pok.nombrePokemon);
                                      } 
      case e => {
                  println(s"El Pokemon ${pok.nombrePokemon} No Evoluciona")
                  pok
      }
    }
  }
  
}