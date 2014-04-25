/**
 * Java講座 中級3. カードとデッキ <br />
 * Mark クラス
 * 
 * @author Kazuki_Endo
 */

public enum Mark {
	CLUB(0), DIAMOND(1), HEART(2), SPADE(3), JOKER(4);

	// マークの大小を表す値
	private int rank;

	/**
	 * マークの大小関係を表す値を設定する クラブ、ダイヤ、ハート、スペードの順に小さい
	 * 
	 * @param rank
	 */
	private Mark(int rank) {
		this.rank = rank;
	}

	/**
	 * マークの大小関係を比較する
	 * 
	 * @param 比較対象となるマーク
	 * @return　比較対象より大きいなら正, 等しいならゼロ, 小さいなら負
	 */
	public int compare(Mark mark) {
		int result = this.rank - mark.rank;
		if (result > 0) {
			return 1;
		} else if (result == 0) {
			return 0;
		} else {
			return -1;
		}
	}
}
