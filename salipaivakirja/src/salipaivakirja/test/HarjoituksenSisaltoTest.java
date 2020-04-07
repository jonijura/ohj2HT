package salipaivakirja.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import salipaivakirja.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.04.07 17:05:33 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class HarjoituksenSisaltoTest {



  // Generated by ComTest BEGIN
  /** testHarjoituksenSisalto27 */
  @Test
  public void testHarjoituksenSisalto27() {    // HarjoituksenSisalto: 27
    int[] t = { 2,1,3,12,65} ; 
    int[] t2 = { 2,1,3,12,20,1} ; 
    HarjoituksenSisalto hs = new HarjoituksenSisalto(t); 
    HarjoituksenSisalto hs2 = new HarjoituksenSisalto(t2); 
    assertEquals("From: HarjoituksenSisalto line: 32", 1, hs.getLiike_id()); 
    assertEquals("From: HarjoituksenSisalto line: 33", 2, hs.getHarj_id()); 
    assertEquals("From: HarjoituksenSisalto line: 34", "|3|12|65", hs.tiedostona()); 
    assertEquals("From: HarjoituksenSisalto line: 35", "|0|0|0", hs2.tiedostona()); 
    assertEquals("From: HarjoituksenSisalto line: 36", 1, hs2.get_id()-hs.get_id()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse62 */
  @Test
  public void testParse62() {    // HarjoituksenSisalto: 62
    var hs = new HarjoituksenSisalto("7|1|2|3|4|5"); 
    assertEquals("From: HarjoituksenSisalto line: 64", "|3|4|5", hs.tiedostona()); 
  } // Generated by ComTest END
}