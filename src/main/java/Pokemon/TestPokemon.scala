package Pokemon

import org.junit.Test
import org.junit.Assert._


class TestPokemon {
  
  val gim = new Gimnasio()
  
  @Test
  def testearQueUnPokemonPuedeRealizarUnaActividad():Unit ={
    var ratonElectrico = new Especie("Pikachu",0,10,100,95,50,"Pelea")
    var pikachu = ratonElectrico.crearPokemon(0, 0, 0, 0, 0,"Pikachu")
    assertEquals(10,pikachu.energia)
    //Pikachu hace una actividad de descanso 
    val actividadDescansar = new ActividadDescansar();
    val pikachuDescansado = gim.realizarActividad(actividadDescansar, pikachu)
    assertEquals(100,pikachuDescansado.energia)
    // Haciendo que Pikachu haga una actividad de pesas
    val actividadLevantarPesas = new ActividadLevantarPesas(3);
    val pikachuMasFuerte = gim.realizarActividad(actividadLevantarPesas, pikachu)
    assertEquals(6,pikachuMasFuerte.experiencia)
    
    // Haciendo que pikachu realize una actividad de nado
    val actividadNadar = new ActividadNadar(63);
    val pikachuBuceador = gim.realizarActividad(actividadNadar, pikachu)
    assertEquals(12600,pikachuBuceador.experiencia)
    assertEquals(0,pikachuBuceador.energia)
    
    
  }
  
  @Test
  def testearQueUnPokemonAguaAlHacerUnaActDeNadoLeSumeVelocidadYExp():Unit = {
    var squirtleAgua = new Especie("Squirtle",0,100,100,95,50,"Agua")
    var squirtle = squirtleAgua.crearPokemon(0, 0, 0, 0, 0, "Squirtle")
    assertEquals(100,squirtle.energia)
    val actividadNadar = new ActividadNadar(63);
    val squirtleBuceador = gim.realizarActividad(actividadNadar, squirtle)
    assertEquals(12600,squirtleBuceador.experiencia)
    assertEquals(37,squirtleBuceador.energia)
    assertEquals(51,squirtleBuceador.velocidad)
   
  }
  
  @Test
  def testearQueUnPokemonIntentaLevantarMasPesasDeLasQuePuedeYPierdeEnergia():Unit ={
    var ratonElectrico = new Especie("Pikachu",0,10,100,60,50,"Pelea")
    var pikachu2 = ratonElectrico.crearPokemon(0, 10, 0, 0, 0, "Pikachu Blanco ") 
    assertEquals(20,pikachu2.energia)
    
    val actividadLevantarPesas2 = new ActividadLevantarPesas(1500);
    val pikachuMasFuerte2 = gim.realizarActividad(actividadLevantarPesas2, pikachu2)
    assertEquals(0,pikachuMasFuerte2.experiencia)
    assertEquals(10,pikachuMasFuerte2.energia)
    
  }
  
  @Test
  def testearUnPokemonFantasmaNoPuedeLevantarPesas():Unit  = {
    var ratonFantasmal = new Especie("Sartin",0,20,100,60,50,"Fantasma")
    var sartin = ratonFantasmal.crearPokemon(0, 0, 0, 0, 0, "Sartin")
    assertEquals(20,sartin.energia)
    
    val actividadLevantarPesas = new ActividadLevantarPesas(30);
    val sartinFantasmal = gim.realizarActividad(actividadLevantarPesas, sartin)
    assertEquals(0,sartinFantasmal.experiencia)
    assertEquals(20,sartinFantasmal.energia)
    
     
    
  }
  
  
  ///////////// PARTE 2 //////////////////////
  
  @Test
  def testearQueUnPokemonQuePierdeConElAguaQuedaKOYNoRecibeNadaDeExp():Unit = {
    var geodudeEsp = new Especie("Geodude",0,20,100,60,50,"Roca")
    var geodude = geodudeEsp.crearPokemon(0, 0, 0, 0, 0, "Geodude")
    assertEquals(20,geodude.energia)
    
    val actividadNadar = new ActividadNadar(13);
    val geodudeBuceador = gim.realizarActividad(actividadNadar, geodude)
    assertEquals(0,geodudeBuceador.experiencia)
    assertEquals("K.O",geodudeBuceador.estado.string)
    
  }
  
