package UserManager; /**
 * Created by mff on 2017/5/15.
 */

import java.sql.*;

public class MysqlConnect {
    public static Connection connectMysql() {
        // 驱动程序名
       // String driver = "com.mysql.jdbc.Driver";
        String driver = "com.mysql.cj.jdbc.Driver";
        // URL指向要访问的数据库,并设置编码方式为UTF-8
        String url = "jdbc:mysql://localhost/myDataBase?useUnicode=true&characterEncoding=UTF-8";
        // MySQL配置时的用户名
        String user = "root";
        // MySQL配置时的密码
        String password = "1234";
        Connection conn = null;
        try {
            // 加载驱动程序
            Class.forName(driver);
            // 连接数据库
            conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");
        } catch (ClassNotFoundException e) {
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

   /**
     * 关闭conn
     *
     * @param conn
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭PreparedStatement
     *
     * @param pstmt
     */
    public static void close(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 关闭ResultSet
     * @param rs
     */
    public static void close(ResultSet rs ) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        //测试连接数据库
        Connection connection=connectMysql();


    }
}