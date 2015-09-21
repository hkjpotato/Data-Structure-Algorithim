import org.junit.Test;
import static org.junit.Assert.*;

public class TextProcessingTests {

	@Test
	public void testBoyerMooreBasic() {
		int[] table = TextProcessing.buildLastTable("abcd");
		assertEquals(0,table['a']);
		assertEquals(1,table['b']);
		assertEquals(2,table['c']);
		assertEquals(3,table['d']);
		assertEquals(-1,table['e']);
	}

	@Test
	public void testBoyerMooreRepeating() {
		int[] table = TextProcessing.buildLastTable("ismfofism");
		assertEquals(6,table['i']);
		assertEquals(7,table['s']);
		assertEquals(8,table['m']);
		assertEquals(5,table['f']);
		assertEquals(4,table['o']);
		assertEquals(5,table['f']);
		assertEquals(-1,table['a']);
	}

	@Test
	public void testUpdateHashBasic() {
		assertEquals(129301,TextProcessing.updateHash(129301, 'a', 'a', 2));

	}

	@Test
	public void testUpdateHashLong() {
		assertEquals(-791990801,TextProcessing.updateHash(-607618070, 'a', 'g', 6));
	}

	@Test
	public void testUpdateHashOverflow() {
		assertEquals(-15471684,TextProcessing.updateHash(2142358318, 'a', 'd', 7));	
	}
}