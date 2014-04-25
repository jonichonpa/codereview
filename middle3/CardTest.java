import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Java講座 中級3. カードとデッキ <br />
 * CardTest クラス
 * 
 * @author Kazuki_Endo
 * 
 */

public class CardTest {

	/**
	 * クラブ、ダイヤ、ハート、スペード、ジョーカーの順に小さい
	 */
	@Test
	public void カードの大小をマークかつジョーカー抜きで比較する1() {
		Mark[] MARKS = Mark.values();
		for (int i = 0; i < MARKS.length - 1; i++) {
			Card card1 = new Card(MARKS[i], 1);
			for (int j = i + 1; j < MARKS.length - 1; j++) {
				Card card2 = new Card(MARKS[j], 1);
				assertEquals(-1, card1.compareTo(card2));
			}
		}
	}

	/**
	 * ジョーカーはどのカードよりも大きい
	 */
	@Test
	public void カードの大小をジョーカーで比較する2() {
		Card joker = new Card(Mark.JOKER);
		for (Mark mark : Mark.values()) {
			if (mark == Mark.JOKER) {
				continue;
			}
			for (int number = 1; number <= 13; number++) {
				Card card = new Card(mark, number);
				assertEquals(1, joker.compareTo(card));
			}
		}
	}

	/**
	 * 1 から 13 の数字に対して調べる
	 */
	@Test
	public void カードの大小を数字で比較する3() {
		for (int i = 1; i <= 13; i++) {
			Card card1 = new Card(Mark.CLUB, i);
			for (int j = i + 1; j <= 13; j++) {
				Card card2 = new Card(Mark.CLUB, j);
				assertEquals(-1, card1.compareTo(card2));
			}
		}
	}

}
