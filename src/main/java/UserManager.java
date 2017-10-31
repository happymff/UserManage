/**
 * Created by mff on 2017/10/31.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 采用单例管理用户
 *
 * @author mff
 */
public class UserManager {
    private static UserManager instance = new UserManager();

    private UserManager() {
    }

    /**
     * 提供一个入口方法
     *
     * @return
     */
    public static UserManager getInstance() {
        return instance;
    }
    /**
     * 取得用户列表
     * @return
     */
    public List<User> getUsers(){//如果不是static 我们取用户的时候可能要new 一个新的用户
        String sql = "select * from t_user";
        Connection conn = null;
        Statement pstmt = null;
        ResultSet rs = null;
        User user = null;
        List<User> users = new ArrayList<User>();
        try {
            // Statement,createStatement用来执行SQL语句
            conn = MysqlConnect.connectMysql();
            pstmt = conn.createStatement();
            rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setContactTel(rs.getString("contact_tel"));
                user.setEmail(rs.getString("email"));
                user.setCreateDate(rs.getTimestamp("create_date"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlConnect.close(rs);
            //MysqlConnect.close(pstmt);
            MysqlConnect.close(conn);
        }
        return users;
    }


    /**
     * 添加用户的方法
     *
     * @param user
     */
    public void addUser(User user) {
        String sql = "insert into t_user (user_id, user_name, password, contact_tel, email, create_date) values (?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = MysqlConnect.connectMysql();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getContactTel());
            pstmt.setString(5, user.getEmail());
            pstmt.setTimestamp(6, new Timestamp(new Date().getTime()));//能保存年月日 时分秒
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlConnect.close(pstmt);
            MysqlConnect.close(conn);
        }
    }

    /**
     * 修改用户
     *
     * @param user
     */
    public void modifyUser(User user) {
        StringBuilder sbSql = new StringBuilder();
        sbSql.append("update t_user ")
                .append("set    user_name   = ?, ")
                .append("password    = ?, ")
                .append("contact_tel = ?, ")
                .append("email       = ? ")
                .append("where  user_id     = ? ");
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = MysqlConnect.connectMysql();
            pstmt = conn.prepareStatement(sbSql.toString());
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getContactTel());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlConnect.close(pstmt);
            MysqlConnect.close(conn);
        }
    }

    /**
     * 根据用户代码删除
     *
     * @param userId
     */
    public void delUser(String userId) {
        String sql = "delete from t_user where user_id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = MysqlConnect.connectMysql();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlConnect.close(pstmt);
            MysqlConnect.close(conn);
        }
    }

    /**
     * 根据用户代码查询
     *
     * @param userId
     * @return 如果存在返回User对象，否则返回null
     */
    public User findUserById(String userId) {
        String sql = "select user_id, user_name, password, contact_tel, email, create_date from t_user where user_id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = MysqlConnect.connectMysql();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setContactTel(rs.getString("contact_tel"));
                user.setEmail(rs.getString("email"));
                user.setCreateDate(rs.getTimestamp("create_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlConnect.close(rs);
            MysqlConnect.close(pstmt);
            MysqlConnect.close(conn);
        }
        return user;
    }

    /**
     * 分页查询
     *
     * @param pageNo   第几页
     * @param pageSize 每页多少条数据
     * @return pageModel
     */
    public PageModel<User> findUserList(int pageNo, int pageSize) {
        StringBuffer sbSql = new StringBuffer();
        sbSql.append("select user_id, user_name, password, contact_tel, email, create_date ")
                .append("from ")
                .append("( ")
                .append("select rownum rn, user_id, user_name, password, contact_tel, email, create_date ")
                .append("from ")
                .append("( ")
                .append("select user_id, user_name, password, contact_tel, email, create_date from t_user where user_id <> 'root' order by user_id ")
                .append(")  where rownum <= ? ")
                .append(")  where rn > ? ");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PageModel<User> pageModel = null;
        try {
            conn = MysqlConnect.connectMysql();
            pstmt = conn.prepareStatement(sbSql.toString());
            pstmt.setInt(1, pageNo * pageSize);
            pstmt.setInt(2, (pageNo - 1) * pageSize);
            rs = pstmt.executeQuery();
            List<User> userList = new ArrayList<User>();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setContactTel(rs.getString("contact_tel"));
                user.setEmail(rs.getString("email"));
                user.setCreateDate(rs.getTimestamp("create_date"));
                userList.add(user);
            }
            pageModel = new PageModel<User>();
            pageModel.setList(userList);
            pageModel.setTotalRecords(getTotalRecords(conn));
            pageModel.setPageSize(pageSize);
            pageModel.setPageNo(pageNo);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlConnect.close(rs);
            MysqlConnect.close(pstmt);
            MysqlConnect.close(conn);
        }
        return pageModel;
    }

    /**
     * 取得总记录数
     *
     * @param conn
     * @return
     */
    private int getTotalRecords(Connection conn)
            throws SQLException {
        String sql = "select count(*) from t_user where user_id <> 'root'";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } finally {
            MysqlConnect.close(rs);
            MysqlConnect.close(pstmt);
        }
        return count;
    }
}
