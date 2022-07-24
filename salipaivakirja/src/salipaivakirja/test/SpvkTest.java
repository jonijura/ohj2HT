package salipaivakirja.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import salipaivakirja.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.07.24 10:59:30 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class SpvkTest {



  // Generated by ComTest BEGIN
  /** 
   * testLiikeHistoriaTiedostona156 
   * @throws SailoException when error
   */
  @Test
  public void testLiikeHistoriaTiedostona156() throws SailoException {    // Spvk: 156
    Spvk spvk = new Spvk(); 
    spvk.lisaa(new Liike("kuperkeikka",true)); 
    spvk.lisaa(new Liike("karrynpyora",true)); 
    spvk.lisaa(new Harjoitus("3.4.2019",true)); 
    spvk.lisaa(new Harjoitus("1.4.2019",true)); 
    spvk.lisaa(new Harjoitus("5.4.2019",true)); 
    int[] t1 = { 2,1,3,12,65} ; 
    int[] t2 = { 1,2,4,4,40} ; 
    int[] t3 = { 1,1,5,9,120} ; 
    int[] t4 = { 3,1,3,8,75} ; 
    spvk.lisaa(new HarjoituksenSisalto(t1)); 
    spvk.lisaa(new HarjoituksenSisalto(t2)); 
    spvk.lisaa(new HarjoituksenSisalto(t3)); 
    spvk.lisaa(new HarjoituksenSisalto(t4)); 
    assertEquals("From: Spvk line: 172", 50, spvk.liikeHistoriaTiedostona(1).length()); 
    assertEquals("From: Spvk line: 173", 40, spvk.harjsisTiedostona(1).length()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTarkistaLiike209 */
  @Test
  public void testTarkistaLiike209() {    // Spvk: 209
    Spvk spvk = new Spvk(); 
    Liike li =  new Liike("kuperkeikka",true); 
    spvk.lisaa(li); 
    assertEquals("From: Spvk line: 213", null, spvk.tarkistaLiike("kuperkeikka")); 
    assertEquals("From: Spvk line: 214", null, spvk.tarkistaLiike(" Kuperkeikka  ")); 
    assertEquals("From: Spvk line: 215", "UusiLiike: \"markus\"", spvk.tarkistaLiike("markus")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoistaHarjoitus233 
   * @throws SailoException when error
   */
  @Test
  public void testPoistaHarjoitus233() throws SailoException {    // Spvk: 233
    Spvk spvk = new Spvk(); 
    var h =  new Harjoitus("1.2.2020", true); 
    var hs = new HarjoituksenSisalto("1|1|1|1|1|1"); 
    spvk.lisaa(h); 
    spvk.lisaa(hs); 
    assertEquals("From: Spvk line: 240", 1, spvk.getHarjsislkm()); 
    spvk.poistaHarjoitus(h.getID()); 
    spvk.poistaHarjoitus(1); 
    assertEquals("From: Spvk line: 243", 0, spvk.getHarjoitustenlkm()); 
    assertEquals("From: Spvk line: 244", 0, spvk.getHarjsislkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testEtsi319 */
  @Test
  public void testEtsi319() {    // Spvk: 319
    Spvk spvk = new Spvk(); 
    spvk.lisaa(new Liike("penkkipunnerrus", false)); 
    spvk.lisaa(new Liike("pystypunnerrus", false)); 
    assertEquals("From: Spvk line: 323", 2, spvk.etsi("p").size()); 
    assertEquals("From: Spvk line: 324", 1, spvk.etsi("penk").size()); 
    assertEquals("From: Spvk line: 325", 0, spvk.etsi("a").size()); 
  } // Generated by ComTest END
}