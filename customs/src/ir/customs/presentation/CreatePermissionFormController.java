package ir.customs.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ir.customs.CustomsApp;
import ir.customs.domain.LicenseManager;
import ir.customs.domain.PermissionManager;
import ir.customs.domain.Transport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CreatePermissionFormController {
	private CustomsApp customsApp;
	private MainWindowController mainWin;
	
	private List<RadioButton> radioTransport;
	Map<String, Integer> licensesMap;
	
	@FXML
	private GridPane LicenseBox;
	
	private ComboBox<String> LicenseName;
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
	private TextField GoodTotalValue;
	@FXML
	private TextField GoodName;
	@FXML
	private TextField GoodProducer;
	@FXML
	private TextField GoodCount;
	
	@FXML
    private void initialize() {
		ToggleGroup group = new ToggleGroup();
		radioTransport = new ArrayList<RadioButton>();
		
		//Initializing LicenseName choice box
		LicenseName = new ComboBox<String>();
		licensesMap = LicenseManager.getManager().getLicenseTitles();
		List<String> licOptionsList = new ArrayList<String>();
		licOptionsList.addAll(licensesMap.keySet());
		ObservableList<String> licOptions = FXCollections.observableList(licOptionsList);
		LicenseName.setItems(licOptions);
		LicenseName.setPrefWidth(320);
		LicenseBox.add(LicenseName, 1, 0);
		
		
		//Initializing transport types
		for (Transport t : Transport.values()) {
			RadioButton tmpButt = new RadioButton(t.getPersianName());
			tmpButt.setToggleGroup(group);
			transportBox.getChildren().add(tmpButt);
			radioTransport.add(tmpButt);
		}		
    }
	
	public void setCustomsApp(CustomsApp app) {
    	this.customsApp = app;
    }
	
	public void setMainWindowController(MainWindowController win) {
		this.mainWin = win;
	}

	private Boolean validateForm() {
		String errorMessage = "";
		
		if (merNID.getText() == null || merNID.getText().length() == 0) {
			errorMessage += "اطلاعات مربوط به کد ملی تاجر معتبر نیست.\n";
		}
		if (LicenseName.getValue() == null || LicenseName.getValue().length() == 0) {
			errorMessage += "نوع مجوز مشخص نشده است.\n";
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
		
		String selectedRadio = null;
		for(RadioButton r : this.radioTransport) {
			if (r.isSelected()) {
				selectedRadio = r.getText();
				break;
			}
		}
		
		Integer id = -1;
		
		try {
			id = PermissionManager.getManager().createNew(
						merNID.getText(),
						merFirstName.getText(),
						merLastName.getText(),
						licensesMap.get(LicenseName.getValue()),
						GoodTotalValue.getText() == null || GoodTotalValue.getText().equals("") ? null : Integer.valueOf(GoodTotalValue.getText()),
						srcCountry.getText(),
						selectedRadio,
						GoodName.getText(),
						GoodProducer.getText(),
						GoodCount.getText() == null || GoodCount.getText().equals("") ? null : Integer.valueOf(GoodCount.getText())
				);
		} catch (Exception e) {
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
