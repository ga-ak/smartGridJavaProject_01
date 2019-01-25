package feature.message.GroupMessage;
import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.GroupMessage;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleCreateGroup
 * @brief This sample code demonstrate how to check group list through CoolSMS Rest API PHP
 */
public class ExampleGroupList {
  public static void main(String[] args) {
    String api_key = "NCSWPDL8IUFAJVNA";
    String api_secret = "QJYNG3NEB6W8MQ1CWOE9OM5LSZGF5NJB";
    GroupMessage coolsms = new GroupMessage(api_key, api_secret);

    try {
      JSONObject obj = (JSONObject) coolsms.getGroupList();
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}
