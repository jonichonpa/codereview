/**
 * Java講座 中級3. カードとデッキ <br />
 * Card クラス
 * 
 * @author Kazuki_Endo
 * 
 */

public class Card implements Comparable<Card> {

	/**
	 * マーク
	 */
	private Mark mark;

	/**
	 * 数字
	 */
	private int number;

	/**
	 * マークと数を元に大小関係を比較する
	 * 
	 * @param 比較対象になるカード
	 * @return 大きいなら正、等しいならゼロ、小さいなら負
	 */
	@Override
	public int compareTo(Card o) {

		// 等しいときの条件について調べる
		if (this.mark == Mark.JOKER && o.mark == Mark.JOKER) {
			return 0;
		} else if (this.number == o.number && this.mark.compare(o.mark) == 0) {
			return 0;
		}

		// ジョーカーの場合無条件で一番大きい数
		if (this.mark == Mark.JOKER) {
			return 1;
		} else if (o.mark == Mark.JOKER) {
			return -1;
		}

		// マークに関係なく数が大小で比較
		if (this.number > o.number) {
			return 1;
		} else if (this.number < o.number) {
			return -1;
		}

		// 数が等しく、マークの大小で比較
		if (this.mark.compare(o.mark) == 1) {
			return 1;
		} else {
			return -1;
		}

	}

	/**
	 * Cardコンストラクタ ジョーカー以外の場合はこちらを利用する
	 * 
	 * @param マークの種類
	 * @param カードの数字
	 */
	public Card(Mark mark, int number) {
		this.mark = mark;
		this.number = number;
	}

	/**
	 * Cardコンストラクタ　 ジョーカーの場合はこちらを利用する
	 * 
	 * @param マークの種類
	 */
	public Card(Mark mark) {
		this.mark = mark;
	}

	/**
	 * カードの数を返す
	 * 
	 * @return カードの数
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * カードのマークを返す
	 * 
	 * @return マークの種類
	 */
	public Mark getMark() {
		return this.mark;
	}

}
