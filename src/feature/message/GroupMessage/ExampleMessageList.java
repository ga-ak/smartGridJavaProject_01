package feature.message.GroupMessage;
import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.GroupMessage;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleCreateGroup
 * @brief This sample code demonstrate check message list through CoolSMS Rest API PHP
 */
public class ExampleMessageList {
  public static void main(String[] args) {
    String api_key = "NCSWPDL8IUFAJVNA";
    String api_secret = "QJYNG3NEB6W8MQ1CWOE9OM5LSZGF5NJB";
    GroupMessage coolsms = new GroupMessage(api_key, api_secret);

    // Optional parameters for your own needs
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("group_id", "GID56FA3B1BF0826"); // Group ID
    // params.put("offset", "0"); // default 0
    // params.put("limit", "20"); // default 20

    try {
      JSONObject obj = (JSONObject) coolsms.getMessageList(params);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}