  @Test
  def testearQueUnPokemonIntentaLevantarMasPesasDeLasQuePuedeYQuedaParalizado():Unit = {
    var ratonElectrico = new Especie("Pikachu",0,10,100,60,50,"Pelea")
    var pikachu2 = ratonElectrico.crearPokemon(0, 10, 0, 0, 0, "Pikachu Marron") 
    assertEquals(20,pikachu2.energia)
    
    val actividadLevantarPesas2 = new ActividadLevantarPesas(1500);
    val pikachuMasFuerte2 = gim.realizarActividad(actividadLevantarPesas2, pikachu2)
    assertEquals(0,pikachuMasFuerte2.experiencia)
    assertEquals(10,pikachuMasFuerte2.energia)
    assertEquals("Paralizado",pikachuMasFuerte2.estado.string)
    
    // El pokemon paralizado"PikachuMasFuerte2" intenta levantar pesas por lo tanto queda k.o y
    // no recibe nada de experiencia
    val pikachuMasFuerte3 = gim.realizarActividad(actividadLevantarPesas2, pikachuMasFuerte2)
    assertEquals(0,pikachuMasFuerte3.experiencia)
    assertEquals(10,pikachuMasFuerte3.energia)
    assertEquals("K.O",pikachuMasFuerte3.estado.string)
  }
  
  @Test
  def testearQueUnPokemonConMenosDeLaMitadDeEnergiaySinNingunEstadoSeQuedaDormido():Unit = {
    var ratonElectrico = new Especie("Pikachu",0,10,100,60,50,"Pelea")
    var pikachu = ratonElectrico.crearPokemon(0, 10, 0, 0, 0, "Pikachu Rojo") 
    assertEquals(20,pikachu.energia)
    val actividadDescansar = new ActividadDescansar();
    val pikachuDescansado = gim.realizarActividad(actividadDescansar, pikachu)
    assertEquals(100,pikachuDescansado.energia)
    assertEquals("Dormido",pikachuDescansado.estado.string)
    
    // CASO CONTRARIO DONDE EL POKEMON NO TIENE MENOS DE LA MITAD DE ENERGIAMAXIMA
    
    var ratonElectrico2 = new Especie("Pikachu",0,60,100,60,50,"Pelea")
    var pikachu2 = ratonElectrico2.crearPokemon(0, 10, 0, 0, 0, "Pikachu Azul") 
    assertEquals(70,pikachu2.energia)
    val actividadDescansar2 = new ActividadDescansar();
    val pikachuDescansado2 = gim.realizarActividad(actividadDescansar2, pikachu2)
    assertEquals(100,pikachuDescansado2.energia)
    assertEquals(null,pikachuDescansado2.estado)
  }
  
  @Test
  def testearQueUnPokemonDormidoIgnora3ActividadesParLuegoDespertarse():Unit = {
    var ratonElectrico = new Especie("Pikachu",0,10,100,60,50,"Pelea")
    var pikachu = ratonElectrico.crearPokemon(0, 10, 0, 0, 0, "Pikachu Verde") 
    assertEquals(20,pikachu.energia)
    val actividadDescansar = new ActividadDescansar();
    val pikachuDescansado = gim.realizarActividad(actividadDescansar, pikachu)
    assertEquals(100,pikachuDescansado.energia)
    assertEquals("Dormido",pikachuDescansado.estado.string)
    
    
    // COMIENZA A IGNORAR LAS ACTIVIDADES
    val actividadLevantarPesas = new ActividadLevantarPesas(3);
    val pikachuDescansado2 = gim.realizarActividad(actividadLevantarPesas, pikachuDescansado)
    assertEquals(100,pikachuDescansado2.energia)
    assertEquals(0,pikachuDescansado2.experiencia)
    assertEquals("Dormido",pikachuDescansado2.estado.string)
    
    val pikachuDescansado3 = gim.realizarActividad(actividadLevantarPesas, pikachuDescansado2)
    assertEquals(100,pikachuDescansado3.energia)
    assertEquals(0,pikachuDescansado3.experiencia)
    assertEquals("Dormido",pikachuDescansado3.estado.string)
    
    val pikachuDescansado4 = gim.realizarActividad(actividadLevantarPesas, pikachuDescansado3)
    assertEquals(100,pikachuDescansado4.energia)
    assertEquals(0,pikachuDescansado4.experiencia)
    assertEquals("Dormido",pikachuDescansado4.estado.string)
    
    // LUEGO DE TRES ACTIVIDADES IGNORADAS A ESTA LA EJECTURA
    val pikachuDescansado5 = gim.realizarActividad(actividadLevantarPesas, pikachuDescansado4)
    assertEquals(100,pikachuDescansado5.energia)
    assertEquals(6,pikachuDescansado5.experiencia)
    assertEquals(null,pikachuDescansado5.estado)
    
  }
  
  /////////////////////// PARTE 3 /////////////////////
  
