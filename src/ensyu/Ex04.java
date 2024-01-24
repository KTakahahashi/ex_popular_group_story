package ensyu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex04 {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/db";
        String user = "postgres";
        String password = "postgres";

        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "null";

        try {
            con = DriverManager.getConnection(url, user, password);
            sql = "update members set name = '高橋海理'" + 
                    ",birth_day='1996-12-05'" + 
                    ",gender='男'" + 
                    ",color_id=6" + 
                    "where id = 6;";
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
