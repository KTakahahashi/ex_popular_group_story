package jokyuensyu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex01 {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/db";
        String user = "postgres";
        String password = "postgres";

        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "null";

        try {
            con = DriverManager.getConnection(url, user, password);
            sql = "drop table if exists colors;" +
                    "create table colors(" +
                    "id integer primary key," +
                    "name text" +
                    ");" +
                    "drop table if exists members;" +
                    "create table members(" +
                    "id serial primary key," +
                    "name text not null," +
                    "birth_day date," +
                    "gender varchar(1)," +
                    "color_id integer references colors(id)" +
                    ");";
            pstmt = con.prepareStatement(sql);
            int numOfUpdated = pstmt.executeUpdate();
            System.out.println(numOfUpdated + "件のテーブルを追加しました");
        } catch (SQLException ex) {
            System.out.println("SQL=" + sql);
            ex.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
