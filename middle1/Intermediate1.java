
/**
 * Java講座 中級1. 2進数変換クラス
 * @author Kazuki_Endo
 *
 */

public class Intermediate1 {
	
	/**
	 * １０進数の数を２進数に変換する
	 * @param ２進数に変換したい１０進数の数
	 * @return ２進数の文字列
	 */
	public static String toBinaryString(int number) {	
		// 0の場合は0を返す
		if(number == 0) {
			return "0";
		} else if (number < 0) {
		// 負の数の場合は例外を投げる
			throw new IllegalArgumentException();
		}
		
		StringBuilder binaryStr = new StringBuilder();
		while (true) {
			binaryStr.append(String.valueOf(number % 2));
			number = number / 2;
			if(number == 1){
				binaryStr.append(String.valueOf(number));
				break;
			}
		}
		
		return binaryStr.reverse().toString();
	}
	
}
