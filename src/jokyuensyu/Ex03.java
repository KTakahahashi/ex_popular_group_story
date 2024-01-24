package jokyuensyu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ex03 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/db";
        String user = "postgres";
        String password = "postgres";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "null";

        try {
            con = DriverManager.getConnection(url, user, password);
            sql = "SELECT m.name"+
            ",m.birth_day"+
            ",m.gender"+
            ",c.name "+
            "FROM members m "+
            "join colors c "+
            "on m.color_id=c.id "+
            "ORDER BY m.id;";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String birth_day = rs.getString("birth_day");
                String gender =rs.getString("gender");
                String color_name = rs.getString("name");

                System.out.print("m.name=" + name);
                System.out.print(" m.birth_day=" + birth_day);
                System.out.print(" m.gender"+gender);
                System.out.println(" c.name=" + color_name);
                System.out.println();

            }
        } catch (SQLException ex) {
            System.err.println("SQLの例外が発生しました");
            System.err.println("発生したSQLは「" + sql + "」です");
            ex.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pstmt != null) {
                    pstmt.close();

                }
                if (rs != null) {
                    rs.close();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
