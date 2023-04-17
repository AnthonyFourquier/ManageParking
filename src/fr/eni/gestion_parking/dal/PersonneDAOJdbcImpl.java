package fr.eni.gestion_parking.dal;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.eni.gestion_parking.bo.Personne;
import fr.eni.gestion_parking.dal.ConnexionUtils;
import fr.eni.gestion_parking.dal.DALException;
import fr.eni.gestion_parking.utils.MonLogger;





public class PersonneDAOJdbcImpl implements PersonneDAO {

		public static final Logger logger = MonLogger.getLogger(PersonneDAOJdbcImpl.class.getSimpleName());
		
		public boolean insert(Personne personne) {
			boolean resultat = false;
		
			try (var cnx = ConnexionUtils.getConnection();
				var requete = cnx.prepareStatement("INSERT INTO Personnes VALUES(?,?)",Statement.RETURN_GENERATED_KEYS);)
			{	
				
				requete.setString(1,personne.getNom());
				requete.setString(2,personne.getPrenom());

				resultat = requete.executeUpdate() == 1;
				var rs = requete.getGeneratedKeys();
				if(rs.next()) {
					personne.setId(rs.getInt(1));
				}
			}
			catch(SQLException e){
				logger.log(Level.SEVERE,"Erreur dans insert " + e.getMessage());
			}
			return resultat;
		}
		
		
		public boolean update(Personne personne) throws DALException{
			boolean resultat = false;
			try (var cnx = ConnexionUtils.getConnection();
					var requete = cnx.prepareStatement(
							"UPDATE Personnes SET nom=?,prenom=? WHERE id=?");) {
					requete.setString(1,personne.getNom());
					requete.setString(2,personne.getPrenom());
					requete.setInt(3, personne.getId());
				
					resultat = requete.executeUpdate() == 1;
			} catch (SQLException e) {
				logger.log(Level.SEVERE,"Erreur dans update " + e.getMessage());
			}
			return resultat;
		}
		
		public boolean delete(Integer id) throws DALException {
			boolean resultat = false;
			try (var cnx = ConnexionUtils.getConnection();
					var requete = cnx.prepareStatement("DELETE Personnes WHERE id=?");) {
					requete.setInt(1,id);
					resultat = requete.executeUpdate() == 1;
			} catch (SQLException e) {
				logger.log(Level.SEVERE,"Erreur dans delete " + e.getMessage());
			}
			return resultat;
		}
		
		public List<Personne> selectAll()  throws DALException {
			List<Personne> resultat = new ArrayList<>();
			try (var cnx = ConnexionUtils.getConnection();
					var requete = cnx.prepareStatement("SELECT * FROM Personnes");)
				{
					var rs = requete.executeQuery();
					while(rs.next()) {
						
							var item = new Personne();
							item.setId(rs.getInt("id"));
							item.setNom(rs.getString("nom"));
							item.setPrenom(rs.getString("prenom"));
				
							resultat.add(item);
					}
				}
				catch(SQLException e){
					logger.log(Level.SEVERE,"Erreur dans selectALL " + e.getMessage());
				}
				return resultat ;
		}
		
		public Personne selectById(Integer id) throws DALException {

			Personne resultat = null;
			
			try (var cnx = ConnexionUtils.getConnection();
					var requete = cnx.prepareStatement("SELECT * FROM Personnes WHERE id= ?");)
				{
					
					requete.setInt(1,id);
					var rs = requete.executeQuery();
					
						if(rs.next()) {
							resultat = new Personne();
							resultat.setId(rs.getInt("id"));
							resultat.setNom(rs.getString("nom"));
							resultat.setPrenom(rs.getString("prenom"));
						}
					
				}
				catch(SQLException e){
					logger.log(Level.SEVERE,"Erreur dans selectById " + e.getMessage());
				}
			
					return resultat;				
		}
	
}
