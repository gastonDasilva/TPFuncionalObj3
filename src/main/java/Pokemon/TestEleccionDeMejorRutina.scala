

package Pokemon
import org.junit.Test
import org.junit.Assert._

class TestEleccionDeMejorRutina {
  val gim = new Gimnasio()
  
  @Test
  def testElejirLaRutinaQueMasHaceSubirDeNivelAlPokemon():Unit = {
    var geodudeEsp = new Especie("Geodude",0,20,100,60,50,"Roca")
    var geodude = geodudeEsp.crearPokemon(0, 0, 0, 0, 0, "Geodude Escarchado")
    assertEquals(20,geodude.energia)
    
    val actividadNadar = new ActividadNadar(13);
    val actividadDescansar = new ActividadDescansar();
    val actividadLevantarPesas = new ActividadLevantarPesas(3);
    val actividadLevantarPesas2 = new ActividadLevantarPesas(5); 
    
    var actividades = Array(actividadNadar, actividadLevantarPesas, actividadDescansar);
    var rutinaParaGeodude1 = new Rutina("RutinaGeodudePurpura",actividades);
    
    var actividades2:Array[Actividad] = Array(actividadLevantarPesas2, actividadLevantarPesas2, actividadLevantarPesas2);
    var rutinaParaGeodude2 = new Rutina("RutinaGeodudePurpuraLevantarPesas",actividades2);
    
    var actividades3 = Array(actividadLevantarPesas, actividadDescansar);
    var rutinaParaGeodude3 = new Rutina("RutinaGeodudePurpuraDescansar",actividades3);
    
    
    var rutinas: Array[Rutina[Actividad]] = Array(rutinaParaGeodude1,rutinaParaGeodude2,rutinaParaGeodude3);
    val criterioElecc = new CriterioDeSubirMasNivel();
    var rutina = gim.detectarRutinaMasEficienteParaUnCriterio(geodude, rutinas, criterioElecc);
    assertEquals("RutinaGeodudePurpuraLevantarPesas",rutina.nombreRutina)
    
    
  }
  
  @Test
  def testearNOHayRutinaQueSatisfagaUnCriterioParUnPokemon():Unit = {
    var geodudeEsp = new Especie("Geodude",0,20,100,60,50,"Roca")
    var geodude = geodudeEsp.crearPokemon(0, 0, 0, 0, 0, "Geodude Brasilero")
    assertEquals(20,geodude.energia)
    
    val actividadNadar = new ActividadNadar(13);
    val actividadDescansar = new ActividadDescansar();
    val actividadLevantarPesas = new ActividadLevantarPesas(3);
    val actividadLevantarPesas2 = new ActividadLevantarPesas(5); 
    
    var actividades = Array(actividadNadar, actividadLevantarPesas, actividadDescansar);
    var rutinaParaGeodude1 = new Rutina("RutinaGeodudePurpura",actividades);
    
    var actividades2:Array[Actividad] = Array(actividadLevantarPesas2, actividadLevantarPesas2, actividadLevantarPesas2);
    var rutinaParaGeodude2 = new Rutina("RutinaGeodudePurpuraLevantarPesas",actividades2);
    
    var actividades3 = Array(actividadLevantarPesas, actividadNadar);
    var rutinaParaGeodude3 = new Rutina("RutinaGeodudePurpuraDescansar",actividades3);
    
    
    var rutinas: Array[Rutina[Actividad]] = Array(rutinaParaGeodude1,rutinaParaGeodude2,rutinaParaGeodude3);
    val criterioElecc = new CriterioDeMayorCantidadDeEnergia();
    var rutina = gim.detectarRutinaMasEficienteParaUnCriterio(geodude, rutinas, criterioElecc);
    assertEquals(null,rutina)
    
  }
  
  @Test
  def testElejirLaRutinaQueMasEnergialeDaAlPokemon():Unit = {
    var geodudeEsp = new Especie("Geodude",0,20,100,60,50,"Roca")
    var geodude = geodudeEsp.crearPokemon(0, 0, 0, 0, 0, "Geodude Escarchado")
    assertEquals(20,geodude.energia)
    
    val actividadNadar = new ActividadNadar(13);
    val actividadDescansar = new ActividadDescansar();
    val actividadLevantarPesas = new ActividadLevantarPesas(3);
    val actividadLevantarPesas2 = new ActividadLevantarPesas(5); 
    
    var actividades = Array(actividadNadar, actividadLevantarPesas, actividadDescansar);
    var rutinaParaGeodude1 = new Rutina("RutinaGeodudePurpura",actividades);
    
    var actividades2:Array[Actividad] = Array(actividadLevantarPesas2, actividadLevantarPesas2, actividadLevantarPesas2);
    var rutinaParaGeodude2 = new Rutina("RutinaGeodudePurpuraLevantarPesas",actividades2);
    
    var actividades3 = Array(actividadLevantarPesas, actividadDescansar);
    var rutinaParaGeodude3 = new Rutina("RutinaGeodudePurpuraDescansar",actividades3);
    
    
    var rutinas: Array[Rutina[Actividad]] = Array(rutinaParaGeodude1,rutinaParaGeodude2,rutinaParaGeodude3);
    val criterioElecc = new CriterioDeMayorCantidadDeEnergia();
    var rutina = gim.detectarRutinaMasEficienteParaUnCriterio(geodude, rutinas, criterioElecc);
    assertEquals("RutinaGeodudePurpuraDescansar",rutina.nombreRutina)
  }
  
  @Test
  def testearElejirLaRutinaQueVuelvaMenosPesadoAlPokemon():Unit = {
    var omanyteEsp = new Especie("Omanyte",0,100,100,60,50,"Agua")
    var omanyte = omanyteEsp.crearPokemon(0, 0, 0, 0, 0, "Omanyte Uruguayo")
    assertEquals(100,omanyte.energia)
    
    val actividadNadar = new ActividadNadar(63);
    val actividadDescansar = new ActividadDescansar();
    val actividadLevantarPesas = new ActividadLevantarPesas(3);
    val actividadLevantarPesas2 = new ActividadLevantarPesas(5); 
    
    var actividades = Array(actividadNadar, actividadLevantarPesas, actividadDescansar);
    var rutinaParaGeodude1 = new Rutina("RutinaGeodudePurpura",actividades);
    
    var actividades2:Array[Actividad] = Array(actividadLevantarPesas2, actividadLevantarPesas2, actividadLevantarPesas2);
    var rutinaParaGeodude2 = new Rutina("RutinaGeodudePurpuraLevantarPesas",actividades2);
    
    var actividades3 = Array(actividadLevantarPesas, actividadDescansar);
    var rutinaParaGeodude3 = new Rutina("RutinaGeodudePurpuraDescansar",actividades3);
    
    
    var rutinas: Array[Rutina[Actividad]] = Array(rutinaParaGeodude1,rutinaParaGeodude2,rutinaParaGeodude3);
    val criterioElecc = new CriterioDeMenosPesado();
    var rutina = gim.detectarRutinaMasEficienteParaUnCriterio(omanyte, rutinas, criterioElecc);
    assertEquals("RutinaGeodudePurpura",rutina.nombreRutina)
    
  }
}