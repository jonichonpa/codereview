import java.util.Scanner;
import java.io.IOException;
import java.lang.NumberFormatException;

/**
 * java研修 初級3.じゃんけん用クラス
 * @author Kazuki_Endo
 */

public class RPS {

	/** 勝敗の種類 */
 	private static final int NO_JUDGE = 0, WIN = 1, LOSE = 2, DRAW = 3;

	/** じゃんけんの手の種類 */
	private static final int NO_HAND = 0, ROCK = 1, SCISSORS = 2, PAPER = 3;

	/** じゃんけんの手の文字列 */
	private static final String[] HANDS = {"", "グー", "チョキ", "パー"};

	/*+ プレーヤーの手 */
	private int playerHand;

	/** コンピュータの手 */
	private int computerHand;

	/**
	* RPS コンストラクタ
	* フィールドの初期化を行う
	*/
	public RPS() {
		this.playerHand = NO_HAND;
		this.computerHand = NO_HAND;
	}

	/**
	* 1 ~ 3の中からランダムに取得
	* @return ROCK or SCISSORS or PAPER
	*/
	private int getHandOfComputer() {
		return (int)(Math.random() * 3 + 1);
	}

	/**
	* コンピュータとじゃんけんをする
	* @param プレイヤーの手
	* @return 勝ちの場合 WIN , 負けの場合 LOSE , あいこの場合 DRAW
	*/
	public int play(int playerHand) {
		this.playerHand = playerHand;
		this.computerHand = getHandOfComputer();

		if ((this.playerHand == ROCK && this.computerHand == SCISSORS) 
			|| (this.playerHand == SCISSORS && this.computerHand == PAPER) 
			|| (this.playerHand == PAPER && this.computerHand == ROCK)) {
			return WIN;
		} else if (this.playerHand == this.computerHand) {
			return DRAW;
		} 
		return LOSE;
	}

	/**
	* プレーヤーの現在の手を返す
	* @return NONE_HAND or ROCK or SCISSORS or PAPER
	*/
	public int getPlayerHand() {
		return this.playerHand;
	}

	/**
	* コンピュータの現在の手を返す
	* @return NONE_HAND or ROCK or SCISSORS or PAPER
	*/
	public int getComputerHand() {
		return this.computerHand;
	}

	/**
	* 引数の文字列が数字の1,2,3であるか調べる
	* @return 1,2,3である場合はtrue
	*/
	private static boolean is123(String numStr) {
		if ("1".equals(numStr) || "2".equals(numStr) || "3".equals(numStr)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String args[]) {
		System.out.println("じゃんけんをしましょう！");
		System.out.println("1: グー、2: チョキ、3: パー です。");
		System.out.println("じゃーんけーん・・");

		RPS rps = new RPS();
		Scanner scanner = new Scanner(System.in);
		int result = NO_JUDGE;
		int playerInput;
		while (true) {
			System.out.print("出す手を入力 => ");

			// 入力処理
			try {
				String rawInput = scanner.next();
				if (!RPS.is123(rawInput)) {
					continue;
				}
				playerInput = Integer.valueOf(rawInput);
			} catch (NumberFormatException e) {
				continue;
			}

			// もしあいこならかけ声が変わる
			if (result == RPS.DRAW) {
				System.out.println("しょ！");
			} else {
				System.out.println("ぽん！");
			}

			// じゃんけんの結果の出力
			result = rps.play(playerInput);
			System.out.println("あなた：" + HANDS[rps.getPlayerHand()] + "、コンピュータ：" + HANDS[rps.getComputerHand()]);	
			if (result == RPS.WIN) {
				System.out.println("あなたの勝ちです！");
				break;
			} else if (result == RPS.LOSE) {
				System.out.println("あなたの負けです！");
				break;
			} else {
				System.out.println("あーいこーで・・");
			}
		}
	}

}