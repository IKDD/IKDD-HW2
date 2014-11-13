import java.io.*;
import java.sql.*;


public class IKDDhw2 {
	public static void main(String[] args) {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://iservdb.cloudopenlab.org.tw:5432/ronny88148_db_9062?useUnicode=true&characterEncoding=UTF-8",
				      "ronny88148_user_9062","gGZLj5CP");
			String SQL = "SELECT * FROM \"twitter\" WHERE q = " + "'" + args[0] + "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			System.out.println("text                      user_name          user_id\n");
			String tmpS;
			int flag = 0;
			while (rs.next()) {
				tmpS = rs.getString("text");
				while(tmpS.length() >= 20) {
					if(flag == 0) {
						System.out.print(tmpS.substring(0, 20) + "     ");
						System.out.println(rs.getString("user_name") + "     " + rs.getString("user_id"));
						flag = 1;
					}
					else System.out.println(tmpS.substring(0, 20));
					tmpS = tmpS.substring(20, tmpS.length());
				}
				System.out.println(tmpS + "\n");
				flag = 0;
				//System.out.println(rs.getString("text") + "\t" + rs.getString("user_name") + "\t" + rs.getString("user_id") + "\n");
			}
			rs.close();
			stmt.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
