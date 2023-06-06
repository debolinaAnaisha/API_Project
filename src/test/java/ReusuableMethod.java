import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.commons.lang3.RandomStringUtils;

public class ReusuableMethod {
    public static String email ="";
    public static String transactionId = "";
     public static String getURICreateUser(){
         return RestAssured.baseURI = "https://zee5-uapi-pt.zee5.io/";
     }
    public static String getURIGetBearertoken(){
        return RestAssured.baseURI = "https://zee5-uapi-pt.zee5.io";
    }
    public static String getURIGetUserID() { return RestAssured.baseURI = "https://auth-pt.zee5.com";}
    public static String getURIAddSubscription() { return RestAssured.baseURI = "https://pt-oms-co.zee5.io/";}

    public static String getURIAddAdvanceRenewalCampaign() { return RestAssured.baseURI = "https://cpapi-pt.zee5.io";}

    public static String getURIAddLapserCampaign() { return RestAssured.baseURI = "https://cpapi-pt.zee5.io";}
     public static JsonPath jsonPathConverter(String response){
         JsonPath js = new JsonPath(response);
         return js;
     }
     public static String generateEmailRandomly()
     {
         String allowedChars = "abcdefghij6787klmnopqrstuvwxyz" + "1234567890" + "_-.";
         email = "";
         String temp = RandomStringUtils.random(12, allowedChars);
         email = temp.substring(0, temp.length() - 9) + "@yopmail.com";
         return email;

     }

    public static String generateTransactionIDRandomly()
    {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_-.";
        transactionId = "";
        String temp = RandomStringUtils.random(25, allowedChars);
        email = temp.substring(0, temp.length() - 9) ;
        return transactionId;

    }
}
