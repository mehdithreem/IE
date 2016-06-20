package ir.customs.presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.customs.CustomsApp;
import ir.customs.domain.Admin;
import ir.customs.domain.Agent;
import ir.customs.domain.Employee;
import ir.customs.domain.Senior;
import ir.customs.domain.manager.UserManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CreateUserFormController {
	private CustomsApp customsApp;
	private MainWindowController mainWin;
	
	@FXML
	private TextField NID;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private PasswordField password;
	@FXML
	private PasswordField passwordRepeat;
	
	@FXML
	private ComboBox<String> rules;
	@FXML
	private TextField orgName;
	
	@SuppressWarnings("rawtypes")
	private Map<String, Class> rulesMap; // persian, class

	@SuppressWarnings("rawtypes")
	@FXML
    private void initialize() {
		rulesMap = new HashMap<String, Class>();
		
		rulesMap.put("مدیر سیستم", Admin.class);
		rulesMap.put("نماینده", Agent.class);
		rulesMap.put("کارمند", Employee.class);
		rulesMap.put("کارمند ارشد", Senior.class);
		
		List<String> rulesOptionsList = new ArrayList<String>();
		rulesOptionsList.addAll(rulesMap.keySet());
		ObservableList<String> rulesOptions = FXCollections.observableList(rulesOptionsList);
		rules.setItems(rulesOptions);
		
		rules.setValue("کارمند");
		
		orgName.setEditable(false);
		orgName.setDisable(true);
		
		rules.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (rulesMap.get(newValue).equals(Agent.class)) {
					orgName.setEditable(true);
					orgName.setDisable(false);
				} else {
					orgName.setEditable(false);
					orgName.setDisable(true);
					orgName.setText(null);
				}
			}	
		});
    }
	
	public void setCustomsApp(CustomsApp app) {
    	this.customsApp = app;
    }
	
	public void setMainWindowController(MainWindowController win) {
		this.mainWin = win;
	}
	
	public void submitForm() {
		if (!validateForm())
			return;
		
		Integer id = -2;
		
		try {
			id = UserManager.getManager().createUser(
					NID.getText(),
					firstName.getText(),
					lastName.getText(),
					password.getText(),
					orgName.getText(),
					rulesMap.get(rules.getValue())
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (id < 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(customsApp.getPrimaryStage());
			alert.setTitle("خطا در مقادیر");
			if (id.equals(-1)) {
		  		alert.setHeaderText("شماره‌ی ملی کاربر تکراری است.");
			} else {
		  		alert.setHeaderText("لطفا مقادیر وارده را دوباره بررسی کنید.");
			}
	  		
	  		alert.showAndWait();
	
	  		return;
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(customsApp.getPrimaryStage());
			alert.setTitle("موفق");
	  		alert.setHeaderText("کاربر جدید ساخته شد.");
	  		
	  		alert.showAndWait();
	  		
	  		mainWin.showHome();
	
	  		return;
		}	
		
	}
	
	private Boolean validateForm() {
		String errorMessage = "";
		
		if (NID.getText() == null || NID.getText().length() == 0) {
			errorMessage += "کد ملی معتبر نیست.\n";
		}
		if (firstName.getText() == null || firstName.getText().length() == 0) {
			errorMessage += "نام وارد نشده است.\n";
		}
		if (lastName.getText() == null || lastName.getText().length() == 0) {
			errorMessage += "نام خانوادگی وارد نشده است.\n";
		}
		
		if (password.getText() == null || password.getText().length() == 0) {
			errorMessage += "رمز عبور وارد نشده است.\n";
		}
		if (!password.getText().equals(passwordRepeat.getText())) {
			errorMessage += "تکرار رمز عبور اشتباه است.\n";
		}
		
		if (!orgName.isDisable() &&
				(orgName.getText() == null || orgName.getText().length() == 0)) {
			errorMessage += "نام نهاد نماینده مشخص نشده است.\n";
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
}
