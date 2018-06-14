package alex.digui;

/**
 * zhiguang
 */
public class SafeQueen {

    public static void main(String[] args) {
        Queen q = new Queen();
        System.out.println("-------八皇后问题-------");
        q.num = 0; // 方案初始化
        for (int i = 0; i < 8; i++)
            // 置所有列为安全
            q.col[i] = true;
        for (int j = 0; j < 16; j++)
            // 置所有对角线为安全
            q.diagonal[j] = q.undiagonal[j] = true;
        q.solve(1);
    }
}

//Queen类，显示所有的摆放方案
class Queen {

    int num; // 记录方案数
    int[] queenline = new int[8]; // 记录8个皇后所占用的列号
    boolean[] col = new boolean[8]; // 列安全标志
    boolean[] diagonal = new boolean[16]; // 对角线安全标志
    boolean[] undiagonal = new boolean[16]; // 反对角线安全标志

    void solve(int i) {
        for (int j = 0; j < 8; j++) {
            if (col[j] && diagonal[i - j + 7] && undiagonal[i + j]) {
                // 表示第i行第j列是安全的可以放皇后
                queenline[i - 1] = j + 1;
                col[j] = false; // 修改安全标志
                diagonal[i - j + 7] = false;
                undiagonal[i + j] = false;
                if (i < 8) // 判断是否放完8个皇后
                {
                    solve(i + 1); // 未放完8个皇后则继续放下一个
                }
                else // 已经放完8个皇后
                {
                    num++;
                    System.out.println("\n皇后摆放第" + num + "种方案:");
                    int[] lieHao = new int[9];
                    for (int ii = 0; ii < 8; ii++)
                        lieHao[ii] = queenline[ii];
                    for(int row = 0;row < 8;row++){
                        System.out.print("|");
                        for(int column = 0;column < 8;column++){
                            if(lieHao[row] == column + 1)
                                System.out.print("Q|");
                            else
                                System.out.print(" |");
                        }
                        System.out.println();
                    }
                }
                col[j] = true; // 修改安全标志，回溯
                diagonal[i - j + 7] = true;
                undiagonal[i + j] = true;
            }
        }
    }
}