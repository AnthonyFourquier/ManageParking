package fr.eni.gestion_parking.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.gestion_parking.bo.Personne;
import fr.eni.gestion_parking.bo.Voiture;


public class AppliTestBLL {

	public static void main(String[] args) {
		// Instanciation du jeu d'essai
		List<Personne> personnes = new ArrayList<>();
		
		Personne personne = new Personne("Bic", "BBOrange");
		Personne personneVoiture = new Personne("Test", "Test");
		personnes.add(personne);
		personnes.add(personneVoiture);
		
		personnes.add(new Personne("Jean", "AAAA"));
		personnes.add(new Personne("Adrien", "BBBB"));
		personnes.add(new Personne("Julien", "CCCCC"));
		personnes.add(new Personne("Gired", "DDDDD"));

		CatalogueManagerPersonne mger;
		try {
			mger = CatalogueManagerPersonne.getInstancePersonne();

			// Ajout d'un article au catalogue
			try {
				for (Personne pers : personnes) {
					mger.addPersonne(pers);
				}
				System.out.println(mger.getCataloguePersonne());

			} catch (BLLException e) {
				e.printStackTrace();
			}

			// Modification d'un article
			try {
				personne.setNom("Anthony");
				personne.setPrenom("Fourquier");
				mger.updatePersonne(personne);
				System.out.println("Personne après modification  : " + personne.toString());
			} catch (BLLException e) {
				e.printStackTrace();
			}

			// Suppression d'un article
			try {
				mger.removePersonne(personne.getId());
				System.out.println(mger.getCataloguePersonne());
			} catch (BLLException e) {
				e.printStackTrace();
			}
		} catch (BLLException e1) {
			e1.printStackTrace();
			return;
		}
		
	List<Voiture> voitures = new ArrayList<>();
		
		Voiture voiture = new Voiture("Megane", "AB 645 CF");
		voitures.add(voiture);
		
		voitures.add(new Voiture("Clio", "BG 666 KF",personneVoiture));
		voitures.add(new Voiture("Polo", "AL 445 LM"));
		voitures.add(new Voiture("Golf","AL 445 LM"));
		voitures.add(new Voiture("Merco", "KJ 555 TM"));

		CatalogueManagerVoiture mgerV;
		try {
			mgerV = CatalogueManagerVoiture.getInstanceVoiture();

			// Ajout d'un article au catalogue
			try {
				for (Voiture v : voitures) {
					mgerV.addVoiture(v);
				}
				System.out.println(mgerV.getCatalogueVoiture());

			} catch (BLLException e) {
				e.printStackTrace();
			}

			// Modification d'un article
			try {
				voiture.setNom("FERRARI");
				voiture.setPlaqueImmatriculation("AA 000 BB");
				mgerV.updateVoiture(voiture);
				System.out.println("Voiture après modification  : " + voiture.toString());
			} catch (BLLException e) {
				e.printStackTrace();
			}

			// Suppression d'un article
			try {
				mgerV.removeVoiture(voiture.getId());
				System.out.println(mgerV.getCatalogueVoiture());
			} catch (BLLException e) {
				e.printStackTrace();
			}
		} catch (BLLException e1) {
			e1.printStackTrace();
			return;
		}

	}

}
