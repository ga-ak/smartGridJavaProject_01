package feature.message.SenderID;
import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.SenderID;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleGetSenderIdList
 * @brief This sample code demonstrate how to check sender number list through CoolSMS Rest API PHP
 */
public class ExampleGetSenderIdList {
  public static void main(String[] args) {
    String api_key = "NCSWPDL8IUFAJVNA";
    String api_secret = "QJYNG3NEB6W8MQ1CWOE9OM5LSZGF5NJB";

    try {
      SenderID coolsms = new SenderID(api_key, api_secret);

      // Optional parameters for your own needs
      HashMap<String, String> params = new HashMap<String, String>();
      // params.put("site_user", "admin"); // site user id. '__private__' is default

      JSONObject obj = (JSONObject) coolsms.getSenderidList(params);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}
