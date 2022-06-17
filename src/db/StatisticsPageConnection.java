package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class StatisticsPageConnection {

	static String connectionUrl = "jdbc:sqlserver://pr-infor.database.windows.net:1433;" + "database=pr-infor;"
			+ "user=admin2022@pr-infor;" + "password=Flipi2022;" + "encrypt=true;" + "trustServerCertificate=false;"
			+ "hostNameInCertificate=*.database.windows.net;" + "loginTimeout=30";

	public List<String> getUserRanking(int shopId, int limit) {
		Connection conn = null;
		List<String> userNames = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("[sp_get_user_ranking] " + shopId + ", " + limit);

			while (rs.next()) {
				String aux = rs.getString("USER_NAME");
				userNames.add(aux);
			}
			while (userNames.size() <= limit) {
				userNames.add("-");
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return userNames;
	}

	public Hashtable<String, Object> getUserStatistics(int userId) {
		Connection conn = null;
		Hashtable<String, Object> userStatistics = new Hashtable<String, Object>();
		try {
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected to DB");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("[sp_get_statistics_user] " + userId);

			Double totalEarnings = 0.0;
			int numSales = 0;
			Double lastMonthEarnings = 0.0;
			int numLastMonthSales = 0;

			while (rs.next()) {
				totalEarnings = rs.getDouble("GANANCIAS_TOTALES");
				numSales = rs.getInt("NUM_VENTAS");
				lastMonthEarnings = rs.getDouble("GANANCIAS_ULT_MES");
				numLastMonthSales = rs.getInt("NUM_VENTAS_ULT_MES");
			}

			userStatistics.put("totalEarnings", totalEarnings);
			userStatistics.put("numSales", numSales);
			userStatistics.put("lastMonthEarnings", lastMonthEarnings);
			userStatistics.put("numLastMonthSales", numLastMonthSales);

		}

		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return userStatistics;
	}
}
