package ir.customs.presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.customs.CustomsApp;
import ir.customs.domain.manager.LicenseManager;
import ir.customs.domain.manager.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

public class CreateLicenseFormController {
	private CustomsApp customsApp;
	private MainWindowController mainWin;
	
	@FXML
	private GridPane mainGrid;
	
	@FXML
	private TextField licenseName;
	
	@FXML
	private TextField validityDuration;
	
	private ComboBox<String> agentList;
	private HashMap<String, String> agentViewMap;
	
	public void setCustomsApp(CustomsApp app) {
    	this.customsApp = app;
    }
	
	public void setMainWindowController(MainWindowController win) {
		this.mainWin = win;
	}
	
	private Boolean validateForm() {
		String errorMessage = "";
		
		if(licenseName.getText() == null || licenseName.getText().length() == 0)
			errorMessage += "اطلاعات نام نوع مجوز معتبر نیست.\n";
		try {
			Integer.valueOf(validityDuration.getText());
		} catch(NumberFormatException e) {
		     errorMessage += "نوع ورودی داده شده برای مدت زمان اعتبار معتبر نیست. \n";
		}
		if(agentList.getValue() == null) {
			errorMessage += "نهاد صادر کننده مشخص نشده است.\n";
		}
		
		if(errorMessage.length() == 0)
			return true;
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(customsApp.getPrimaryStage());
			alert.setTitle("مقادیر اشتباه");
      		alert.setHeaderText("لطفا مقادیر را اصلاح نمایید.");
      		alert.setContentText(errorMessage);
      		
      		alert.showAndWait();
			
			return false;
		}
	}
	
	@FXML
	private void initialize() {
		agentList = new ComboBox<String>();
		Map<String,String> agentMap = UserManager.getManager().getAgentList();
		agentViewMap = new HashMap<String,String>();
		
		List<String> agntOptionsList = new ArrayList<String>();
		for(String id : agentMap.keySet()) {
			String key = id + " -  نماینده‌ی: " + agentMap.get(id);
			agntOptionsList.add(key);
			agentViewMap.put(key, id);			
		}
		
		ObservableList<String> agntOptions = FXCollections.observableList(agntOptionsList);
		
		agentList.setItems(agntOptions);
		agentList.setPrefWidth(320);
		
		mainGrid.add(agentList, 1, 2);
	}
	
	public void submitForm() {
		if(!validateForm())
			return;
		
		Integer id = -1;
		try {
			id = LicenseManager.getManager().createLicense(
					licenseName.getText(),
					Integer.valueOf(validityDuration.getText()),
					agentViewMap.get(agentList.getValue())
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
