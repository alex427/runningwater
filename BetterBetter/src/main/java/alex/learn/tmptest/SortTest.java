package alex.learn.tmptest;

import java.util.Arrays;

public class SortTest {
    public static void main(String [] args){
        int[] a = {11,15,13,19,18,3,26,5,7,12,17,14,6,27,22,20,9,4,25,8,21,10,16};
        int[] b = {25,8,24,16,20,28,9,3,21,11,4,13,17,12,15,5,19,10,14,6,27,22,23};
        bubbleSort(a);
        bubbleSort(b);
    }

    public static void bubbleSort(int[] numbers) {
        int temp = 0;
        int size = numbers.length;
        for(int i = 0 ; i < size-1; i ++) {
            for(int j = 0 ;j < size-1-i ; j++) {
                //交换两数位置
                if(numbers[j] > numbers[j+1]){
                    temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(numbers));
    }

}
