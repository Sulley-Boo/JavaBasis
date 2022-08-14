package basisKnowledge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringToArray {

    // 输入一个字符串，如[[1,2],[3,4,5],[6]]
    // 将其解析到一个lists中去
    // 即：将字符串中的数据解析到一个二维数组中去
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        List<List<Integer>> lists = new ArrayList<>();
        String[] strs = str.split("],");
        for (String s : strs) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                if (Character.isDigit(s.charAt(i))) {
                    int k = i;
                    int num = 0;
                    while (k < s.length() && Character.isDigit(s.charAt(k))) {
                        num = num * 10 + s.charAt(k) - '0';
                        k++;
                    }
                    list.add(num);
                    i = k - 1;
                }
            }
            lists.add(new ArrayList<>(list));
        }
        System.out.println(lists);
    }
}
