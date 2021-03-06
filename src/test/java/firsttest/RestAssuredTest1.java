package firsttest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class RestAssuredTest1 {

 @Test
  public void test_Scen_Retri_FirstCircuit_SeasonAndGetCountry_ShouldBeAustralia() {
    String circuitId =
        given().when().get("http://ergast.com/api/f1/2017/circuits.json").then().assertThat().statusCode(200).and()
            .contentType(ContentType.JSON).extract().path("MRData.CircuitTable.Circuits.circuitId[0]");

    given().pathParam("circuitId", circuitId).when().get("http://ergast.com/api/f1/circuits/{circuitId}.json").then()
        .assertThat().body("MRData.CircuitTable.Circuits.Location[0].country", equalTo("Australia"));
   
   System.out.println("Test case complete."); 
  }
 
 @Test
  public void test_DummyWillFail() {
   Assert.assertEquals(1, 0);
   System.out.println("Dummy Test case complete."); 
  }
}
