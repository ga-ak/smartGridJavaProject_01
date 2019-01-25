package feature.message.SenderID;
import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.SenderID;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleRegister
 * @brief This sample code demonstrate how to request sender number register through CoolSMS Rest API PHP
 */
public class ExampleRegister {
  public static void main(String[] args) {
    String api_key = "NCSWPDL8IUFAJVNA";
    String api_secret = "QJYNG3NEB6W8MQ1CWOE9OM5LSZGF5NJB";

    try {
      SenderID coolsms = new SenderID(api_key, api_secret);

      // phone are mandatory. must be filled
      HashMap<String, String> params = new HashMap<String, String>();
      params.put("phone", "01020142889"); // sender number to register

      // Optional parameters for your own needs
      // params.put("site_user", "admin"); // site user id. '__private__' is default

      JSONObject obj = (JSONObject) coolsms.register(params);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}
