/**
* Java講座 初級6. ヒストグラムクラス
* @author Kazuki Endo
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.NumberFormatException;

public class Histogram {

    // ヒストグラムの各データ
    private List<Integer> dataList; 

    /**
    * フィールドの初期化
    */
    private Histogram() {
        dataList = new ArrayList<Integer>();
    }

    /**
    * データを一つセットする
    * @param 追加したいデータ
    */
    public void addData(Integer data){
        dataList.add(data);
    }

    /**
    * 標準出力にヒストグラムを出力する
    */
    public void outputGraph() {
        int maxValue = Collections.max(dataList);
        int numberOfPoints = dataList.size();
        for (int i = maxValue ; i > 0 ; i--) {
            for (int j = 0 ; j < numberOfPoints ; j++) {
                Integer data = dataList.get(j);
                if (data >= i){
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String args[]) {
        // 引数がない場合は即終了
        if (args.length == 0){
            System.out.println("グラフにプロットする値を引数に指定してください。");
            System.out.println("例）java Histogram 1 2 3 3 2 1");
            return;
        }
        
        try {
            Histogram histogram = new Histogram();
            for (String arg : args){
                Integer number = Integer.valueOf(arg);
                // 引数の中に負の数が入っている場合は即終了
                if(number < 0) {
                    System.out.println("引数に指定できるのは正の数だけです。");
                    return;
                }
                histogram.addData(number);
            }
            histogram.outputGraph();
        } catch (NumberFormatException e) {
            System.out.println("引数に指定できるのは数値のみです。");
            return;
        }
    }
    
}