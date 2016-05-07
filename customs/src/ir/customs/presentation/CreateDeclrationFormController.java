package ir.customs.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ir.customs.CustomsApp;
import ir.customs.domain.ArgumentNotFoundException;
import ir.customs.domain.DeclarationManager;
import ir.customs.domain.Transport;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

public class CreateDeclrationFormController {
	private CustomsApp customsApp;
	private MainWindowController mainWin;
	
	private List<NewGoodDataController> goodCtrl;
	private List<RadioButton> radioTransport;
	
	@FXML
	private VBox newGoodsVBox;
	
	@FXML
	private TextField merNID;
	@FXML
	private TextField merFirstName;
	@FXML
	private TextField merLastName;
	@FXML
	private TextField srcCountry;
	@FXML
	private VBox transportBox;
	
	@FXML
    private void initialize() {
		ToggleGroup group = new ToggleGroup();
		goodCtrl = new ArrayList<NewGoodDataController>();
		radioTransport = new ArrayList<RadioButton>();
		
		for (Transport t : Transport.values()) {
			RadioButton tmpButt = new RadioButton(t.getPersianName());
			tmpButt.setToggleGroup(group);
			transportBox.getChildren().add(tmpButt);
			radioTransport.add(tmpButt);
		}
		((RadioButton) transportBox.getChildren().get(0)).setSelected(true);
    }
	
	public void setCustomsApp(CustomsApp app) {
    	this.customsApp = app;
    }
	
	public void setMainWindowController(MainWindowController win) {
		this.mainWin = win;
	}
	
	public void addGoodItem() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CustomsApp.class.getResource("presentation/NewGoodData.fxml"));
			
			TitledPane newtpane = (TitledPane) loader.load();
			
			goodCtrl.add((NewGoodDataController) loader.getController());
			
			newGoodsVBox.getChildren().add(newtpane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Boolean validateForm() {
		String errorMessage = "";
		
		if (merNID.getText() == null || merNID.getText().length() == 0) {
			errorMessage += "اطلاعات مربوط به کد ملی تاجر معتبر نیست.\n";
		}
		if (srcCountry.getText() == null || srcCountry.getText().length() == 0) {
			errorMessage += "اطلاعات مربوط به کشور مبدا معتبر نیست.\n";
		}
		
		for(NewGoodDataController ctrl : goodCtrl){
			errorMessage += ctrl.validateForm();
		}
		
		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(customsApp.getPrimaryStage());
			alert.setTitle("مقادیر اشتباه");
      		alert.setHeaderText("لطفا مقادیر را اصلاح نمایید.");
      		alert.setContentText(errorMessage);
      		
      		alert.showAndWait();

      		return false;
		}
	}
	
	public void submitForm() {
		if(!validateForm())
			return;
		
		List<Map<String, String>> goodStr = new ArrayList<Map<String, String>>();
		for(NewGoodDataController ctrl : goodCtrl){
			goodStr.add(ctrl.getData());
		}
		String selectedRadio = "";
		for(RadioButton r : this.radioTransport) {
			if (r.isSelected()) {
				selectedRadio = r.getText();
				break;
			}
		}
		
		Integer id = -1;
		
		try {
			id = DeclarationManager.getManager().submitNew(
				merNID.getText(),
				merFirstName.getText(),
				merLastName.getText(),
				srcCountry.getText(),
				selectedRadio,
				goodStr);
		} catch (ArgumentNotFoundException e) {
			System.out.println("ERROR: " + e.getArgumentName());
			e.printStackTrace();
		}
		
		if (id < 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(customsApp.getPrimaryStage());
			alert.setTitle("خطا در مقادیر");
      		alert.setHeaderText("لطفا مقادیر وارده را دوباره بررسی کنید..");
      		
      		alert.showAndWait();

      		return;
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(customsApp.getPrimaryStage());
			alert.setTitle("موفق");
      		alert.setHeaderText("کد رهگیری: " + String.valueOf(id));
      		alert.setContentText("لطفا کد بالا را برای پیگیری‌های بعدی ثبت نمایید.");
      		
      		alert.showAndWait();
      		
      		mainWin.showHome();

      		return;
		}
	}
}
