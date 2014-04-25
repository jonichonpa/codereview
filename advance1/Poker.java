import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Java講座 上級1. ポーカー <br />
 * Poker クラス
 * 
 * @author Kazuki_Endo
 * 
 */

public class Poker {

	/** 現在のデッキ */
	private Deck deck = new Deck();

	/** 現在の手札 */
	private List<Card> cardList = new ArrayList<Card>();

	public static void main(String[] args) {
		Poker poker = new Poker();
		poker.start();
	}

	/**
	 * ポーカーを開始する。<br />
	 * 1. 初めにデッキから５枚カードを引く。<br />
	 * 2. ユーザーから指定された番号のカードを捨て、捨てた枚数分カードを引く。<br />
	 * 3. 最後に持っている５枚の手札で役を判定する。<br />
	 */
	public void start() {
		// 初めに5枚引く
		this.cardList = this.deck.pullCards(5);

		System.out.println("交換するカードの番号を入力してください(例：135）。");
		System.out.println("0を入力するとカードを交換しません。");
		showHands();

		List<Integer> numList;
		// 正しい入力があるまで入力処理を繰り返す
		while (true) {
			try {
				numList = inputNumbers();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				continue;
			}
			break;
		}

		// カードの入れ替え
		updateHands(numList);
		showHands();

		// 判定
		judge();
	}

	/**
	 * 現在の手札を表示する。
	 */
	private void showHands() {
		int i = 1;
		for (Card card : this.cardList) {
			System.out.println(i + ":" + card.toString());
			i++;
		}
	}

	/**
	 * 入力処理
	 * 
	 * @return 入力された各数が格納されたリスト
	 * @throws InputMismatchException
	 *             誤った入力をしたとき
	 * 
	 */
	private List<Integer> inputNumbers() {
		int input = 0;
		Scanner scanner = new Scanner(System.in);
		try {
			input = scanner.nextInt();
		} catch (InputMismatchException e) {
			throw new InputMismatchException("入力は数値のみです。");
		}

		// 入力されたデータの桁数チェック
		int digit = String.valueOf(input).length();
		if (digit > 5) {
			throw new InputMismatchException("５枚以上は交換できません。");
		}

		// 桁数分だけ要素数を確保
		List<Integer> numbers = new ArrayList<Integer>();
		while (input > 0) {
			Integer number = new Integer(input % 10);
			if (numbers.contains(number)) {
				throw new InputMismatchException("入力に重複があります。");
			} else if (number < 0 || number > 5) {
				throw new InputMismatchException("1から５の間で入力してください");
			} else {
				numbers.add(number);
			}
			input /= 10;
		}

		return numbers;
	}

