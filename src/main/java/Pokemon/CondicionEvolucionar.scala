

package Pokemon

trait CondicionEvolucionar {
  
  def puedeEvolucionar(pok: Pokemon): Boolean
  
}


class CondicionSubirExperiencia(val expNecesaria : Int)extends CondicionEvolucionar{
    def puedeEvolucionar(pok: Pokemon): Boolean = {
      pok.experiencia > expNecesaria
  
  }

}

class CondicionIntercambiar() extends CondicionEvolucionar{
  
  def puedeEvolucionar(pok: Pokemon): Boolean = {
      true
  
  }
}


class CondicionUsoDePiedra(val piedra: PiedraEvolutiva) extends CondicionEvolucionar{
  
  def puedeEvolucionar(pok: Pokemon): Boolean = true
}
