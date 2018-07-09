

package Pokemon

abstract class Estado {
  def string():String
  
}

class Dormido(var cantDeLlamados : Int = 0)extends Estado {
  def string():String = {
    "Dormido"
  }
  
}

class Paralizado()extends Estado {
  def string():String = {
    "Paralizado"
  }
}

class KO()extends Estado {
  def string():String = {
    "K.O"
  }
   
}