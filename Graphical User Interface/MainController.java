package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
	
	@FXML
	private Label lblStatus;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private TextField txtPassword;
	
	public void login (ActionEvent event) throws Exception {
		
		UserClass newuser = new UserClass(txtUsername.getText(),txtPassword.getText());
		if ((newuser.getuser().equals("User"))&& newuser.getpassword().equals("Password")) {
			lblStatus.setText("Login Success"); 
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/AddingMeal.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} else {
			lblStatus.setText("Login Failed");
		}
	}
	
	public void register (ActionEvent event) throws Exception {
		
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Register.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
