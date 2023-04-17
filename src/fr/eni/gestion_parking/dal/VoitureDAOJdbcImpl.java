package fr.eni.gestion_parking.dal;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.eni.gestion_parking.bo.Voiture;
import fr.eni.gestion_parking.utils.MonLogger;
import fr.eni.gestion_parking.bo.Personne;

public class VoitureDAOJdbcImpl implements VoitureDAO {
	
	public static final Logger logger = MonLogger.getLogger(VoitureDAOJdbcImpl.class.getSimpleName());
	
	public boolean insert(Voiture voiture) {
		boolean resultat = false;
	
		try (var cnx = ConnexionUtils.getConnection();
			var requete = cnx.prepareStatement("INSERT INTO Voitures VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS);)
		{	
			
			requete.setString(1,voiture.getNom());
			requete.setString(2,voiture.getPlaqueImmatriculation());
			if(voiture.getPersonne() != null) {
				requete.setInt(3,voiture.getPersonne().getId());
			}else {
				requete.setNull(3,Types.INTEGER);
			}
			resultat = requete.executeUpdate() == 1;
			var rs = requete.getGeneratedKeys();
			if(rs.next()) {
				voiture.setId(rs.getInt(1));
			}
		}
		catch(SQLException e){
			logger.log(Level.SEVERE,"Erreur dans insert " + e.getMessage());
		}
		return resultat;
	}
	
	
	public boolean update(Voiture voiture) throws DALException{
		boolean resultat = false;
		try (var cnx = ConnexionUtils.getConnection();
				var requete = cnx.prepareStatement(
						"UPDATE Voitures SET nom=?,plaqueImmatriculation=?, idPersonne=? WHERE id=?");) {
				requete.setString(1,voiture.getNom());
				requete.setString(2,voiture.getPlaqueImmatriculation());
				
				if(voiture.getPersonne()!= null) {
					requete.setInt(3, voiture.getPersonne().getId());
				}else {
					requete.setNull(3,Types.INTEGER);
				}
				
				requete.setInt(4, voiture.getId());
			
				resultat = requete.executeUpdate() == 1;
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreur dans update " + e.getMessage());
		}
		return resultat;
	}
	
	public boolean delete(Integer id) throws DALException {
		boolean resultat = false;
		try (var cnx = ConnexionUtils.getConnection();
				var requete = cnx.prepareStatement("DELETE Voitures WHERE id=?");) {
				requete.setInt(1,id);
				resultat = requete.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat;
	}
	
	public List<Voiture> selectAll()  throws DALException {
		
		List<Voiture> resultat = new ArrayList<>();
		PersonneDAO pDao = new PersonneDAOJdbcImpl();
		
		try (var cnx = ConnexionUtils.getConnection();
				var requete = cnx.prepareStatement("SELECT * FROM Voitures");)
			{
				var rs = requete.executeQuery();
				while(rs.next()) {
					
						var item = new Voiture();
						item.setId(rs.getInt("id"));
						item.setNom(rs.getString("nom"));
						item.setPlaqueImmatriculation(rs.getString("plaqueImmatriculation"));
						//TODO averif si i lest null
						if(pDao.selectById(rs.getInt("idPersonne")) != null) {
							item.setPersonne(pDao.selectById(rs.getInt("idPersonne")));
						}
						
						
						resultat.add(item);
				}
			}
			catch(SQLException e){
				logger.log(Level.SEVERE,"Erreur dans selectAll " + e.getMessage());
			}
			return resultat ;
	}
	
	public Voiture selectById(Integer id) throws DALException {

		Voiture resultat = null;
		PersonneDAO pDao = new PersonneDAOJdbcImpl();
		
		try (var cnx = ConnexionUtils.getConnection();
				var requete = cnx.prepareStatement("SELECT * FROM Voitures WHERE id= ?");)
			{
				requete.setInt(1,id);
				var rs = requete.executeQuery();
				
					if(rs.next()) {
						resultat = new Voiture();
						resultat.setId(rs.getInt("id"));
						resultat.setNom(rs.getString("nom"));
						resultat.setPlaqueImmatriculation(rs.getString("plaqueImmatriculation"));
						resultat.setPersonne(pDao.selectById(rs.getInt("idPersonne")));
					}
			}
			catch(SQLException e){
				logger.log(Level.SEVERE,"Erreur dans selectById " + e.getMessage());
			}
				return resultat;				
	}

}
