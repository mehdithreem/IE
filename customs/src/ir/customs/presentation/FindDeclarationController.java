package ir.customs.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.customs.CustomsApp;
import ir.customs.domain.DeclarationManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.VBox;

public class FindDeclarationController {
	private CustomsApp customsApp;
	@SuppressWarnings("unused")
	private MainWindowController mainWin;
	
	@FXML
	private TextField findDecID;
	
	@FXML
	private ScrollPane dataPane;
	@FXML
	private TextField decID;
	@FXML
	private TextField decDecDate;
	@FXML
	private TextField decStatus;
	@FXML
	private TextField decReqLics;
	@FXML
	private TextField decIssPerm;
	@FXML
	private TextField merNID;
	@FXML
	private TextField merFirstName;
	@FXML
	private TextField merLastName;
	@FXML
	private TextField srcCountry;
	@FXML
	private TextField trasportType;
	@FXML
	private VBox newGoodsVBox;
	
	@FXML
	private TextField issPermID;
	@FXML
	private Button issPerm;
	@FXML
	private ButtonBar buttBar;
	
	@FXML
    private void initialize() {
		setDisableIssuePerm(true);
    }
	
	public void setCustomsApp(CustomsApp app) {
    	this.customsApp = app;
    }
	
	public void setMainWindowController(MainWindowController win) {
		this.mainWin = win;
	}
	
	public void findDec() {
		if (findDecID.getText() == null || findDecID.getText().equals("")) return;
		Integer decID;
		try {
			decID = Integer.valueOf(findDecID.getText());
		} catch (Exception e) {
			return;
		}
		
		Map<String,String> data = new HashMap<String,String>();
		List<Map<String,String>> goodsData = new ArrayList<Map<String,String>>();
		List<String> requiredLicensesTitles = new ArrayList<String>();
		Map<Integer,String> issuedPermission = new HashMap<Integer,String>();
		if (DeclarationManager.getManager().getDeclarationInfo(decID, data, goodsData, requiredLicensesTitles, issuedPermission) < 0) {
			setDisableIssuePerm(true);
			clearDecFields();
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(customsApp.getPrimaryStage());
			alert.setTitle("اظهارنامه");
      		alert.setHeaderText("اظهارنامه‌ پیدا نشد.");
      		
      		alert.showAndWait();
      		
      		return;
		}
		
		setGeneralData(data);
		setGoods(goodsData);
		
		String rqstr = "";
		for(String str : requiredLicensesTitles) {
			rqstr += str + " ، ";
		}
		//rqstr.substring(0, rqstr.length() - 3);
		decReqLics.setText(rqstr);
		
		String perstr = "";
		for(Integer pid : issuedPermission.keySet()) {
			perstr += issuedPermission.get(pid) + ")" + String.valueOf(pid) + "(" + " ، ";
		}
		//perstr.substring(0, perstr.length() - 3);
		decIssPerm.setText(perstr);
		
		setDisableIssuePerm(false);
	}
	
	public void issuePerm() {
		
	}
	
	private void setGeneralData(Map<String,String> data) {		
		decID.setText(data.get("id"));
		decDecDate.setText(data.get("declareDate"));
		decStatus.setText(data.get("currStatus"));
		merNID.setText(data.get("merchantID"));
		merFirstName.setText(data.get("merchantFirstName"));
		merLastName.setText(data.get("merchantLastName"));
		srcCountry.setText(data.get("country"));
		trasportType.setText(data.get("transportType"));
	}
	
	private void setGoods(List<Map<String,String>> goods) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CustomsApp.class.getResource("presentation/NewGoodData.fxml"));
			
			for(Map<String,String> infoMap : goods) {
				TitledPane newtpane = (TitledPane) loader.load();
				
				((NewGoodDataController) loader.getController()).setData(infoMap);
				((NewGoodDataController) loader.getController()).DisableFields();
				
				newGoodsVBox.getChildren().add(newtpane);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void clearDecFields() {
		decID.setText("");
		decDecDate.setText("");
		decStatus.setText("");
		decReqLics.setText("");
		decIssPerm.setText("");
		merNID.setText("");
		merFirstName.setText("");
		merLastName.setText("");
		srcCountry.setText("");
		trasportType.setText("");
		newGoodsVBox.getChildren().clear();
	}
	
	private void setDisableIssuePerm(Boolean val) {
		issPerm.setDisable(val);
		issPermID.setDisable(val);
		dataPane.setVisible(!val);
		buttBar.setVisible(!val);
	}
}
