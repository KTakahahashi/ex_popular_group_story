package jokyuensyu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex02 {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/db";
        String user = "postgres";
        String password = "postgres";

        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "null";

        try {
            con = DriverManager.getConnection(url, user, password);
            sql = "insert into colors(id,name)"+
            "values(1,'blue')"+
            ",(2,'red')"+
            ",(3,'green')"+
            ",(4,'yellow')"+
            ",(5,'purple')"+
            ",(6,'orange');";
            pstmt = con.prepareStatement(sql);
            int numOfUpdated = pstmt.executeUpdate();
            System.out.println(numOfUpdated + "件のレコードを追加しました");
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
