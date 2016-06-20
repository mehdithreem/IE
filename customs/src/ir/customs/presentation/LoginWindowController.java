package ir.customs.presentation;

import ir.customs.CustomsApp;
import ir.customs.domain.manager.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginWindowController {
	private CustomsApp customsApp;
	
	@FXML
	private Label wrongpass;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	    
	public LoginWindowController() {
	}
	
	@FXML
	private void initialize() {
		wrongpass.setVisible(false);
	}
	
	public void setCustomsApp(CustomsApp app) {
    	this.customsApp = app;
    }
	
	public void tryLogin() {
		if(UserManager.getManager().signIn(username.getText(), password.getText())) {
			customsApp.getPrimaryStage().close();
			customsApp.initRootLayout();
		} else {
			wrongpass.setVisible(true);
		}
	}
}
