package salipaivakirja.test;
// Generated by ComTest BEGIN
import java.io.File;
import java.util.Iterator;
import static org.junit.Assert.*;
import org.junit.*;
import salipaivakirja.*;
// Generated by ComTest END

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import salipaivakirja.Liike;
import salipaivakirja.Liikkeet;
import salipaivakirja.SailoException;

/**
 * Test class made by ComTest
 * @version 2022.07.24 10:57:23 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class LiikkeetTest {



  // Generated by ComTest BEGIN
  /** testPoista59 */
  @Test
  public void testPoista59() {    // Liikkeet: 59
    var l = new Liikkeet(); 
    l.lisaa(new Liike()); 
    var li = new Liike(); 
    l.lisaa(li); 
    int a = l.getlkm(); 
    assertEquals("From: Liikkeet line: 65", true, l.poista(li.getID())); 
    assertEquals("From: Liikkeet line: 66", 1, a-l.getlkm()); 
    try {
    l.annaLiike(li.getID()); 
    fail("Liikkeet: 67 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testAnnaLiike123 
   * @throws SailoException when error
   */
  @Test
  public void testAnnaLiike123() throws SailoException {    // Liikkeet: 123
    Liikkeet l = new Liikkeet(); 
    l.lisaa(new Liike("kuperkeikka",true)); 
    assertEquals("From: Liikkeet line: 127", "kuperkeikka", l.annaLiike(1).getLiikkeenNimi()); 
    try {
    l.annaLiike(2); 
    fail("Liikkeet: 128 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
    l.lisaa(new Liike("karrynpyora", true)); 
    assertEquals("From: Liikkeet line: 130", "karrynpyora", l.annaLiike(2).getLiikkeenNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testOnkoUusi163 */
  @Test
  public void testOnkoUusi163() {    // Liikkeet: 163
    Liikkeet l = new Liikkeet(); 
    Liike li =  new Liike("kuperkeikka",true); 
    l.lisaa(li); 
    assertEquals("From: Liikkeet line: 167", li.getID(), l.onkoUusi("kuperkeikka")); 
    assertEquals("From: Liikkeet line: 168", li.getID(), l.onkoUusi(" Kuperkeikka  ")); 
    assertEquals("From: Liikkeet line: 169", -1, l.onkoUusi("markus")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedosto193 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedosto193() throws SailoException {    // Liikkeet: 193
    Liikkeet liikkeet = new Liikkeet(); 
    Liike l1 = new Liike("leuanveto"); 
    Liike l2 = new Liike("kyykky"); 
    Liike l3 = new Liike("penkkipunnerrus"); 
    Liike l4 = new Liike("kuperkeikka"); 
    Liike l5 = new Liike("alatalja"); 
    String tiedNimi = "liikkeetTesti"; 
    File ftied = new File(tiedNimi); 
    try {
    liikkeet.lueTiedosto(tiedNimi); 
    fail("Liikkeet: 205 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
    liikkeet.lisaa(l1); 
    liikkeet.lisaa(l2); 
    liikkeet.lisaa(l3); 
    liikkeet.lisaa(l4); 
    liikkeet.lisaa(l5); 
    liikkeet.tallenna(tiedNimi); 
    liikkeet = new Liikkeet(); 
    liikkeet.lueTiedosto(tiedNimi); 
    Iterator<Liike> i = liikkeet.iterator(); 
    assertEquals("From: Liikkeet line: 215", l1.toString(), i.next().toString()); 
    assertEquals("From: Liikkeet line: 216", l2.toString(), i.next().toString()); 
    assertEquals("From: Liikkeet line: 217", l3.toString(), i.next().toString()); 
    assertEquals("From: Liikkeet line: 218", l4.toString(), i.next().toString()); 
    assertEquals("From: Liikkeet line: 219", l5.toString(), i.next().toString()); 
    assertEquals("From: Liikkeet line: 220", false, i.hasNext()); 
    ftied.delete(); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetLiikeID316 */
  @Test
  public void testGetLiikeID316() {    // Liikkeet: 316
    Liikkeet ll = new Liikkeet(); 
    Liike l1 = new Liike("kuperkeikka",true); 
    Liike l2 = new Liike("karrynpyora", true); 
    ll.lisaa(l1); 
    ll.lisaa(l2); 
    assertEquals("From: Liikkeet line: 322", l1.getID(), ll.getLiikeID("kuperkeikka")); 
    assertEquals("From: Liikkeet line: 323", -1, ll.getLiikeID("kuperkeika")); 
  } // Generated by ComTest END
}