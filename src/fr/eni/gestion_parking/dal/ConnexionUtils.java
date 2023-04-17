package fr.eni.gestion_parking.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.gestion_parking.dal.Settings;

public class ConnexionUtils {
	private static String urldb = Settings.getProperties("urldb") ;
	private static String userdb = Settings.getProperties("userdb");
	private static String passworddb = Settings.getProperties("passworddb");
	
		public static  Connection getConnection() throws SQLException {
			return DriverManager.getConnection(urldb,userdb,passworddb);
		}
}
