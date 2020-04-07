package salipaivakirja.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import salipaivakirja.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.04.07 19:15:03 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class LiikeTest {



  // Generated by ComTest BEGIN
  /** testParse47 */
  @Test
  public void testParse47() {    // Liike: 47
    Liike l1 = new Liike(); 
    l1.parse("15|kuperkeikka"); 
    assertEquals("From: Liike line: 50", "kuperkeikka", l1.getLiikkeenNimi()); 
    assertEquals("From: Liike line: 51", 15, l1.getLiike_id()); 
    Liike l2 = new Liike(); 
    assertEquals("From: Liike line: 53", 1, l2.getLiike_id()-l1.getLiike_id()); 
    l1.parse(""); 
    assertEquals("From: Liike line: 55", "VirheellinenTallennusmuoto", l1.getLiikkeenNimi()); 
    assertEquals("From: Liike line: 56", -1, l1.getLiike_id()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi102 */
  @Test
  public void testRekisteroi102() {    // Liike: 102
    Liike l1 = new Liike(); 
    Liike l2 = new Liike(); 
    int n1 = l1.getLiike_id(); 
    int n2 = l2.getLiike_id(); 
    assertEquals("From: Liike line: 107", n2-1, n1); 
  } // Generated by ComTest END
}