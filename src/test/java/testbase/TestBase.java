package testbase;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class TestBase {
	public static String baseURL=null;
	public static String resource_path=null;
	public String payload=null;

	public static String xmsUserName=null;
	public static String xmsPassword=null;
	public static String clientID=null;
	public static String clientSecret=null;
	public static String grantType=null;
	public static String token=null;
	public static String tokenType=null;
	
	
	/**
	 * This function will get backoffice API token
	 */
	@BeforeSuite
	public void initialize_all_variables() {	
		baseURL="https://login-test03.cloud.xirrus.com";
		clientID="xmsmobileclient";
		clientSecret="Xirrus!23";
		xmsUserName="kartik.aiyar@riverbed.com";
		xmsPassword="Kartik@123";
		grantType="password";
		getBackofficeAPIToken();
	}
	
	public void getBackofficeAPIToken(){
		RestAssured.baseURI=baseURL;
		resource_path="/oauth/token";
		Response resp= given().
				param("client_id", clientID).
				param("client_secret", clientSecret).
				param("username", xmsUserName).
				param("password", xmsPassword).
				param("grant_type", grantType).
				when().
				post(resource_path).
				then().assertThat().statusCode(200).and().log().ifValidationFails().
				extract().response();	
		token=resp.asString().split("</access_token>")[0].split("<access_token>")[1];
		tokenType=resp.asString().split("</token_type>")[0].split("<token_type>")[1];
	}	
	public JsonPath rawResponseToJsonFormat(Response resp) { 
		String respon=resp.asString();
		JsonPath retResp=new JsonPath(respon);
		return retResp;
	}
	public XmlPath rawResponseToXMLFormat(Response resp) {			
		String respon=resp.asString();
		XmlPath retRespx=new XmlPath(respon);
		return retRespx;
		
	}
}
