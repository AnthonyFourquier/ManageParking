package fr.eni.gestion_parking.dal;

import java.util.List;

import fr.eni.gestion_parking.bo.Personne;


public interface  PersonneDAO {
	
	public boolean insert(Personne personne);
	
	public boolean update(Personne personne)throws DALException;
	
	public boolean delete(Integer id)throws DALException;
	
	public List<Personne> selectAll() throws DALException;
	
	public Personne selectById(Integer id) throws DALException;

}
