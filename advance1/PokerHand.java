/**
 * Java講座 上級1. ポーカー　<br />
 * PokerHand クラス
 * 
 * @author Kazuki_Endo
 * 
 */

public enum PokerHand {

	/**
	 * 役の種類
	 */
	NO_PAIR("ノーペア"), ONE_PAIR("ワンペア"), TWO_PAIR("ツーペア"), THREE_CARD("スリーカード"), STRAIGHT(
			"ストレート"), FLUSH("フラッシュ"), FULL_HOUSE("フルハウス"), FOUR_CARD("フォーカード"), STRAIGHT_FLUSH(
			"ストレートフラッシュ"), ROYAL_STRAIGHT("ロイヤルストレートフラッシュ");

	/** 役の表示名 */
	private String label;

	/**
	 * PokerHand コンストラクタ <br />
	 * 役の日本語名をセットする。
	 * 
	 * @param 役の日本語名
	 */
	private PokerHand(String label) {
		this.label = label;
	}

	/*
	 * 役の日本語名を返す。
	 * 
	 * @return 役の日本語名
	 */
	public String getPokerHandName() {
		return this.label;
	}

}
