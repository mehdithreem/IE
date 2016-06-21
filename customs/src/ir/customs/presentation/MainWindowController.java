package ir.customs.presentation;

import java.io.IOException;

import ir.customs.CustomsApp;
import ir.customs.domain.manager.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindowController {
	// Reference to the main application.
    private CustomsApp customsApp;
    
    public MainWindowController() {
    	
    }
    
    @FXML
    private void initialize() {
    	
    }
    
    public void showCreateDeclrationFrom() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CustomsApp.class.getResource("presentation/CreateDeclrationForm.fxml"));
            VBox newDecForm = (VBox) loader.load();
            
            CreateDeclrationFormController newCtrl = loader.getController();
            newCtrl.setCustomsApp(customsApp);
            newCtrl.setMainWindowController(this);

            // Set person overview into the center of root layout.
            customsApp.getRootLayout().setCenter(newDecForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showFindDeclaration() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CustomsApp.class.getResource("presentation/FindDeclaration.fxml"));
            VBox newDecForm = (VBox) loader.load();
            
            FindDeclarationController newCtrl = loader.getController();
            newCtrl.setCustomsApp(customsApp);
            newCtrl.setMainWindowController(this);

            // Set person overview into the center of root layout.
            customsApp.getRootLayout().setCenter(newDecForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showCreatePermissionFrom() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CustomsApp.class.getResource("presentation/CreatePermissionForm.fxml"));
            VBox newDecForm = (VBox) loader.load();
            
            CreatePermissionFormController newCtrl = loader.getController();
            newCtrl.setCustomsApp(customsApp);
            newCtrl.setMainWindowController(this);

            // Set person overview into the center of root layout.
            customsApp.getRootLayout().setCenter(newDecForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showCreateUserForm() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CustomsApp.class.getResource("presentation/CreateUserForm.fxml"));
            VBox newDecForm = (VBox) loader.load();
            
            CreateUserFormController newCtrl = loader.getController();
            newCtrl.setCustomsApp(customsApp);
            newCtrl.setMainWindowController(this);

            // Set person overview into the center of root layout.
            customsApp.getRootLayout().setCenter(newDecForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showCreateRuleForm() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CustomsApp.class.getResource("presentation/CreateRuleForm.fxml"));
            VBox newDecForm = (VBox) loader.load();
            
            CreateRuleFormController newCtrl = loader.getController();
            newCtrl.setCustomsApp(customsApp);
            newCtrl.setMainWindowController(this);

            // Set person overview into the center of root layout.
            customsApp.getRootLayout().setCenter(newDecForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showCreateLicenseForm() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CustomsApp.class.getResource("presentation/CreateLicenseForm.fxml"));
            VBox newDecForm = (VBox) loader.load();
            
            CreateLicenseFormController newCtrl = loader.getController();
            newCtrl.setCustomsApp(customsApp);
            newCtrl.setMainWindowController(this);

            // Set person overview into the center of root layout.
            customsApp.getRootLayout().setCenter(newDecForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
    public void showHome() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CustomsApp.class.getResource("presentation/Home.fxml"));
            AnchorPane home = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            customsApp.getRootLayout().setCenter(home);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void logout() {
    	UserManager.getManager().signOut();
		customsApp.getPrimaryStage().close();
		customsApp.initLoginLayout();
    }
    
    public void setCustomsApp(CustomsApp app) {
    	this.customsApp = app;
    }
}
