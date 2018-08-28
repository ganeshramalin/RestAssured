package firsttest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class RestAssuredTest {

  String circuitId;

  @Test
  public void test_ScenarioRetrieveFirstCircuitFor2017SeasonAndGetCountry() {
    circuitId = given().when().get("http://ergast.com/api/f1/2017/circuits.json").then().assertThat().statusCode(200)
        .and().contentType(ContentType.JSON).extract().path("MRData.CircuitTable.Circuits.circuitId[0]");
  }

  public void test_CountryShouldBeAustralia() {
    given().pathParam("circuitId", circuitId).when().get("http://ergast.com/api/f1/circuits/{circuitId}.json").then()
        .assertThat().body("MRData.CircuitTable.Circuits.Location[0].country", equalTo("Australia"));
  }
}
