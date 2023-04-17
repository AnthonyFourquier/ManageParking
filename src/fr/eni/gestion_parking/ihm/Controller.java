package fr.eni.gestion_parking.ihm;

import java.util.List;

import fr.eni.gestion_parking.bll.BLLException;
import fr.eni.gestion_parking.bll.CatalogueManagerPersonne;
import fr.eni.gestion_parking.bll.CatalogueManagerVoiture;
import fr.eni.gestion_parking.bo.Personne;
import fr.eni.gestion_parking.bo.Voiture;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.beans.XMLEncoder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;

public class Controller {

    @FXML
    private Label label;
    
    @FXML
    private TableView<Personne> personneTable ;
    
    @FXML
    private TableColumn<Personne, String> nomColonne;
    
    @FXML
    private TableColumn<Personne, String> prenomColonne;
    
    @FXML
    private TableView<Voiture> voitureTable ;
    
    @FXML
    private TableColumn<Voiture, String> nomVoitureColonne;
    
    @FXML
    private TableColumn<Voiture, String> piVoitureColonne;
    
    @FXML
    private TableColumn<Voiture, String> nomPrenomVoitureColonne;
    
    
    @FXML
    private TextField nomVoiture;
    
    @FXML
    private TextField PIVoiture;
    
    @FXML
    private TextField nomPersonne;
    
    @FXML
    private TextField prenomPersonne;
    
    @FXML
    private ChoiceBox selectName;
    
    private CatalogueManagerPersonne cmp ;
    
    private CatalogueManagerVoiture cmv ;
    
    private Integer idVoiture ;
    
    private Integer idPersonne;

