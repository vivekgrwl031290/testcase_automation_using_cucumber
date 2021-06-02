package stepDefinitions;
import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.FileInputStream;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class step{
	 
	@Given("^Set url for sending requests to LS from BS$")
	public void Set_url_for_sending_requests_to_LS_from_BS() throws Throwable
	{		
		RestAssured.baseURI = "rest_api_base_url";
		System.out.println("URL for sending requests to BS from LS has been set.");
		
	}
	
	@When("^User send invoice to LS and check request status$")
	public void User_send_invoice_to_LS_and_check_request_status() throws Throwable
	{
		File fileInputStream = new File("path_where_file_containing_input_json_request_for_invoice_import_is_saved");
		
		given()
				.auth().basic("username", "password")
				.contentType(ContentType.JSON)
				.body(fileInputStream)
				.when().post("rest_of_path")
				.then().assertThat()
				.statusCode(200)
				;
		System.out.println("Invoice import request has been successfully processed by LS.");
	}
	
	@Then("^check updated point balance in LS$")
	public void check_updated_point_balance_in_LS() throws Throwable
	{

		FileInputStream fileInputStream = new FileInputStream(new File("path_where_file_containing_input_json_request_for_fetching_member_details_is_saved"));
		Response response = given()		 
				.auth().basic("username", "password")
				.contentType(ContentType.JSON)
				.body(fileInputStream)
				.when().post("rest_of_path").then().contentType(ContentType.JSON).extract().response()
		;
		System.out.println("Updated LOY Member details in LS => " + response.asString());
	}
	
}