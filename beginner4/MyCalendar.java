/**
*	java 研修 初級.4 カレンダークラス
*	@author Kazuki_Endo
*/

import java.util.Scanner;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.util.Calendar;

public class MyCalendar {

    // カレンダークラス
    private Calendar calendar;

    /**
    * Calendarコンストラクタ
    * カレンダークラスの初期化と年と月をセットする
    * @param 表示させる年
    * @param 表示させる月
    */
    private MyCalendar(int year, int month) {
        calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month-1, 1);
    }

    /**
    * 標準出力にカレンダーを表示する
    */
    public void showCalendar() {
        System.out.println(calendar.get(Calendar.YEAR) + "年  " + calendar.get(Calendar.MONTH) + "月");
        System.out.println("日 月 火 水 木 金 土");
        System.out.println();

        //１日の曜日までスペースを開ける
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        for (int i = 0 ; i < dayOfWeek ; i++) {
            System.out.print("   ");
        }
        //月の最大日数まで出力する
        int days = calendar.getActualMaximum(Calendar.DATE);
        for (int i = 1 ; i <= days ; i++) {
            System.out.printf("%2d ",i);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            int weekOfDay = calendar.get(Calendar.DAY_OF_WEEK);
            if (weekOfDay == Calendar.SUNDAY) {
                System.out.println();
            }
        }
    }

    public static void main (String args[]) {
        System.out.println("カレンダーを出力します");
        Scanner sc = new Scanner(System.in);
        int year, month;
        try {
            //年の入力とバリデーション
            System.out.println("年を入力してください（例：2014）");
            String yearOfStr = sc.next();
            year = Integer.parseInt(yearOfStr);
            if (year <= 0) {
                System.out.println("年は１以上の自然数で入力してください。");
                return;
            }
            //月の入力とバリデーション
            System.out.println("月を入力してください（例：4）");
            String monthOfStr = sc.next();
            month = Integer.parseInt(monthOfStr);
            if (!(month >= Calendar.JANUARY && month <= Calendar.DECEMBER)) {
                System.out.println("月は１～１２の間で入力してください。");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("数値の形式が正しくありません。");
            return;
        }

        MyCalendar calendar = new MyCalendar(year, month);
        calendar.showCalendar();
    }
}
