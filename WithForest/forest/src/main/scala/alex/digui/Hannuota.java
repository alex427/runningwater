package alex.digui;

/**
 * zhiguang
 * 汉诺塔问题的描述如下：有A, B, C 3跟柱子，在A上从下往上按照从小到大的顺序放着5个圆盘，以B为中介，把盘子全部移动到C上。
 * 移动过程中，要求任意盘子的下面要么没有盘子，要么只能有比它大的盘子。本实例将演示如何求解3阶汉诺塔问题。
 */
public class Hannuota {

    public static void main(String[] args) {
        int nDisks = 3;
        movedisk(nDisks, 'A', 'B', 'C');
    }
    
    public static void movedisk(int level, char from, char inter, char to) {
        if (level == 1) {
            System.out.println("从" + from + " 移动盘子" + level + " 号到" + to);
        } else {
            movedisk(level - 1, from, to, inter);
            System.out.println("从" + from + " 移动盘子" + level + " 号到" + to);
            movedisk(level - 1, inter, from, to);
        }
    }

}
