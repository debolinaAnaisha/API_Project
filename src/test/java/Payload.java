public class Payload {
    public static String email = ReusuableMethod.generateEmailRandomly();
    public static String transactionID = ReusuableMethod.generateTransactionIDRandomly();

    public static String createUser() {
        String s = "{\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"first_name\": \"TEST\",\n" +
                "  \"last_name\": \"vr\",\n" +
                "  \"registration_country\": \"IN\",\n" +
                "  \"registration_region\": \"KA\"\n" +
                "  }\n";
        return s;
    }

    public static String getBearerToken() {
        String s = "{\n" +
                "    \"email\": \"" + email + "\",\n" +
                "    \"password\": \"123456\",\n" +
                "    \"platform\": \"app1\",\n" +
                "    \"guest_token\": \"zee5_guest\",\n" +
                "    \"aid\": 1,\n" +
                "    \"cttl\": 1800\n" +
                "}";
        return s;

    }

    public static String addSubscription(String userID) {
        String s = "{\n" +
                " \"customer_id\":\"" + userID + "\",\n" +
                " \"subscription_plan_id\": \"0-11-3237\",\n" +
                " \"subscription_end\": \"2023-06-20\",\n" +
                " \"ip_address\": \"192.168.0.1\",\n" +
                " \"country\": \"IN\",\n" +
                " \"recurring_enabled\": false,\n" +
                " \"additional\": {\n" +
                "  \"transaction_id\": \"" + transactionID + "\"\n" +
                " },\n" +
                " \"recuring_mandateId\": \"1234\"\n" +
                "}";
        return s;

    }

    public static String addAdvanceRenewalCampaign(String userID) {
        String s = "{\n" +
                "    \"targetId\": 1634122325,\n" +
                "    \"key_values\": {\n" +
                "        \"campaign_name\": \"ADVANCE-RENEWAL\",\n" +
                "        \"plan_id\": \"0-11-3237\",\n" +
                "        \"platform_name\": \"Web\",\n" +
                "        \"UniqueId\": \"" + userID +"\"\n" +
                "    },\n" +
                "    \"profiles\": [\n" +
                "        {\n" +
                "            \"key_values\": {\n" +
                "                \"campaign_name\": \"ADVANCE-RENEWAL\",\n" +
                "                \"plan_id\": \"0-11-3237\",\n" +
                "                \"platform_name\": \"Web\",\n" +
                "                \"UniqueId\": \""+ userID +"\"\n" +
                "            },\n" +
                "            \"identity\": \"5998b4f1-0ae9-49ff-99ac-c5c4ba4e15e4\",\n" +
                "            \"all_identities\": [\n" +
                "                \"5998b4f1-0ae9-49ff-99ac-c5c4ba4e15e4\"\n" +
                "            ],\n" +
                "            \"objectId\": \"__g8d4a1eda96a84e058cdf508aee594e88\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        return s;
    }
    public static String lapserCampaign(String userID) {
        String s = "{\n" +
                "    \"targetId\": 1634122325,\n" +
                "    \"key_values\": {\n" +
                "        \"campaign_name\": \"LAPSER\",\n" +
                "        \"plan_id\": \"0-11-3237\",\n" +
                "        \"platform_name\": \"Web\",\n" +
                "        \"UniqueId\": \""+ userID +"\"\n"+
                "    },\n" +
                "    \"profiles\": [\n" +
                "        {\n" +
                "            \"key_values\": {\n" +
                "                \"campaign_name\": \"LAPSER\",\n" +
                "                \"plan_id\": \"0-11-3237\",\n" +
                "                \"platform_name\": \"Web\",\n" +
                "                \"UniqueId\": \""+ userID +"\"\n" +
                "            },\n" +
                "            \"identity\": \"5998b4f1-0ae9-49ff-99ac-c5c4ba4e15e4\",\n" +
                "            \"all_identities\": [\n" +
                "                \"5998b4f1-0ae9-49ff-99ac-c5c4ba4e15e4\"\n" +
                "            ],\n" +
                "            \"objectId\": \"__g8d4a1eda96a84e058cdf508aee594e88\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        return s;
    }


}
