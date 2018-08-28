package firsttest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {
	
	
	@Test
	public void checkHttpStatus() {
		
		given().
		when().
			get("http://ergast.com/api/f1/2017/circuits.json").
		then().
			assertThat().
			statusCode(200);
		and().
       			 contentType(ContentType.JSON).
   		and().
        		header("Content-Length",equalTo("4567"));			
	}
}
