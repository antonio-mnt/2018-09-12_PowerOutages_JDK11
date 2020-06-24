package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Vicino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private ComboBox<Nerc> cmbBoxNerc;

    @FXML
    private Button btnVisualizzaVicini;

    @FXML
    private TextField txtK;

    @FXML
    private Button btnSimula;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	this.model.creaGrafo();
    	
    	this.txtResult.setText("Grafo creato!\nVertici: "+this.model.getNumeroVertici()+" Archi: "+this.model.getNumeroArchi()+"\n");
    	
    	this.cmbBoxNerc.getItems().addAll(this.model.getNerc());

    }

    @FXML
    void doSimula(ActionEvent event) {

    }

    @FXML
    void doVisualizzaVicini(ActionEvent event) {
    	
    	Nerc n = this.cmbBoxNerc.getValue();
    	
    	if(n==null) {
    		this.txtResult.setText("Selezionare un NERC");
    		return;
    	}
    	
    	this.txtResult.clear();
    	for(Vicino v: this.model.getVicini(n)) {
    		this.txtResult.appendText(v+"\n");
    	}

    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert cmbBoxNerc != null : "fx:id=\"cmbBoxNerc\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert btnVisualizzaVicini != null : "fx:id=\"btnVisualizzaVicini\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'PowerOutages.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}
