package alex.suanfa;

/**
 * Created by zhiguang on 2018/5/2.
 */
public class BinarySearchDemo {
    
    public static void main(String[] args){
        int[] arr = {1,9,3,12,7,8,3,4,65,22};
        QuickSort.quickSort(arr, 0, arr.length - 1);
        recursionBinarySearch(arr,8,0,9);
    }
    
    /**
     * 使用递归的二分查找 
     *title:recursionBinarySearch 
     *@param arr 有序数组 
     *@param key 待查找关键字 
     *@return 找到的位置
     */
    public static int recursionBinarySearch(int[] arr,int key,int low,int high){

        if(key < arr[low] || key > arr[high] || low > high){
            return -1;
        }

        int middle = (low + high) / 2;          //初始中间位置  
        if(arr[middle] > key){
            //比关键字大则关键字在左区域  
            return recursionBinarySearch(arr, key, low, middle - 1);
        }else if(arr[middle] < key){
            //比关键字小则关键字在右区域  
            return recursionBinarySearch(arr, key, middle + 1, high);
        }else {
            return middle;
        }

    }
}
