package testbase;

import static io.restassured.RestAssured.given;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class TestBase {
	//Command arguments
	public static String projectID="XirrusMgmtSys_API";
	public static String releaseID="9.0.0";
	public static String testcycleID= "development"; //development or regression
	public static String buildID="4852-23420"; //UI version - Backend version
	public static String testcaseID=null;
	public static String testsuiteID=null;
	
	static Connection testdbConn;
	String sqlHost;
	String sqlPort;
	String sqlUser;
	String sqlPassword;
	String sqlQuery;
	PreparedStatement sqlPrepStat;
	String sqlServerURL;
	
	
	File file;
	FileWriter filestream;
    BufferedWriter out;
	
    Logger log;
	
	
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
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	@BeforeSuite
	public void initialize_all_variables() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		log= LogManager.getLogger("xmsc");
				
		sqlUser= "kartik01";
		sqlPassword = "Xirrus!23";				
		sqlHost="10.100.185.3";
		sqlPort="3306";
		String database="/xirrustestdb";
		sqlServerURL="jdbc:mysql://"+sqlHost+":"+sqlPort+database;
		testdbConn= DriverManager.getConnection(sqlServerURL, sqlUser, sqlPassword);
		
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
	
	
	
	//command methods 
	public void postTestResult(String testSuiteName, String testCaseName, String testResult, long startDateTime, long endDateTime) throws IOException, ParseException, SQLException, ClassNotFoundException {	
		SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startDate= dt.format(startDateTime);
		String endDate= dt.format(endDateTime);
		file =new File(System.getProperty("user.dir")+"\\logs\\resultfile.txt");
		filestream = new FileWriter(file, true);
		out = new BufferedWriter(filestream);
		out.write(testSuiteName+"||"+testCaseName+"||"+testResult+"||"+startDate+"||"+endDate);
		out.newLine();
		out.close();
		insertTestResultIntoDataBase(testSuiteName, testCaseName, testResult, startDate, endDate);
		
	}
	
	public void insertTestResultIntoDataBase(String testSuiteName, String testCaseName, String testResult, String startDate, String endDate) throws SQLException, ClassNotFoundException {
		//Create SQL Query  
		sqlQuery = "insert into testexecutionresults (releaseID, projectID, testcycleID, buildID, testsuiteID, testcaseID, testcaseResult, testcaseStartTime, testcaseEndTime, testcaseLogPath)"+ 
				" values (?,?,?,?,?,?,?,?,?,?)";
		// create the mysql insert preparedstatement
		sqlPrepStat = testdbConn.prepareStatement(sqlQuery);
		sqlPrepStat.setString (1, releaseID);
		sqlPrepStat.setString (2, projectID);
		sqlPrepStat.setString (3, testcycleID);
		sqlPrepStat.setString (4, buildID);
		sqlPrepStat.setString (5, testSuiteName);
		sqlPrepStat.setString (6, testCaseName);
		sqlPrepStat.setString (7, testResult);
		sqlPrepStat.setString (8, startDate);
		sqlPrepStat.setString (9, endDate);
		sqlPrepStat.setString (10, "c:\\logs.txt");
		// execute the preparedstatement
	    sqlPrepStat.execute();
	}
}
