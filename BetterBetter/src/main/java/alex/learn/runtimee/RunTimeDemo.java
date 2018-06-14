package alex.learn.runtimee;

import java.io.IOException;

public class RunTimeDemo {

    public static void main(String [] args) throws Exception {
        Runtime r = Runtime.getRuntime();
        //Process p = r.exec("notepad");
        //p.waitFor();
        //System.out.println("Notepad returned " + p.exitValue());

        long mem1,mem2;
        String someints[] = new String[100000];
        long tt = r.totalMemory();
        System.out.println("Total memory is ：" + tt/1024/1024/10);
        mem1 = r.freeMemory();
        System.out.println("Initial free is : " + mem1);

        //这里显示的内存信息, 和机器的任务管理器显示的信息, 怎么对应不上????
        //request garbage collection
        r.gc();
        mem1 = r.freeMemory();
        System.out.println("no work, free memory after garbage collection : " + mem1/1024/1024/10);

        //allocate mem for object
        for(int i=0; i<100000; i++) {
            someints[i] = "aaaaaaaaaaaaaaaaaaaaaaaaaaddddddddddddddddddddddddddddddddddddddddaaaaaaaaaaaaaaaaa"+new Integer(i) ;
        }
        mem2 = r.freeMemory();
        System.out.println("Free memory after allocation : " + mem2/1024/1024/10);
        System.out.println("Memory used by allocation : " +(mem1-mem2)/1024/1024/10);

        Thread.sleep(30000);

        //kill object to release
        for(int i=0; i<100000; i++) {
            someints = null;
        };

        Thread.sleep(30000);
        //request gc and get mem back
        r.gc();
        mem2 = r.freeMemory();
        System.out.println("Free memory after collecting " + "discarded integers : " + mem2/1024/1024/10);
    }
}
