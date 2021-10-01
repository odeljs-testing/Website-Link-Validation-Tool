package linkValidation;

import com.smartsheet.api.*;
import com.smartsheet.api.models.*;
import com.smartsheet.api.oauth.*;

public class SmartsheetAPIConnector {

	String accessToken = "";
	
	Smartsheet smartsheet = new SmartsheetBuilder().setAccessToken(accessToken)
			.build();
	
}
