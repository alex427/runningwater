package alex.digui;

/**
 * zhiguang
 */
public class ChengfaBiao {
    public static void main(String [] args){
        m(9);
    }

    public static void m(int i) {
        if (i == 1) {
            System.out.println("1*1=1 ");
        } else {
            m(i - 1);
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + j * i + " ");
            }
            System.out.println();
        }
    }
}
