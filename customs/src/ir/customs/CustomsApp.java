package ir.customs;

import java.io.IOException;

import org.hibernate.exception.ConstraintViolationException;

import ir.customs.data.UserRepository;
import ir.customs.domain.Admin;
import ir.customs.presentation.LoginWindowController;
import ir.customs.presentation.MainWindowController;
import ir.customs.tools.HibernateUtils;
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
    	try {
    		UserRepository.getRepository().create(new Admin("admin", "admin", "Super", "Power"));
    	} catch (ConstraintViolationException e) {
    		System.out.println("Admin already added to database.");
    	}
    	
        launch(args);
        HibernateUtils.getSessionFactory().close();
    }
}