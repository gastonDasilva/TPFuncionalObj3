

package Pokemon

import org.junit.Test
import org.junit.Assert._

class TestEvolucionesPokemon {
  
  
  val gim = new Gimnasio()
  
  
  @Test
  def testearLaEvolucionPorSubirExperienciaDeUnPokemon():Unit = {
    var geodudeDeAlohaEsp = new Especie("GeodudeDeAlola",0,60,100,70,55,"Roca");
    var condicionSubirExp = new CondicionSubirExperiencia(1500);
    var geodudeEsp = new Especie("Geodude",1496,20,100,60,50,"Roca",geodudeDeAlohaEsp,condicionSubirExp)
    var geodude = geodudeEsp.crearPokemon(0, 0, 0, 0, 0, "GeodudePurpura")
    assertEquals(20,geodude.energia)
    assertEquals(1496,geodude.experiencia)
    
    var geodudeSinPoderEvolucionar = geodude.evolucionar();
    assertEquals("Geodude",geodudeSinPoderEvolucionar.especie.nombreEspecie)
    
    val actividadLevantarPesas = new ActividadLevantarPesas(13);
    
    val geodudeMasFuerte = gim.realizarActividad(actividadLevantarPesas, geodude)
    assertEquals(1509,geodudeMasFuerte.experiencia)
    
    val geodudeEvolucionado = geodudeMasFuerte.evolucionar();
    assertEquals(60,geodudeEvolucionado.energia)
    assertEquals(1509,geodudeEvolucionado.experiencia)
    assertEquals(70,geodudeEvolucionado.fuerza)
    assertEquals(55,geodudeEvolucionado.velocidad)
    assertEquals("GeodudeDeAlola",geodudeEvolucionado.especie.nombreEspecie)
    
  }
  
  @Test
  def testearLaEvolucionDeUnPokemonAlSerIntercambiado():Unit = {
    var omastar = new Especie("Omastar",0,60,100,70,55,"Roca");
    var condicionIntercambiar = new CondicionIntercambiar();
    var omanyteEsp= new Especie("Omanyte",1496,20,100,60,50,"Roca",omastar,condicionIntercambiar)
    var omanyte = omanyteEsp.crearPokemon(0, 0, 0, 0, 0, "OmanyteXeneize")
    assertEquals(20,omanyte.energia)
    assertEquals(1496,omanyte.experiencia)
    
    val actividadIntercambiar = new ActividadIntercambiar();
    
    val omanyteEvolucionado  = gim.realizarActividad(actividadIntercambiar, omanyte)
    
    assertEquals(60,omanyteEvolucionado.energia)
    assertEquals(1496,omanyteEvolucionado.experiencia)
    assertEquals(70,omanyteEvolucionado.fuerza)
    assertEquals(55,omanyteEvolucionado.velocidad)
    assertEquals("Omastar",omanyteEvolucionado.especie.nombreEspecie)
  }
  
  @Test
  def testearQueUnPokemonSinConficionDeEvuliocnarJntercabiarPierdeEnergiaAlSerIntercambiado():Unit = {
    var omastar = new Especie("Omastar",0,60,100,70,55,"Roca");
    var condicionSubExp = new CondicionSubirExperiencia(1500);
    var omanyteEsp= new Especie("Omanyte",1496,20,100,60,50,"Roca",omastar,condicionSubExp)
    var omanyte = omanyteEsp.crearPokemon(0, 0, 0, 0, 0, "OmanyteDeLasPraderas")
    assertEquals(20,omanyte.energia)
    assertEquals(1496,omanyte.experiencia)
    
    val actividadIntercambiar = new ActividadIntercambiar();
    
    val omanyteNOEvolucionado  = gim.realizarActividad(actividadIntercambiar, omanyte)
    
    assertEquals(10,omanyteNOEvolucionado.energia)
    
    assertEquals(1496,omanyteNOEvolucionado.experiencia)
    assertEquals(60,omanyteNOEvolucionado.fuerza)
    assertEquals(50,omanyteNOEvolucionado.velocidad)
    assertEquals("Omanyte",omanyteNOEvolucionado.especie.nombreEspecie)
  }
  
