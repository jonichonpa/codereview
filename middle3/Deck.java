import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Java講座 中級3. カードとデッキ <br />
 * Deck クラス
 * @author Kazuki_Endo
 */

public class Deck {

	/**
	 * デッキを構成するカードのリスト
	 */
	private List<Card> cardList;

	/**
	 * デッキを初期化する
	 * 
	 * @param ジョーカーの数
	 */
	public Deck(int numberOfJoker) {
		this.cardList = new ArrayList<Card>();
		// 1 から 13 までの数のすべてのマークのカードをデッキに追加する
		for (Mark mark : Mark.values()) {
			if (mark == Mark.JOKER) {
				continue;
			}
			for (int num = 1; num <= 13; num++) {
				this.cardList.add(new Card(mark, num));
			}
		}
		// ジョーカーをデッキに追加する
		for (int i = 0; i < numberOfJoker; i++) {
			this.cardList.add(new Card(Mark.JOKER));
		}
		shuffle();
	}

	/**
	 * デッキをソートする
	 */
	public void sort() {
		Collections.sort(this.cardList);
	}

	/**
	 * デッキをシャッフルする
	 */
	public void shuffle() {
		Collections.shuffle(this.cardList);
	}

	/**
	 * カードのリストを返す
	 * 
	 * @return カードのリスト
	 */
	public List<Card> getCardList() {
		return this.cardList;
	}

	/**
	 * デッキから指定枚数分カードを引く
	 * 
	 * @param デッキから引くカードの枚数
	 * @return 引いたカードのリスト
	 */
	public List<Card> pullCards(int numberOfCard)
			throws IndexOutOfBoundsException {
		List<Card> pulledCardList = new ArrayList<Card>();
		for (int i = 0; i < numberOfCard; i++) {
			pulledCardList.add(this.cardList.get(0));
			this.cardList.remove(0);
		}
		return pulledCardList;
	}

}
