package ir.customs;

import java.io.IOException;

import ir.customs.data.UserRepository;
import ir.customs.domain.Admin;
import ir.customs.domain.Agent;
import ir.customs.domain.HibernateUtils;
import ir.customs.domain.LicenseManager;
import ir.customs.presentation.LoginWindowController;
import ir.customs.presentation.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomsApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private VBox loginLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("گمرک");
        this.primaryStage.setResizable(false);

        initLoginLayout();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CustomsApp.class.getResource("presentation/MainWindow.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            MainWindowController ctrlr = loader.getController();
            ctrlr.setCustomsApp(this);
            ctrlr.showHome();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void initLoginLayout() {
    	try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CustomsApp.class.getResource("presentation/LoginWindow.fxml"));
            loginLayout = (VBox) loader.load();
            
            LoginWindowController ctrlr = loader.getController();
            ctrlr.setCustomsApp(this);
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(loginLayout);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public BorderPane getRootLayout() {
    	return rootLayout;
    }
    
    public BorderPane getLoginLayout() {
    	return rootLayout;
    }

    public static void main(String[] args) {
//    	//to create admin user for the first time
//    	UserRepository.getRepository().create(new Admin("admin", "admin", "Super", "Power"));
//    	UserRepository.getRepository().create(new Agent("123", "123", "Agent", "Agentian", "AB"));
//    	
//    	//to create licenses
//    	try {
//			LicenseManager.getManager().createLicense("مجوز تست", 10, "123");
//			LicenseManager.getManager().createLicense("۲ مجوز تست", 10, "123");
//		} catch (Exception e) {
//			System.out.println("Agent not found.");
//			e.printStackTrace();
//			System.exit(0);
//		}
//    	
        launch(args);
        HibernateUtils.getSessionFactory().close();
    }
}