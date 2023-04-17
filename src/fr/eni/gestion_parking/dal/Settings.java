package fr.eni.gestion_parking.dal;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
	 public static String getProperties(String key) {
	 		String resultat = null;
     try (InputStream input = new FileInputStream("C:\\Users\\afourquier2022\\eclipse-workspace\\GestionParking\\ressources/config.properties")) {

         Properties prop = new Properties();
         
         // load a properties file
         prop.load(input);

         // get the property value and print it out
         if(key == "urldb") {
         	resultat= prop.getProperty("url");
         }
         if(key == "userdb") {
         	resultat = prop.getProperty("user");
        }
         if(key == "passworddb") {
         	resultat =prop.getProperty("pwd");
        }

     } catch (IOException ex) {
         ex.printStackTrace();
     }
     return resultat;
 }
}
