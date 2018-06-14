package alex.learn.tmptest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhiguangai on 2017/11/20.
 */
public class MyTestUntils {

     Map<Integer, Integer> blSaleMap = new  HashMap<Integer, Integer>();


     final Map<Integer, List<Integer>> saleBlMap = new HashMap<Integer, List<Integer>>();
    Map<Integer, List<Integer>> mymap = new HashMap<Integer, List<Integer>>();

    public Map<Integer, List<Integer>> initSaleBlMap() {
        blSaleMap.put(1,10);
        blSaleMap.put(2,20);
        blSaleMap.put(3,20);
        blSaleMap.put(4,40);
        blSaleMap.put(5,60);
        blSaleMap.put(6,50);
        blSaleMap.put(7,60);
        System.out.println(blSaleMap.keySet());
        System.out.println(blSaleMap);

        for (Integer blpackageid : blSaleMap.keySet()) {
            Integer salePacakgeid = blSaleMap.get(blpackageid);  // 4-->40
            List<Integer> blList = saleBlMap.get(salePacakgeid);  // null
            if (blList == null) {
                blList = new ArrayList<Integer>(); //
                saleBlMap.put(salePacakgeid, blList);///(40, blist)
            }
            blList.add(blpackageid);  //[4]

        }
        return saleBlMap;
    }

    public static void main(String[] args) {
        MyTestUntils mm  = new  MyTestUntils();
        Map<Integer, List<Integer>> map = mm.initSaleBlMap();
        System.out.println(map);
    }
}