  @Test
  def testearUnaRutinaAlSerEjecutada():Unit = {
    var ratonElectrico = new Especie("Pikachu",0,70,100,95,2,"Pelea")
    var pikachu = ratonElectrico.crearPokemon(0, 0, 0, 0, 0, "Pikachu VerdeAmarrillo")
    assertEquals(70,pikachu.energia)
    
    val actividadDescansar = new ActividadDescansar();
    
    val actividadLevantarPesas = new ActividadLevantarPesas(3);
    
    val actividadNadar = new ActividadNadar(63);
    
    var actividades = Array(actividadDescansar, actividadLevantarPesas, actividadNadar);
    var rutinaParaPikachu = new Rutina("RutinaPikachuVerdeAmarrillo",actividades);
    
    //println("Cant de actividades:"+actividades.size)
    
    val picackuConRutina = gim.realizarRutina(rutinaParaPikachu, pikachu)
    assertEquals(37,picackuConRutina.energia)
    assertEquals(12606,picackuConRutina.experiencia)
    assertEquals(2,picackuConRutina.velocidad)
    
  }
  
  @Test
  def testearQueUnaRutinaNoSePuedeHacerSiElPokemonEstaDormido():Unit = {
    var ratonElectrico = new Especie("Pikachu",0,10,100,60,50,"Pelea")
    var pikachu = ratonElectrico.crearPokemon(0, 10, 0, 0, 0, "Pikachu Rosado") 
    assertEquals(20,pikachu.energia)
    val actividadDescansar = new ActividadDescansar();
    val pikachuDescansado = gim.realizarActividad(actividadDescansar, pikachu)
    assertEquals(100,pikachuDescansado.energia)
    assertEquals("Dormido",pikachuDescansado.estado.string)
    
    
    val actividadDescansar2 = new ActividadDescansar();
    
    val actividadLevantarPesas = new ActividadLevantarPesas(3);
    
    val actividadNadar = new ActividadNadar(63);
    
    var actividades = Array(actividadDescansar2, actividadLevantarPesas, actividadNadar);
    var rutinaParaPikachu = new Rutina("RutinaPikachuRosado",actividades);
    
    val picackuConRutina = gim.realizarRutina(rutinaParaPikachu, pikachuDescansado)
    assertEquals(100,picackuConRutina.energia)
    assertEquals(0,picackuConRutina.experiencia)
    assertEquals(50,picackuConRutina.velocidad)
    
  }
  
  @Test
  def testearQueUnPokemonDeRocaNoPuedeHacerUnaRutinaQueContengaActDeNado():Unit ={
    var geodudeEsp = new Especie("Geodude",0,20,100,60,50,"Roca")
    var geodude = geodudeEsp.crearPokemon(0, 0, 0, 0, 0, "Geodude Escarchado")
    assertEquals(20,geodude.energia)
    
    val actividadNadar = new ActividadNadar(13);
    
    val actividadDescansar = new ActividadDescansar();
    
    val actividadLevantarPesas = new ActividadLevantarPesas(3);
    
    
    var actividades = Array(actividadNadar, actividadLevantarPesas, actividadDescansar);
    var rutinaParaGeodude = new Rutina("RutinaGeodudePurpura",actividades);
    
    val GeodudeConRutina = gim.realizarRutina(rutinaParaGeodude, geodude)
    assertEquals(0,GeodudeConRutina.energia)
    assertEquals(0,GeodudeConRutina.experiencia)
    assertEquals(50,GeodudeConRutina.velocidad)
    
  
  }
  
  @Test 
  def testearUnaRutinaDeUnaUnicaActividadParaUnPokemon():Unit = {
    var geodudeEsp = new Especie("Geodude",0,20,100,60,50,"Roca")
    var geodude = geodudeEsp.crearPokemon(0, 0, 0, 0, 0, "Geodude Purpura")
    assertEquals(20,geodude.energia)
    
    val actividadLevantarPesas = new ActividadLevantarPesas(3);
    
    var actividadesDePesas= Array(actividadLevantarPesas, actividadLevantarPesas, actividadLevantarPesas)
    
    val rutinaUnicaDePesasParaGeodude : Rutina[ActividadLevantarPesas] = new Rutina[ActividadLevantarPesas]("RutinaGeodudePurpura",actividadesDePesas)
    
    val GeodudeConRutina = gim.realizarRutina(rutinaUnicaDePesasParaGeodude, geodude)
    assertEquals(20,GeodudeConRutina.energia)
    assertEquals(9,GeodudeConRutina.experiencia)
    
  }
  
  
  
}