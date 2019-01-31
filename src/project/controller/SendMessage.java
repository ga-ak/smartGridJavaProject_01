package project.controller;

import net.nurigo.java_sdk.api.GroupMessage;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class SendMessage {

    public void send(String phoneNum, String name, String formatedText) {
        String api_key = "NCSWPDL8IUFAJVNA";
        String api_secret = "QJYNG3NEB6W8MQ1CWOE9OM5LSZGF5NJB";
        Message coolsms = new Message(api_key, api_secret);
        String text = String.format(formatedText, name, phoneNum);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNum); // 수신번호
        params.put("from", "01020142889"); // 발신번호
        params.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
        params.put("text", text); // 문자내용
        params.put("app_version", "JAVA SDK v2.1"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }

    public void groupSend(String[][] groupInfo) {
        String api_key = "NCSWPDL8IUFAJVNA";
        String api_secret = "QJYNG3NEB6W8MQ1CWOE9OM5LSZGF5NJB";
        GroupMessage coolsms = new GroupMessage(api_key, api_secret);
        String groupID ="";
        // Optional parameters for your own needs
        HashMap<String, String> params = new HashMap<String, String>();
        // params.put("charset", "utf8"); // utf8, euckr default value is utf8
        // params.put("srk", "293DIWNEK103"); // Solution key
        // params.put("mode", "test"); // If 'test' value, refund cash to point
        // params.put("delay", '10'); // '0~20' delay messages
        // params.put("force_sms", true); // true is always send sms ( default true )
        // params.put("app_version", ""); 	// App version

        try {
            JSONObject obj = (JSONObject) coolsms.createGroup(params);
            System.out.println(obj.toString() + "그룹 생성 완료!");
            String temp = obj.toString();
            groupID = temp.substring(13, temp.length() - 2);
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

        //JSONObject msg = new JSONObject();
        JSONArray messages = new JSONArray();
        String group_id = groupID; // Group ID

        for (int i = 0; i < groupInfo[0].length; i++) {
            JSONObject msg = new JSONObject();
            // options(to, from, text) are mandatory. must be filled
            msg.put("type", "SMS");
            msg.put("to", groupInfo[1][i]); // 수신번호
            msg.put("text", groupInfo[0][i]+"한테 자바가 보낸 Test Message!!"); // 문자내용
            msg.put("from", "01020142889"); // 10월 16일 부터 발신번호 등록제 적용으로 인해 등록된 발신번호만 사용이 가능합니다

            // Optional parameters for your own needs
            // msg.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
            // msg.put("image_id", "image_id"); // image_id. type must be set as 'MMS'
            // msg.put("refname", ""); // Reference name
            // msg.put("country", "82"); // Korea(82) Japan(81) America(1) China(86) Default is Korea
            // msg.put("datetime", "20140106153000"); // Format must be(YYYYMMDDHHMISS) 2014 01 06 15 30 00 (2014 Jan 06th 3pm 30 00)
            // msg.put("subject", "Message Title"); // set msg title for LMS and MMS
            // msg.put("delay", "10"); // '0~20' delay messages
            // msg.put("sender_key", "5554025sa8e61072frrrd5d4cc2rrrr65e15bb64"); // 알림톡 사용을 위해 필요합니다. 신청방법 : http://www.coolsms.co.kr/AboutAlimTalk
            // msg.put("template_code", "C004"); // 알림톡 template code 입니다. 자세한 설명은 http://www.coolsms.co.kr/AboutAlimTalk을 참조해주세요.

            messages.add(msg); // 원하는 만큼 JSONObject를 넣어주시면 됩니다
        }


        try {
            JSONObject obj = (JSONObject) coolsms.addMessagesJSON(group_id, messages);
            System.out.println(obj.toString()+" 그룹 메시지 저장완료!");
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

        try {
            JSONObject obj = (JSONObject) coolsms.sendGroupMessage(group_id);
            System.out.println(obj.toString()+ " 그룹 메시지 전송 완료");
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

        String group_ids = groupID; // Group IDs

        try {
            JSONObject obj = (JSONObject) coolsms.deleteGroups(group_ids);
            System.out.println(obj.toString() + " 그룹 삭제 완료!");
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

    }
}
