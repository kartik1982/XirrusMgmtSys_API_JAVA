package TS_Users;

import java.io.IOException;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payload.Tenant;
import resource_path.Tenants;
import testbase.TestBase;

import static io.restassured.RestAssured.given;

public class TC_Add_Arrays_to_Tenant extends TestBase{	
	String tenant_id=null;
	String array_id=null;

	@Test(priority=1)
	public void add_tenant() throws IOException {		
		RestAssured.baseURI=baseURL+"/api/v2";	
		resource_path=Tenants.add_tenant_path();
		Response resp=given().
				header("Content-Type", "application/json").
				queryParam("access_token", token).
				body(Tenant.add_tenant_payload()).
				when().
				post(resource_path).
				then().assertThat().statusCode(200).and().log().ifValidationFails().
				extract().response();
		JsonPath js=rawResponseToJsonFormat(resp);
		tenant_id= js.get("id");
	}
	@Test(priority=2)
	public void verify_add_Arrays_To_Tenant() throws IOException {
		RestAssured.baseURI=baseURL+"/api/v2";		
		resource_path=Tenants.add_array_to_tenant_path(tenant_id);
		Response resp=given().
				header("Content-Type", "application/json").
				queryParam("access_token", token).
				body(Tenant.ap_add_paload()).
				when().
				post(resource_path).
				then().assertThat().statusCode(201).and().log().all().
				extract().response();
		JsonPath js=rawResponseToJsonFormat(resp);
		array_id= js.get("[0].id");			
		}
		
		@Test(priority=3)
		public void delete_array_for_tenant() {
			RestAssured.baseURI=baseURL+"/api/v2";
			resource_path= Tenants.delete_array_for_tenan_path(tenant_id, array_id);	
			
			Response resp= given().
					header("Content-Type", "application/json").
					queryParam("access_token", token).
					when().
					delete(resource_path).
					then().assertThat().statusCode(200).and().log().ifValidationFails().
					extract().response();
					JsonPath js=rawResponseToJsonFormat(resp);
		}
		
		@Test(priority=4)
		public void delete_tenant() {
			RestAssured.baseURI=baseURL+"/api/v2";
			resource_path= Tenants.delete_tenant_path(tenant_id);	
			
			Response resp= given().
					header("Content-Type", "application/json").
					queryParam("access_token", token).
					when().
					delete(resource_path).
					then().assertThat().statusCode(200).and().log().ifValidationFails().
					extract().response();
					JsonPath js=rawResponseToJsonFormat(resp);
		}
}
