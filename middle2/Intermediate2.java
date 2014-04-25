/**
 * Java講座 中級2. 重複削除クラス
 * @author Kazuki_Endo
 *
 */

import java.util.ArrayList;
import java.util.List;

public class Intermediate2 {
	public static int[] uniq(int[] numbers) {
		// 要素が 0 の場合
		if(numbers.length == 0){
			return numbers;
		}
		
		List<Integer> numberList = new ArrayList<Integer>();
		int previousVal = 0;
		for(int number : numbers) {
			// 連続して同じ値の要素は除く
			if(previousVal != number) {
				numberList.add(number);
			}
			previousVal = number;
		}
		
		int resultNumbers[] = new int[numberList.size()];
		int i = 0;
		for(Integer number : numberList){
			resultNumbers[i++] = number;
		}
		
		return resultNumbers;
	}
}
