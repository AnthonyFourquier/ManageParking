package fr.eni.gestion_parking.dal;



public class DAOFactory {
	
	public static VoitureDAO getVoitureDAO() {
		// TODO Auto-generated method stub
		
		return new VoitureDAOJdbcImpl() ;
	}
	
	public static PersonneDAO getPersonneDAO() {
		// TODO Auto-generated method stub
		
		return new PersonneDAOJdbcImpl() ;
	}
}
