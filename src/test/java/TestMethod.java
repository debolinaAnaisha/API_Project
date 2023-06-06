import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class TestMethod {
    public static String Access_Token = "";
    public static String userID = "";
    public static String subscriptionID = "";
    public static String successMsg ="";
   @Test(enabled = true,priority = 1)
    @Given("Create a user")
    public  void createUser() {
//Create User
        ReusuableMethod.getURICreateUser();
        String response =
                given().log().all().
                        header("device_id", "qwe").
                        header("esk", "cXdlX191eWg1bENYYlJ1R2JEZ2N0RjlHYzVadThXbkY1U2xkRF9fY2pj").
                        header("Content-Type", "application/json").
                        body(Payload.createUser()).
                        when().post("v1/user/registeremail").
                        then().assertThat().log().all().statusCode(200).
                        extract().response().asString();

        ReusuableMethod.jsonPathConverter(response).getString("message");
        String SuccessMsg = ReusuableMethod.jsonPathConverter(response).getString("message");
        System.out.println("Registration is" + SuccessMsg);
        System.out.println("Add call is success");
//       throw new io.cucumber.java.PendingException();
    }

    // get bearer Token
    @Test(enabled = true ,priority = 2)
    @And("^Generate the Bearer Token$")
    public static void getBearertoken() {
        ReusuableMethod.getURIGetBearertoken();
        String response =
                given().log().all().
                        header("device_id", "qwe").
                        header("esk", "cXdlX191eWg1bENYYlJ1R2JEZ2N0RjlHYzVadThXbkY1U2xkRF9fY2pj").
                        header("Content-Type", "application/json").
                        body(Payload.getBearerToken()).
                        when().post("v2/user/loginemail").
                        then().assertThat().log().all().statusCode(200).
                        extract().response().asString();

        ReusuableMethod.jsonPathConverter(response).getString("access_token");
        Access_Token = ReusuableMethod.jsonPathConverter(response).getString("access_token");
        System.out.println("Bearer Token  is" + Access_Token);
        System.out.println("Bearer  call is success");
        throw new io.cucumber.java.PendingException();
    }

    @Test(enabled = true ,priority = 3)
    @And("Generate the UserId")
    public static void getUserId() {
        ReusuableMethod.getURIGetUserID();
        String response =
                given().log().all().
                        header("device_id", "qwe").
                        header("esk", "cXdlX191eWg1bENYYlJ1R2JEZ2N0RjlHYzVadThXbkY1U2xkRF9fY2pj").
                        header("Content-Type", "application/json").
                        header("Accept", "application/json").
                        header("Authorization","Bearer " + Access_Token).
                        //body(Payload.).
                        when().get("/v1/user").
                        then().assertThat().log().all().statusCode(200).
                        extract().response().asString();

        ReusuableMethod.jsonPathConverter(response).getString("id");
        userID = ReusuableMethod.jsonPathConverter(response).getString("id");
        System.out.println("User ID  is " + userID);
        System.out.println("Add call is success");
        //throw new io.cucumber.java.PendingException();
    }

    @Test(enabled = true ,priority = 4)
    @When("Add the Subscription Plan")
    public static void addSubscription() {
        ReusuableMethod.getURIAddSubscription();
        String response =
                given().log().all().
                        header("Authorization", "cms a7831b8b-4ca5-4513-8c32-fb03cc26ada7").
                        header("Cookie", "AWSALB=GRknu/rGf4Inrn3zUzMsrwDxH7IB1brG4039Wg26kzha+ohJa3mFbtYTc8WPWq1qh5RznPVAUQ4dG+rD7JPe1zmbEMPxLNM/vi2pzfuxHMBs5JQdVcVOhQ9x2iDf; AWSALBCORS=GRknu/rGf4Inrn3zUzMsrwDxH7IB1brG4039Wg26kzha+ohJa3mFbtYTc8WPWq1qh5RznPVAUQ4dG+rD7JPe1zmbEMPxLNM/vi2pzfuxHMBs5JQdVcVOhQ9x2iDf").
                        header("Content-Type", "application/json").
                        body(Payload.addSubscription(userID)).
                        when().post("order-bff/v1/manage/subscription").
                        then().assertThat().log().all().statusCode(200).
                        extract().response().asString();

        ReusuableMethod.jsonPathConverter(response).getString("id");
        subscriptionID = ReusuableMethod.jsonPathConverter(response).getString("id");
        System.out.println("Subscription ID  is " + subscriptionID);
        System.out.println("addSubscription is success");
       // throw new io.cucumber.java.PendingException();
    }

    //@Test(enabled = true ,priority = 5)
    @Then("Advance Renewal User is created")
    public static void addAdvanceRenewalCampaign() {
        ReusuableMethod.getURIAddAdvanceRenewalCampaign();
        String response =
                given().log().all().
                        header("lambda", "true").
                        header("Cookie", "AWSALB=GRknu/rGf4Inrn3zUzMsrwDxH7IB1brG4039Wg26kzha+ohJa3mFbtYTc8WPWq1qh5RznPVAUQ4dG+rD7JPe1zmbEMPxLNM/vi2pzfuxHMBs5JQdVcVOhQ9x2iDf; AWSALBCORS=GRknu/rGf4Inrn3zUzMsrwDxH7IB1brG4039Wg26kzha+ohJa3mFbtYTc8WPWq1qh5RznPVAUQ4dG+rD7JPe1zmbEMPxLNM/vi2pzfuxHMBs5JQdVcVOhQ9x2iDf").
                        header("Content-Type", "application/json").
                        body(Payload.addAdvanceRenewalCampaign(userID)).
                        when().post("/index/v2/campaign_details_inapp").
                        then().assertThat().log().all().statusCode(200).
                        extract().response().asString();

        ReusuableMethod.jsonPathConverter(response).getString("message");
        successMsg = ReusuableMethod.jsonPathConverter(response).getString("message");
        System.out.println("Success message  is " + successMsg);
        System.out.println("Call is success");
       // throw new io.cucumber.java.PendingException();
    }
    @Then("Lapser User is created")
    @Test(enabled = true ,priority = 5)
    public static void addLapserCampaign() {
        ReusuableMethod.getURIAddLapserCampaign();
        String response =
                given().log().all().
                        header("accept", "application/json").
                        header("device_id", "qwe").
                        header("esk", "cXdlX191eWg1bENYYlJ1R2JEZ2N0RjlHYzVadThXbkY1U2xkRF9fY2pj").
                        header("Content-Type","application/json").
                        body(Payload.lapserCampaign(userID)).
                        when().post("/index/v2/campaign_details_inapp").
                        then().assertThat().log().all().statusCode(200).
                        extract().response().asString();

        ReusuableMethod.jsonPathConverter(response).getString("message");
        successMsg = ReusuableMethod.jsonPathConverter(response).getString("message");
        System.out.println("Success message  is " + successMsg);
        System.out.println("Call is success");
        //throw new io.cucumber.java.PendingException();
    }

}



