package ir.customs.presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.customs.CustomsApp;
import ir.customs.domain.manager.LicenseManager;
import ir.customs.domain.manager.RuleManager;
import ir.customs.tools.IntegerChecker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class CreateRuleFormController {
	private CustomsApp customsApp;
	private MainWindowController mainWin;
	
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;
	
	@FXML
	private TextField minW;
	@FXML
	private TextField maxW;
	@FXML
	private TextField minC;
	@FXML
	private TextField maxC;
	@FXML
	private TextField minUnitPrice;
	@FXML
	private TextField maxUnitPrice;
	
	private Map<String,Integer> licensesMap;
	private ComboBox<String> licenseName;
	@FXML
	private GridPane licenseBox;
	@FXML
	private TextField licenseList;
	
	@FXML
	private TextField newGoodName;
	@FXML
	private TextField goodsName;
	
	private Map<String,Integer> selectedLicenses;
	private List<String> selectedGoods;
	
	@FXML
	private void initialize() {
		//Initializing LicenseName choice box
		licenseName = new ComboBox<String>();
		licensesMap = LicenseManager.getManager().getLicenseTitles();
		List<String> licOptionsList = new ArrayList<String>();
		licOptionsList.addAll(licensesMap.keySet());
		ObservableList<String> licOptions = FXCollections.observableList(licOptionsList);
		licenseName.setItems(licOptions);
		licenseName.setPrefWidth(320);
		licenseBox.add(licenseName, 0, 0);
		
		selectedLicenses = new HashMap<String, Integer>();
		selectedGoods = new ArrayList<String>();
    }
	
	public void addLicense() {
		if (licenseName.getValue() == null || licenseName.getValue().equals(""))
			return;
		
		if (licenseList.getText() == null)
			licenseList.setText("");
		
		selectedLicenses.put(licenseName.getValue(), licensesMap.get(licenseName.getValue()));
		licenseList.setText(licenseList.getText() + " - " + licenseName.getValue());
		licenseName.setValue(null);
	}
	
	public void addGood() {
		if (newGoodName.getText() == null || newGoodName.getText().equals(""))
			return;
		
		if (goodsName.getText() == null)
			goodsName.setText("");
		
		selectedGoods.add(newGoodName.getText());
		goodsName.setText(goodsName.getText() + " - " + newGoodName.getText());
		newGoodName.setText(null);
	}
	
	public void submitForm() {
		if(!validateForm())
			return;
		
		Integer ruleID = -1;
		
		List<Integer> licenses = new ArrayList<Integer>();
		licenses.addAll(selectedLicenses.values());
		
		try {
			ruleID = RuleManager.getManager().submitRule(
					startDate.getValue(), 
					endDate.getValue(),
					(minW.getText() != null && !minW.getText().equals("")) ? Integer.valueOf(minW.getText()) : null, 
					(maxW.getText() != null && !maxW.getText().equals("")) ? Integer.valueOf(maxW.getText()) : null, 
					(minC.getText() != null && !minC.getText().equals("")) ? Integer.valueOf(minC.getText()) : null, 
					(maxC.getText() != null && !maxC.getText().equals("")) ? Integer.valueOf(maxC.getText()) : null, 
					selectedGoods, 
					licenses, 
					(minUnitPrice.getText() != null && !minUnitPrice.getText().equals("")) ? Integer.valueOf(minUnitPrice.getText()) : null,
					(maxUnitPrice.getText() != null && !maxUnitPrice.getText().equals("")) ? Integer.valueOf(maxUnitPrice.getText()) : null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (ruleID < 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(customsApp.getPrimaryStage());
			alert.setTitle("خطا در مقادیر");
		  	alert.setHeaderText("لطفا مقادیر وارده را دوباره بررسی کنید.");
	  		
	  		alert.showAndWait();
	
	  		return;
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(customsApp.getPrimaryStage());
			alert.setTitle("موفق");
	  		alert.setHeaderText("قانون جدید اضافه شد.");
	  		alert.setContentText("شماره‌ی قانون: " + String.valueOf(ruleID));
	  		
	  		alert.showAndWait();
	  		
	  		mainWin.showHome();
	
	  		return;
		}
	}
	
	public Boolean validateForm() {
		String errorMessage = "";
		
		if (startDate.getValue() != null && endDate.getValue() != null)
			if (startDate.getValue().isAfter(endDate.getValue()))
				errorMessage += "تاریخ شروع بعد از تاریخ پایان است.\n";
		
		if (minW.getText() != null && !minW.getText().equals("") && !IntegerChecker.check(minW.getText()))
			errorMessage += "مقدار حداقل وزن عدد نیست.\n";
		if (maxW.getText() != null && !maxW.getText().equals("") && !IntegerChecker.check(maxW.getText()))
			errorMessage += "مقدار حداکثر وزن عدد نیست.\n";
		if (minC.getText() != null && !minC.getText().equals("") && !IntegerChecker.check(minC.getText()))
			errorMessage += "مقدار حداقل تعداد عدد نیست.\n";
		if (maxC.getText() != null && !maxC.getText().equals("") && !IntegerChecker.check(maxC.getText()))
			errorMessage += "مقدار حداکثر تعداد عدد نیست.\n";
		if (minUnitPrice.getText() != null && !minUnitPrice.getText().equals("") && !IntegerChecker.check(minUnitPrice.getText()))
			errorMessage += "مقدار حداقل قیمت واحد عدد نیست.\n";
		if (maxUnitPrice.getText() != null && !maxUnitPrice.getText().equals("") && !IntegerChecker.check(maxUnitPrice.getText()))
			errorMessage += "مقدار حداکثر قیمت واحد عدد نیست.\n";
		
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
	
	public void setCustomsApp(CustomsApp app) {
    	this.customsApp = app;
    }
	
	public void setMainWindowController(MainWindowController win) {
		this.mainWin = win;
	}
}