	/**
	 * カードの入れ替え
	 * 
	 * @param 入れ替える番号のリスト
	 */
	private void updateHands(List<Integer> numList) {
		for (int num : numList) {
			try {
				cardList.remove(num - 1);
				Card card = this.deck.pullCard();
				cardList.add(num - 1, card);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ポーカーの役を判定する
	 */
	private void judge() {
		// 役を判定する前に必ずソートを行うこと！
		Collections.sort(this.cardList);

		String pokerHandStr;
		if (isRoyalStraightFlush()) {
			pokerHandStr = PokerHand.ROYAL_STRAIGHT.getPokerHandName();
		} else if (isStraightFlush()) {
			pokerHandStr = PokerHand.STRAIGHT_FLUSH.getPokerHandName();
		} else if (isStraight()) {
			pokerHandStr = PokerHand.STRAIGHT.getPokerHandName();
		} else if (isFlush()) {
			pokerHandStr = PokerHand.FLUSH.getPokerHandName();
		} else {
			String pairs = checkPairs();
			if (pairs.equals("0001") || pairs.equals("0010")
					|| pairs.equals("0100") || pairs.equals("1000")) {
				pokerHandStr = PokerHand.ONE_PAIR.getPokerHandName();
			} else if (pairs.equals("0101") || pairs.equals("1001")
					|| pairs.equals("1010")) {
				pokerHandStr = PokerHand.TWO_PAIR.getPokerHandName();
			} else if (pairs.equals("0011") || pairs.equals("0110")
					|| pairs.equals("1100")) {
				pokerHandStr = PokerHand.THREE_CARD.getPokerHandName();
			} else if (pairs.equals("1011") || pairs.equals("1101")) {
				pokerHandStr = PokerHand.FULL_HOUSE.getPokerHandName();
			} else if (pairs.equals("0111") || pairs.equals("1110")) {
				pokerHandStr = PokerHand.FOUR_CARD.getPokerHandName();
			} else {
				pokerHandStr = PokerHand.NO_PAIR.getPokerHandName();
			}
		}

		System.out.println("役は " + pokerHandStr + " です。");

	}

	/**
	 * ロイヤルストレートフラッシュかどうか判定する。
	 * 
	 * @return ロイヤルストレートフラッシュならtrue, そうでないならfalse
	 */
	private boolean isRoyalStraightFlush() {
		// ストレートフラッシュでないならナシ
		if (!isStraightFlush()) {
			return false;
		}
		// ストレートフラッシュで、数字が1,10,11,12,13の順か確かめる
		int[] royalNums = { 1, 10, 11, 12, 13 };
		int i = 0;
		for (Card card : cardList) {
			if (card.getNumber() != royalNums[i]) {
				return false;
			}
			i++;
		}
		return true;
	}

	/**
	 * ストレートフラッシュかどうか判定する。
	 * 
	 * @return ストレートフラッシュならtrue, そうでないならfalse
	 */
	private boolean isStraightFlush() {
		return isFlush() && isStraight();
	}

	/**
	 * ストレートかどうか判定する。
	 * 
	 * @return ストレートならtrue, そうでないならfalse
	 */
	private boolean isStraight() {
		// Collections.sort(cardList);
		List<Card> copyCardList = new ArrayList<Card>(cardList);
		Card firstCard = copyCardList.get(0);
		Card lastCard = copyCardList.get(cardList.size() - 1);
		// 10,J,Q,K,A となった時の為の処理
		if (firstCard.getNumber() == 1 && lastCard.getNumber() == 13) {
			Card newCard = new Card(firstCard.getMark(), 14);
			copyCardList.remove(0);
			copyCardList.add(newCard);
		}
		firstCard = copyCardList.get(0);
		int i = 0;
		for (Card card : copyCardList) {
			if (firstCard.getNumber() + i != card.getNumber()) {
				return false;
			}
			i++;
		}

		return true;
	}

	/**
	 * フラッシュかどうか判定する
	 * 
	 * @return フラッシュならtrue, そうでないならfalse
	 */
	private boolean isFlush() {
		Mark mark = cardList.get(0).getMark();
		for (Card card : cardList) {
			if (mark.compareWith(card.getMark()) != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * カードの前後関係を調べる <br />
	 * 各数字の前後関係が等しかったら0で、違うなら1になる。 <br />
	 * (例) <br />
	 * K,K,1,1,5 だったら ”1010" を返す <br />
	 * K,K,K,Q,Q だったら "1101" を返す <br />
	 * 2,2,5,3,3 だったら "1001" を返す <br />
	 * 
	 * 返りの文字列でノーペア、ワンペア、ツーペア、スリーカード、フルハウス、フォーカードが判定できる。<br />
	 * ノーペア: "0000" <br />
	 * ワンペア:　"0001","0010","0100","1000" <br />
	 * ツーペア: "0101","1001","1010" <br />
	 * スリーカード: "0011", "0110", "1100" <br />
	 * フルハウス: "1011", "1101" <br />
	 * フォーカード: "0111", "1110" <br />
	 * 
	 * @return カードの前後関係を表した文字列
	 */
	private String checkPairs() {
		boolean[] bitSet = new boolean[4];
		Arrays.fill(bitSet, false);
		// カードの前後関係を調べる
		int i = 0;
		Card previousCard = null;
		for (Card card : cardList) {
			if (previousCard == null) {
				previousCard = card;
			} else {
				if (previousCard.getNumber() == card.getNumber()) {
					bitSet[i] = true;
				}
				previousCard = card;
				i++;
			}
		}

		// 前後関係が等しい場合は1,そうでない場合は0になる文字列を構成する
		StringBuilder strBuilder = new StringBuilder();
		for (boolean bit : bitSet) {
			if (bit) {
				strBuilder.append("1");
			} else {
				strBuilder.append("0");
			}
		}

		return strBuilder.toString();
	}
}
