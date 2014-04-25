/**
* 初級5 エラトステネス篩いクラス
* @author Kazuki Endo
*/

import java.util.*;
import java.io.*;

public class Eratosthenes {

    // 篩いにかけるリスト
    private List<Integer> searchList;

    // 素数のリスト
    private List<Integer> primeList;

    /**
    * Eratosthenes コンストラクタ
    * フィールドの初期化を行う
    */
    private Eratosthenes() {
        searchList = new ArrayList<Integer>();
    }

    /**
    * 篩いにかける値を追加する
    * @param 追加する値 (2以上の数)
    */
    public void addSearchNumber(int number) {
        if(number <= 1) {
            return;
        }
        searchList.add(new Integer(number));
        Collections.sort(searchList);
    }

    /**
    * 標準出力に素数の一覧を表示する
    */
    public void showPrimeNumbers() {
        for (Integer primeNumber : primeList) {
            System.out.print(primeNumber + " ");
        }
    }

    /**
    * 探査リストをエラトステネスの篩いにかける
    */
    public void doFiltering() {
        // 探査リストのコピーを作って、コピーに対して篩いを実行する
        List<Integer> numbers = new ArrayList<Integer>(searchList);
        primeList = new ArrayList<Integer>();
        Integer max = numbers.get(numbers.size() - 1);
        double sqrtNum = Math.sqrt(max);
        while (true) {
            Integer number = numbers.get(0);
            if (number >= sqrtNum) {
                break;
            }
            numbers.remove(0);
            primeList.add(number);
            int index = 0;
            Integer lastNum = numbers.get(numbers.size() - 1);
            while (true) {                
                Integer targetNum = numbers.get(index);
                if (targetNum % number == 0) {
                    numbers.remove(index);
                } else {
                    index++;
                }
                if (targetNum.equals(lastNum)) {
                    break;
                }
            }
        }
        // 探査リストに残った数を素数リストに追加する
        for(Integer number : numbers){
            primeList.add(number);
        }
    }

    public static void main(String[] args) {
        System.out.println("素数リストを出力するプログラムです。");
        System.out.println("リストの最大値を整数で入力してください。");
        
        int max;
        //最大値の入力とバリデーション
        try {
            Scanner sc = new Scanner(System.in);
            if(!sc.hasNextInt()){
                System.out.println("不正な値が入力されました。");
                return;
            }
            max = sc.nextInt();
            if(max < 0){
                System.out.println("自然数を入力してください。");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("不正な値が入力されました。");
            return;
        }

        //最大値までの自然数を篩いにかける
        Eratosthenes eratosthenes = new Eratosthenes();
        for(int i = 1 ; i <= max ; i++){
            eratosthenes.addSearchNumber(new Integer(i));
        }
        eratosthenes.doFiltering();
        eratosthenes.showPrimeNumbers();
    }
}