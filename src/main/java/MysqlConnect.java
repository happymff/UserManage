/**
 * Created by mff on 2017/5/15.
 */

import java.sql.*;

public class MysqlConnect {
    public static Connection connectMysql() {
        // 驱动程序名
       // String driver = "com.mysql.jdbc.Driver";
        String driver = "com.mysql.cj.jdbc.Driver";
        // URL指向要访问的数据库名students
        String url = "jdbc:mysql://localhost/myDataBase";
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

//    public static PreparedStatement insert(String s, Connection conn) {
//        String[] strings = s.split("\n");
//        PreparedStatement statement1 = null;
//        try {
//            for (String insertString : strings) {
//                // 要执行的SQL语句
//                String sql1 = "insert into userInfo values(" + insertString + ")";
//                // Preparestatement用来执行SQL的insert语句
//                statement1 = conn.prepareStatement(sql1);
//                int count = statement1.executeUpdate();
//                if (count > 0) {
//                    System.out.println("插入成功！");
//                } else {
//                    System.out.println("插入失败！");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return statement1;
//    }
//
//    public static ResultSet select(Connection conn, String userid, String username) {
//        ResultSet rs = null;
//        try {
//            // statement用来执行SQL语句
//            Statement statement = conn.createStatement();
//            // 要执行的SQL语句
//            String sql = "select * from userInfo";
//            // 结果集
//            rs = statement.executeQuery(sql);
//            while (rs.next()) {
//                // 选择runResult这列数据
//                String userid1 = rs.getString(userid);
//                String username1 = rs.getString(username);
//                // 输出结果
//                System.out.println("userid:" + "\t" + userid1 + "\t" + "username:" + "\t" + username1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return rs;
//
//    }
//
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
        Connection connection=connectMysql();


    }
}