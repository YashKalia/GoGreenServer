package application;	

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AddingMealController {
	
	@FXML
	private Label lblMeal;
	
	public void addmeal (ActionEvent event) {
		lblMeal.setText("You added a vegetarian meal");
	}

}
