package alex.learn.dated;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
/**
 * Created by zhiguangai on 2017/9/8.
 */
public class DateParse {

    /*public static void main(String[] args){
        String ds = "20170906";
        String dd = ds.substring(0,4)+"-"+ds.substring(4,6)+"-"+ds.substring(6,8);
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date=sdf.parse(dd);
            //获取昨天
            cal.setTime(date);
            cal.add(Calendar.DATE,-1);
            String yy = sdf.format(cal.getTime());
            String yyy = yy.substring(0,4)+yy.substring(5,7)+yy.substring(8,10);
            System.out.println(date);
            System.out.println(yyy);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //java.util.Date date=new java.util.Date();
        //String str=sdf.format(date);

    }*/
}
