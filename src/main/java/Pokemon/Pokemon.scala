

package Pokemon

case class Pokemon (
    val especie: Especie,
    val experiencia: Int , 
    val energia: Int, 
    val energiaMaxima: Int, 
    val fuerza: Int, 
    val velocidad: Int,
    val nombrePokemon : String , 
    val estado : Estado = null
   ){
   
  
  def evolucionar():Pokemon = {
    this.especie.evolucionarSiPuede(this)
  }
  
  
}