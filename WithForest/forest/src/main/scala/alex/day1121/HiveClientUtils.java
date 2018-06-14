package alex.day1121;

import java.sql.*;
import java.util.logging.Logger;

/**
 * Created by zhiguang on 2017/11/21.
 */
public class HiveClientUtils {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive2://server01:10000/myhive";
    private static String user = "root";
    private static String password = "root";
    private static String sql = "";
    private static ResultSet res;

    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public static void showtables() throws SQLException, ClassNotFoundException {
        sql = "show tables";
        Connection conn = getConn();
        Statement statement =  conn.createStatement();
        res = statement.executeQuery(sql);
        if (res.next()) {
            System.out.println(res.getString(1));
            System.out.println(res.getString(1));
        }
    }

    public static void main(String[] args) throws Exception {
        showtables();
    }
}
