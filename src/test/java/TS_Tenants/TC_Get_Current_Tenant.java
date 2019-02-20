package TS_Tenants;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resource_path.Tenants;
import testbase.TestBase;


public class TC_Get_Current_Tenant extends TestBase{

	@Test
	public void verify_get_current_tenant_api() {
		RestAssured.baseURI=baseURL+"/api/v2";	
		resource_path=Tenants.get_current_tenant_path();
		Response resp=given().
				header("Content-Type", "application/json").
				param("access_token", token).
				when().
				get(resource_path).
				then().assertThat().statusCode(200).and().log().ifValidationFails().
				extract().response();
		JsonPath js=rawResponseToJsonFormat(resp);
	}

}
