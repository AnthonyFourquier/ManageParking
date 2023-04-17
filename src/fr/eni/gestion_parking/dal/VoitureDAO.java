package fr.eni.gestion_parking.dal;

import java.util.List;


import fr.eni.gestion_parking.bo.Voiture;

public interface VoitureDAO {
	
	public boolean insert(Voiture voiture);
	
	public boolean update(Voiture voiture)throws DALException;
	
	public boolean delete(Integer id)throws DALException;
	
	public List<Voiture> selectAll() throws DALException;
	
	public Voiture selectById(Integer id) throws DALException;
	
}
