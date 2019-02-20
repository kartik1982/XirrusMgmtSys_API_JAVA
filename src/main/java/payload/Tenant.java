package payload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Tenant {
	
public static String add_tenant_payload() throws IOException {
	return generateStringFromResource("C:\\XirrusMgmtSys_API\\src\\main\\java\\payload\\tenant.json");
}


public static String ap_add_paload() {
	String baseiapmac="00:0f:dd:0e:00:04";
	String basemac="00:0f:dd:0e:00:03";
	String ipAddr="10.100.185.22";
	String host_name= "XRFAKEEC7790";
	String jsonString = "[{ "+
							"\"baseMacAddress\": \""+basemac+"\","+
							"\"baseIapMacAddress\": \""+baseiapmac+"\","+
							"\"actualIpAddress\": \""+ipAddr+"\","+
							"\"actualNetmask\": \"255.255.255.0\","+            
							"\"actualGateway\": \"10.100.185.1\","+
							"\"serialNumber\": \""+host_name+"\","+
							"\"arrayModel\": \"XR620\","+
							"\"manufacturer\": \"Xirrus\","+
							"\"licenseKey\": \"11111-22222-33333-44444\","+        
							"\"licensedAosVersion\": \"9.9\","+
							"\"location\": \"1111\","+
							"\"hostName\": \""+host_name+"\","+
							"\"activationStatus\": \"ACTIVATED\","+
						    "\"onlineStatus\": \"UP\","+
						    "\"recentActivation\": \"1455130181024\","+
						    "\"reportedAosVersion\": \"7.6.0-6256\","+
						    "\"clients\": \"10000000\","+
						    "\"txBytes\": \"707669134\","+
						    "\"rxBytes\": \"1772561109\","+
						    "\"totalBytes\": \"2480230243\","+
						    "\"country\": \"US\""+
							"}]";

				System.out.println(jsonString);
	
	return jsonString;
	}

public static String generateStringFromResource(String filePath) throws IOException {
	return new String(Files.readAllBytes(Paths.get(filePath)));
}

}
