package fr.eni.gestion_parking.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.gestion_parking.bo.Personne;
import fr.eni.gestion_parking.dal.DALException;
import fr.eni.gestion_parking.dal.DAOFactory;
import fr.eni.gestion_parking.dal.PersonneDAO;


public class CatalogueManagerPersonne {
	
	private PersonneDAO daoPersonne;
	private static volatile CatalogueManagerPersonne instance ;
	
	private CatalogueManagerPersonne() throws BLLException {
		this.daoPersonne=DAOFactory.getPersonneDAO();
	}
	
	public static synchronized CatalogueManagerPersonne getInstancePersonne() throws BLLException {

		if(instance == null) {
			instance = new CatalogueManagerPersonne();
		}
		return instance;
	}
	
	public	List<Personne> getCataloguePersonne() throws BLLException {
		try {
				return daoPersonne.selectAll();
			}
		 catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException(e.getMessage());
		}
	}
	
	public void addPersonne(Personne p) throws BLLException {
			boolean resultat = false;
			try {
				validerPersonne(p);
				resultat = daoPersonne.insert(p);
				System.out.println("La personne est bien inseré");
			
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				throw new BLLException(e.getMessage());
			}
			if(!resultat) {
				throw new BLLException("Erreur d'ajout d'une personne");
			}
		
	}
	
	public void updatePersonne(Personne p) throws BLLException{
		
			try {
				if(p.getId() != null & p.getNom() !=null & p.getPrenom() !=null) {
					if(daoPersonne.update(p)) {
						System.out.println("La personne est a jour");
					}
	
				}
			} catch (DALException ex) {
				// TODO Auto-generated catch block
				throw new BLLException(ex.getMessage());
			}
		
	}
	
	public void removePersonne(int index)throws BLLException {
		try {
			if(daoPersonne.selectById(index)!=null) {
				daoPersonne.delete(index);
				System.out.println("La personne est supprimé ");
			}
		}catch(DALException ex) {
			throw new BLLException(ex.getMessage());
		}
	}
	
	public void validerPersonne(Personne p)throws BLLException {
		if(p.getNom().isBlank()) {
			throw new BLLException("Le nom de la personne est incorrecte");
		}
		if(p.getPrenom() == null || p.getNom().isBlank()) {
			throw new BLLException("Prenom de la personne incorrecte");
		}
		
	}
	public Personne getPersonne(Integer id)throws BLLException {
		//TODO check si existant avant de select
		try {
			var resultat =  daoPersonne.selectById(id);
			
				return resultat;
		
		}catch(DALException ex) {
			throw new BLLException(ex.getMessage());
		}
		
	}
}
