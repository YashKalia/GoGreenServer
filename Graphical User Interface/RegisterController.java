package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterController {
	
	@FXML
	private Label lblregisterstatus;
	
	@FXML
	private TextField passwordfield;
	
	@FXML
	private TextField passwordconfirm;
	
	
	public void checkregisteration (ActionEvent event) {
		if (passwordfield.getText().equals(passwordconfirm.getText())) {
			lblregisterstatus.setText("Registeration Confirmed");
		} else {
			lblregisterstatus.setText("Password Do Not Match");
		}
	}
	

}
