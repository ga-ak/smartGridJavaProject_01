package feature.message.GroupMessage;
import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.GroupMessage;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleAddMessages
 * @brief This sample code demonstrate how to check group info through CoolSMS Rest API PHP
 */
public class ExampleSend {
  public static void main(String[] args) {
    String api_key = "NCSWPDL8IUFAJVNA";
    String api_secret = "QJYNG3NEB6W8MQ1CWOE9OM5LSZGF5NJB";
    GroupMessage coolsms = new GroupMessage(api_key, api_secret);

    // group_id, message_ids are mandatory.
    String group_id = "GID56FA3B1BF0826"; // Group ID	

    try {
      JSONObject obj = (JSONObject) coolsms.sendGroupMessage(group_id);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}
