package salipaivakirja.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import salipaivakirja.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.03.02 15:14:18 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class LiikeTest {



  // Generated by ComTest BEGIN
  /** testParse47 */
  @Test
  public void testParse47() {    // Liike: 47
    Liike l1 = new Liike(); 
    l1.parse("3|kuperkeikka"); 
    assertEquals("From: Liike line: 50", "kuperkeikka", l1.getLiikkeenNimi()); 
    assertEquals("From: Liike line: 51", 3, l1.getLiike_id()); 
    l1.parse(""); 
    assertEquals("From: Liike line: 53", "VirheellinenTallennusmuoto", l1.getLiikkeenNimi()); 
    assertEquals("From: Liike line: 54", -1, l1.getLiike_id()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi97 */
  @Test
  public void testRekisteroi97() {    // Liike: 97
    Liike l1 = new Liike(); 
    Liike l2 = new Liike(); 
    int n1 = l1.getLiike_id(); 
    int n2 = l2.getLiike_id(); 
    assertEquals("From: Liike line: 102", n2-1, n1); 
  } // Generated by ComTest END
}