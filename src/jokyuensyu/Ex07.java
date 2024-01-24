package jokyuensyu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex07 {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/db";
        String user = "postgres";
        String password = "postgres";

        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "null";

        try {
            con = DriverManager.getConnection(url, user, password);

            // トランザクション開始
            con.setAutoCommit(false);

            try {
                // ここにトランザクション内で行いたい処理を追加

                sql = "DELETE FROM members;";
                pstmt = con.prepareStatement(sql);
                int numOfUpdated = pstmt.executeUpdate();
                System.out.println(numOfUpdated + "件のレコードを削除しました");

                // トランザクションのコミット
                con.commit();
            } catch (SQLException ex) {
                // トランザクション内でエラーが発生した場合はロールバック
                con.rollback();
                // throw ex; // エラーを再スローしてプログラムを終了
            } finally {
                // トランザクションモードを元に戻す
                con.setAutoCommit(true);
            }
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
