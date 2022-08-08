package IOStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScannerIntance {

    /**
     * 如何接收键盘的输入，将输入添加到list，按回车键表示接收完毕，停止接收。
     * 在笔试时可能写输入时只要你输入数组元素，按回车表示数组输入完毕。
     * 此时该如何用list接收输入？
     */

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        Scanner sc2 = new Scanner(line);
        while (sc2.hasNextInt()) {
            list.add(sc2.nextInt());
        }

        System.out.println(list);
    }
}
