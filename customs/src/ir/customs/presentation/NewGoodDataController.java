package ir.customs.presentation;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewGoodDataController {
	@FXML
	private TextField goodName;
	@FXML
	private TextField goodProducer;
	@FXML
	private TextField goodWeight;
	@FXML
	private TextField goodCount;
	@FXML
	private TextField goodUnitPrice;
	
	public NewGoodDataController(){
		
	}
	
	@FXML
    private void initialize() {  	
    }
	
	public String validateForm() {
		String errorMessage = "";
		
		if (goodName.getText() == null || goodName.getText().length() == 0) {
			errorMessage += "اطلاعات مربوط به اسم کالا معتبر نیست.\n";
		}
		if (goodProducer.getText() == null || goodProducer.getText().length() == 0) {
			errorMessage += "اطلاعات مربوط به شرکت سازنده کالا معتبر نیست.\n";
		}
		if (goodWeight.getText() == null || goodWeight.getText().length() == 0) {
			errorMessage += "اطلاعات مربوط به وزن کالا معتبر نیست.\n";
		} else {
			try {
				Integer.parseInt(goodWeight.getText());
			} catch (NumberFormatException e) {
				errorMessage += "وزن کالا باید عدد باشد.\n"; 
			}
		}
		if (goodCount.getText() == null || goodCount.getText().length() == 0) {
			errorMessage += "اطلاعات مربوط به تعداد کالا معتبر نیست.\n";
		} else {
			try {
				Integer.parseInt(goodCount.getText());
			} catch (NumberFormatException e) {
				errorMessage += "تعداد کالا باید عدد باشد.\n"; 
			}
		}
		if (goodUnitPrice.getText() == null || goodUnitPrice.getText().length() == 0) {
			errorMessage += "اطلاعات مربوط به قیمت واحد کالا معتبر نیست.\n";
		} else {
			try {
				Integer.parseInt(goodUnitPrice.getText());
			} catch (NumberFormatException e) {
				errorMessage += "قیمت واحد کالا باید عدد باشد.\n"; 
			}
		}
		
		return errorMessage;
	}
	
	public Map<String, String> getData() {
		Map<String, String> retmap = new HashMap<String, String>();
		
		retmap.put("goodName", goodName.getText());
		retmap.put("goodProducer", goodProducer.getText());
		retmap.put("goodWeight", goodWeight.getText());
		retmap.put("goodCount", goodCount.getText());
		retmap.put("goodUnitPrice", goodUnitPrice.getText());
		
		return retmap;
	}
}
