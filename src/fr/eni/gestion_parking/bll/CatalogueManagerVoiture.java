package fr.eni.gestion_parking.bll;

import java.util.List;

import fr.eni.gestion_parking.bo.Personne;
import fr.eni.gestion_parking.bo.Voiture;
import fr.eni.gestion_parking.dal.DALException;
import fr.eni.gestion_parking.dal.DAOFactory;
import fr.eni.gestion_parking.dal.PersonneDAO;
import fr.eni.gestion_parking.dal.VoitureDAO;

public class CatalogueManagerVoiture {
	private VoitureDAO daoVoiture;
	private static volatile CatalogueManagerVoiture instance ;
	
	private   CatalogueManagerVoiture() throws BLLException {
		this.daoVoiture=DAOFactory.getVoitureDAO();
	}
	
	public static synchronized CatalogueManagerVoiture getInstanceVoiture() throws BLLException {
		if(instance == null) {
			instance = new  CatalogueManagerVoiture();
		}
		return instance;
	}
	
	public	List<Voiture> getCatalogueVoiture() throws BLLException {
		try {
				return daoVoiture.selectAll();
			}
		 catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException(e.getMessage());
		}
	}
	
	public void addVoiture(Voiture v) throws BLLException {
			boolean resultat = false;
			try {
				validerVoiture(v);
				resultat = daoVoiture.insert(v);
				System.out.println("La voiture est bien inseré");
			
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				throw new BLLException(e.getMessage());
			}
			if(!resultat) {
				throw new BLLException("Erreur d'ajout d'une voiture");
			}
		
	}
	
	public void updateVoiture(Voiture v) throws BLLException{
		
			try {
				if(v.getId() != null & v.getNom() !=null & v.getPlaqueImmatriculation() != null) {
				daoVoiture.update(v);
				System.out.println("La voiture est a jour");
				}
			} catch (DALException ex) {
				// TODO Auto-generated catch block
				throw new BLLException(ex.getMessage());
			}
		
	}
	
	public void removeVoiture(int index)throws BLLException {
		try {
			if(daoVoiture.selectById(index)!=null) {
				daoVoiture.delete(index);
				System.out.println("La voiture est supprimé ");
			}
		}catch(DALException ex) {
			throw new BLLException(ex.getMessage());
		}
	}
	
	public void validerVoiture(Voiture v)throws BLLException {
		if(v.getNom().isBlank()) {
			throw new BLLException("Le nom de la voiture est incorrecte");
		}
		if(v.getPlaqueImmatriculation() == null || v.getNom().isBlank()) {
			throw new BLLException("La plaque d'immatriculation de la voiture incorrecte");
		}
		
		
	}
	public Voiture getVoiture(Integer id)throws BLLException {
		//TODO check si existant avant de select
		try {
			var resultat =  daoVoiture.selectById(id);
				return resultat;
		}catch(DALException ex) {
			throw new BLLException(ex.getMessage());
		}
		
	}
}
