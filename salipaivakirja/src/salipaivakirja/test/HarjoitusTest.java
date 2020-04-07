package salipaivakirja.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import salipaivakirja.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.04.07 17:05:15 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class HarjoitusTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi56 */
  @Test
  public void testRekisteroi56() {    // Harjoitus: 56
    Harjoitus l1 = new Harjoitus(); 
    Harjoitus l2 = new Harjoitus(); 
    int n1 = l1.getharj_id(); 
    int n2 = l2.getharj_id(); 
    assertEquals("From: Harjoitus line: 61", n2-1, n1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse115 */
  @Test
  public void testParse115() {    // Harjoitus: 115
    Harjoitus harj = new Harjoitus(); 
    harj.parse("20|1.2.2020"); 
    assertEquals("From: Harjoitus line: 118", "20|1.2.2020", harj.toString()); 
    Harjoitus harj2 = new Harjoitus(); 
    assertEquals("From: Harjoitus line: 120", 1, harj2.getID()-harj.getID()); 
  } // Generated by ComTest END
}