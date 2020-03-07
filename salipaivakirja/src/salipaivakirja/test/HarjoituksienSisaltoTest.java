package salipaivakirja.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import salipaivakirja.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.03.07 16:11:17 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class HarjoituksienSisaltoTest {



  // Generated by ComTest BEGIN
  /** 
   * testAnna29 
   * @throws SailoException when error
   */
  @Test
  public void testAnna29() throws SailoException {    // HarjoituksienSisalto: 29
    var h = new HarjoituksienSisalto(); 
    h.lisaa(new HarjoituksenSisalto()); 
    h.lisaa(new HarjoituksenSisalto()); 
    h.lisaa(new HarjoituksenSisalto()); 
    h.lisaa(new HarjoituksenSisalto()); 
    assertEquals("From: HarjoituksienSisalto line: 36", 4, h.getlkm()); 
    assertEquals("From: HarjoituksienSisalto line: 37", 1, h.anna(3).get_id()-h.anna(2).get_id()); 
    assertEquals("From: HarjoituksienSisalto line: 38", true, h.poista(3)); 
    assertEquals("From: HarjoituksienSisalto line: 39", false, h.poista(3)); 
    assertEquals("From: HarjoituksienSisalto line: 40", 3, h.getlkm()); 
    try {
    h.anna(3); 
    fail("HarjoituksienSisalto: 41 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END
}