  @Test
  def testearQueUnPokemonEvolucionaAlUsarUnaPiedraDeSuMismoTipoPrincipal():Unit ={
    
    var kabutops = new Especie("Kabutops",0,90,100,80,95,"Roca");
    var piedraRoca =  new PiedraEvolutiva("Roca");
    var condicionUsoDePiedraDeRoca = new CondicionUsoDePiedra(piedraRoca);
    var kabutoEsp= new Especie("Kabuto",800,20,100,60,50,"Roca",kabutops,condicionUsoDePiedraDeRoca)
    var kabuto = kabutoEsp.crearPokemon(0, 0, 0, 0, 0, "KabutoFluor")
    assertEquals(20,kabuto.energia)
    assertEquals(800,kabuto.experiencia)
    
    
    var piedraRocaAUsar =  new PiedraEvolutiva("Roca");
    val actividadUsarPiedra = new ActividadUsarPiedra(piedraRocaAUsar);
    
    val kabutoEvolucionadoAKabutops  = gim.realizarActividad(actividadUsarPiedra, kabuto)
    
    assertEquals(90,kabutoEvolucionadoAKabutops.energia)
    assertEquals(800,kabutoEvolucionadoAKabutops.experiencia)
    assertEquals(80,kabutoEvolucionadoAKabutops.fuerza)
    assertEquals(95,kabutoEvolucionadoAKabutops.velocidad)
    assertEquals("Kabutops",kabutoEvolucionadoAKabutops.especie.nombreEspecie)
    
    
    
  }
  
  @Test
  def testearQueUnPokemonNoEvolucionaAlUsarUnAPiedraDeDiferenteTipoAlSuyo():Unit = {
    var kabutops = new Especie("Kabutops",0,90,100,80,95,"Roca");
    var piedraRoca =  new PiedraEvolutiva("Roca");
    var condicionUsoDePiedraDeRoca = new CondicionUsoDePiedra(piedraRoca);
    var kabutoEsp= new Especie("Kabuto",800,20,100,60,50,"Roca",kabutops,condicionUsoDePiedraDeRoca)
    var kabuto = kabutoEsp.crearPokemon(0, 0, 0, 0, 0, "KabutoRoman")
    assertEquals(20,kabuto.energia)
    assertEquals(800,kabuto.experiencia)
    
    
    var piedraFuegoAUsar =  new PiedraEvolutiva("Fuego");
    val actividadUsarPiedra = new ActividadUsarPiedra(piedraFuegoAUsar);
    
    val kabutoNOEvolucionoAKabutops  = gim.realizarActividad(actividadUsarPiedra, kabuto)
    
    assertEquals(20,kabutoNOEvolucionoAKabutops.energia)
    assertEquals(800,kabutoNOEvolucionoAKabutops.experiencia)
    assertEquals(60,kabutoNOEvolucionoAKabutops.fuerza)
    assertEquals(50,kabutoNOEvolucionoAKabutops.velocidad)
    assertEquals("Kabuto",kabutoNOEvolucionoAKabutops.especie.nombreEspecie)
  }
  
  @Test
  def testearQueUnPokemonConCondicionEvolutivaDeintercambiarNoEvolucionaAlusarUnaPiedra():Unit = {
    var kabutops = new Especie("Kabutops",0,90,100,80,95,"Roca");
    var condicionIntercambiar = new CondicionIntercambiar();
    var kabutoEsp= new Especie("Kabuto",800,20,100,60,50,"Roca",kabutops,condicionIntercambiar)
    var kabuto = kabutoEsp.crearPokemon(0, 0, 0, 0, 0, "KabutoMaradoniano")
    assertEquals(20,kabuto.energia)
    assertEquals(800,kabuto.experiencia)
    
    var piedraFuegoAUsar =  new PiedraEvolutiva("Fuego");
    val actividadUsarPiedra = new ActividadUsarPiedra(piedraFuegoAUsar);
    
    val kabutoNOEvolucionoAKabutops  = gim.realizarActividad(actividadUsarPiedra, kabuto)
    
    assertEquals(20,kabutoNOEvolucionoAKabutops.energia)
    assertEquals("Kabuto",kabutoNOEvolucionoAKabutops.especie.nombreEspecie)
    assertEquals(800,kabutoNOEvolucionoAKabutops.experiencia)
    assertEquals(60,kabutoNOEvolucionoAKabutops.fuerza)
    assertEquals(50,kabutoNOEvolucionoAKabutops.velocidad)
    
  }
  
  
}