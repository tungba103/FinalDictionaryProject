package Dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MysqlManagagement {
    private static String username = "root";
    private static String password = "";
    private static String URL = "jdbc:mysql://localhost:3306/dictionary";

    public static String getSynonym(String wordRequest) {
        String result = "";
        try {
            Connection conn = getConnection(URL, username, password);
            PreparedStatement stmt = conn.prepareStatement("select * from t_dictionary_synonyms where word = ?");
            stmt.setString(1, wordRequest);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = rs.getString(3).replace(",","\n");
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static String getAntonym(String wordRequest) {
        String result = "";
        try {
            Connection conn = getConnection(URL, username, password);
            PreparedStatement stmt = conn.prepareStatement("select * from t_dictionary_ant where word = ?");
            stmt.setString(1, wordRequest);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = rs.getString(3).replace(",","\n");
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static Connection getConnection(String dbURL, String userName, String password) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, userName, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return connection;
    }
}
