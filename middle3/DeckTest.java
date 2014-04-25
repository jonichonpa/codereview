import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

/**
 * Java講座 中級3. カードとデッキ <br />
 * DeckTestクラス
 * 
 * @author Kazuki_Endo
 * 
 */

public class DeckTest {

	/**
	 * ジョーカーの数を指定し、一つずつ調べてジョーカーの数をカウントする
	 */
	@Test
	public void ジョーカーの数を指定できる() {
		int numOfJoker = 10;
		Deck deck = new Deck(numOfJoker);
		int count = 0;
		for (Card card : deck.getCardList()) {
			if (card.getMark() == Mark.JOKER) {
				count++;
			}
		}
		assertEquals(numOfJoker, count);
	}

	/*
	 * 指定した数のカード数とリストのサイズが等しいか調べる
	 */
	@Test
	public void デッキから指定枚数を引くことができる() {
		int numOfPull = 8; // 引くカード枚数
		Deck deck = new Deck(2);
		List<Card> cardList = deck.pullCards(numOfPull);
		assertEquals(numOfPull, cardList.size());
	}

	/**
	 * 例外を投げるかチェックする
	 */
	@Test
	public void デッキから残り枚数以上のカードを引こうとすると例外を投げる() {
		Deck deck = new Deck(2);
		try {
			deck.pullCards(100);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

}