    public void initialize() throws BLLException  { 
    	
    	cmv = CatalogueManagerVoiture.getInstanceVoiture();
    	initDisplayVoiture();
    	nomVoitureColonne.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
		piVoitureColonne.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getPlaqueImmatriculation()));
		nomPrenomVoitureColonne.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getPersonne() != null ? cellData.getValue().getPersonne().getNom() :null));
		nomPrenomVoitureColonne.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getPersonne() != null ? cellData.getValue().getPersonne().getPrenom() :null));
		
		cmp = CatalogueManagerPersonne.getInstancePersonne();
		initDisplayPersonne();
		nomColonne.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
    	prenomColonne.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getPrenom()));
    	
    	voitureTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
    		if(newValue != null) {
    			nomVoiture.setText(newValue.getNom());
        		PIVoiture.setText(newValue.getPlaqueImmatriculation());
        		idVoiture = newValue.getId();
        		System.out.println(idVoiture);
    		}else {
    			nomVoiture.clear();
    			PIVoiture.clear();
    		}
    	});
    	
    	personneTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
    		if(newValue != null) {
    			nomPersonne.setText(newValue.getNom());
        		prenomPersonne.setText(newValue.getPrenom());
        		idPersonne = newValue.getId();
        		System.out.println(idPersonne);
    		}else {
    			nomVoiture.clear();
    			PIVoiture.clear();
    		}
    	});
    	
    	selectName.setItems(FXCollections.observableArrayList(cmp.getCataloguePersonne()));
    }
    
    public void initDisplayVoiture() {

    	try {
			voitureTable.setItems(FXCollections.observableArrayList(cmv.getCatalogueVoiture()));
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    
    public void initDisplayPersonne()  { 
    	try {
			personneTable.setItems(FXCollections.observableArrayList(cmp.getCataloguePersonne()));
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    public void ajouterPersonne(ActionEvent event) throws BLLException {  	
    	var nom = nomPersonne.getText();
    	var prenom = prenomPersonne.getText();
    	var personne = new Personne();
    	personne.setNom(nom);
    	personne.setPrenom(prenom);
    	cmp.addPersonne(personne);
    	initDisplayPersonne();
    }
    
    @FXML
    public void ajouterVoiture(ActionEvent event) throws BLLException {
    	var nom = nomVoiture.getText();
    	var pi = PIVoiture.getText();
    	var voiture = new Voiture();
    	voiture.setNom(nom);
    	voiture.setPlaqueImmatriculation(pi);
    	cmv.addVoiture(voiture);
    	initDisplayVoiture();
    }
    
    @FXML
    public void deleteVoiture(ActionEvent event) throws BLLException {
    	cmv.removeVoiture(idVoiture);
        	initDisplayVoiture();
    }
    
    @FXML
    public void deletePersonne(ActionEvent event) throws BLLException {
    	cmp.removePersonne(idPersonne);
    	initDisplayPersonne();
    }
    
    @FXML
    public void modifierPersonne(ActionEvent event) throws BLLException {
    	var personne =cmp.getPersonne(idPersonne);
    	
    	var nom = nomPersonne.getText();
    	var prenom = prenomPersonne.getText();
    	personne.setNom(nom);
    	personne.setPrenom(prenom);
    	cmp.updatePersonne(personne);
    	initDisplayPersonne();
    }
    
    @FXML
    public void modifierVoiture(ActionEvent event) throws BLLException {
    	
    	var voiture =cmv.getVoiture(idVoiture);
    	var nom = nomVoiture.getText();
    	var pi = PIVoiture.getText();
    	voiture.setNom(nom);
    	voiture.setPlaqueImmatriculation(pi);
    	cmv.updateVoiture(voiture);
    	
    	initDisplayVoiture();
    }
    
  
    @FXML
    public void exportXml(ActionEvent event) throws FileNotFoundException { 
    	       XMLEncoder encoder = new XMLEncoder(new FileOutputStream("C:\\Users\\afourquier2022\\eclipse-workspace\\GestionParking\\src\\fr\\eni\\gestion_parking\\xml\\GestionParking.xml")); 	 
    	         try { 
    	            var voituresPersonnes = cmv.getCatalogueVoiture();
    	            //TODO faire une jointure des 2 tables 
    	            encoder.writeObject(voituresPersonnes); 
    	            encoder.flush(); 
    	        } catch (BLLException e) { 
    	        	throw new FileNotFoundException(e.getMessage());
    
    	         } finally { 
    	            encoder.close(); 
    	        } 
    	   } 
    	     
    	
    @FXML
    public void exportCsv() throws Exception { 
//             try { 
//                 File file = new File("C:\\Users\\afourquier2022\\eclipse-workspace\\GestionParking\\src\\fr\\eni\\gestion_parking\\csv\\GestionParking.csv"); 
//    	            Writer writer = new BufferedWriter(new FileWriter(file)); 
//    
//    	 
//    	           var voituresPersonnes = cmv.getCatalogueVoiture();  
//                 StringBuilder sb = new StringBuilder(); 
//    	            sb.append("id"); 
//    	            sb.append(";"); 
//                sb.append("nom"); 
//                sb.append(";"); 
//                 sb.append("prenom"); 
//    	             sb.append(";"); 
//    	            sb.append("id"); 
//    	            sb.append(";"); 
//    	            sb.append("nom"); 
//    	            sb.append(";"); 
//    	            sb.append("PI"); 
//    	           sb.append(";"); 
//    	             sb.append("\n"); 
//    	
//    	 
//    	           for (VoiturePersonne voiturePersonne : voituresPersonnes) { 
//    	               sb.append(voiturePersonne.getPersonneId()); 
//                    sb.append(";"); 
//                     sb.append(voiturePersonne.getNomPersonne()); 
//    	                 sb.append(";"); 
//                sb.append(voiturePersonne.getPrenom()); 
//    	                 sb.append(";"); 
//    	                 sb.append(voiturePersonne.getVoitureId()); 
//    	               sb.append(";"); 
//    	                 sb.append(voiturePersonne.getNomVoiture()); 
//    	                 sb.append(";"); 
//    	                 sb.append(voiturePersonne.getPI()); 
//    	                 sb.append("\n"); 
//    	             } 
//    	             writer.write(sb.toString()); 
//    	             writer.flush(); 
//    	             writer.close(); 
//    	         } catch (Exception e) { 
//    	             logger.log(Level.SEVERE, e.getMessage()); 
//    	             showError(errorExportFx, e); 
//    	         } 
    	     } 
  
  
}