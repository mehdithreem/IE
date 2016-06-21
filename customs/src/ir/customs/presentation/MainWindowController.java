package ir.customs.presentation;

import java.io.IOException;

import ir.customs.CustomsApp;
import ir.customs.domain.Access;
import ir.customs.domain.manager.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainWindowController {
	// Reference to the main application.
    private CustomsApp customsApp;
    
    @FXML
    private HBox topMenu;
    
    @FXML
    private Button createPermissionBTN;
    @FXML
    private Button createRuleBTN;
    @FXML
    private Button createLicenseBTN;
    @FXML
    private Button createUserBTN;
    @FXML
    private MenuButton declarationBTN;
    @FXML
    private MenuItem createDeclarationBTN;
    @FXML
    private MenuItem viewDeclarationBTN;
    
    @FXML
    private void initialize() {
    	topMenu.managedProperty().bind(createPermissionBTN.visibleProperty());
    	handleAccess();
    }
    
    private void handleAccess() {
    	// there is a access control in FindDeclarationController.setDisableIssuePerm
    	UserManager mng = UserManager.getManager();
    	
    	if (!mng.hasAccess(Access.CreatePermission)){
    		topMenu.getChildren().remove(createPermissionBTN);
    	}
    	if (!mng.hasAccess(Access.CreateRule)) {
    		topMenu.getChildren().remove(createRuleBTN);
    	}
    	if (!mng.hasAccess(Access.CreateLicense)) {
    		topMenu.getChildren().remove(createLicenseBTN);
    	}
    	if (!mng.hasAccess(Access.CreateUser)) {
    		topMenu.getChildren().remove(createUserBTN);
    	}
    	if (!mng.hasAccess(Access.ViewDeclration) && !mng.hasAccess(Access.CreateDeclration)) {
    		topMenu.getChildren().remove(declarationBTN);
    	}
    	createDeclarationBTN.setDisable(!mng.hasAccess(Access.CreateDeclration));
    	viewDeclarationBTN.setDisable(!mng.hasAccess(Access.ViewDeclration));
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
