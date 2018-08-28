package firsttest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {
	
	@Test
	public void test_ScenarioRetrieveFirstCircuitFor2017SeasonAndGetCountry_ShouldBeAustralia() {
        
	    // First, retrieve the circuit ID for the first circuit of the 2017 season
	    String circuitId = given().
	    when().
		get("http://ergast.com/api/f1/2017/circuits.json").
	    then().
		assertThat().
			statusCode(200);
		and().
       			contentType(ContentType.JSON).
		extract().
		path("MRData.CircuitTable.Circuits.circuitId[0]");
	    // Then, retrieve the information known for that circuit and verify it is located in Australia
	    given().
		pathParam("circuitId",circuitId).
	    when().
		get("http://ergast.com/api/f1/circuits/{circuitId}.json").
	    then().
		assertThat().
		body("MRData.CircuitTable.Circuits.Location[0].country",equalTo("Australia"));
	}
	
}
