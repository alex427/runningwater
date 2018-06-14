package alex.digui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * zhiguang
 */
public class JosefRing2 {

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        int totalNum = 40;
        int cycleNum = 3;
        getTheQ(totalNum, cycleNum);
    }

    public static void getTheQ(int totalNum, int countNum) {
        // 一个集合, 装人
        List<Integer> team = new ArrayList<Integer>();
        for (int i = 1; i <= totalNum; i++) {
            team.add(i);
        }

        //报数者,初始为第一个人
        int k = 0;
        while (team.size() >0) {
            k = k + countNum;
            //第m人的索引位置
            k = k % (team.size()) - 1;
            // 判断是否到队尾
            if (k < 0) {
                System.out.println(team.get(team.size()-1));
                team.remove(team.size() - 1);
                k = 0;
            } else {
                System.out.println(team.get(k));
                team.remove(k);
            }
        }
    }
}
