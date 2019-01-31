package project.view2.user;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class NoticeTable {
    private StringProperty noticeID;
    private StringProperty sender;
    private StringProperty time;
    //private StringProperty check;
    private StringProperty type;

    public NoticeTable(ArrayList<String> inputArrayList) {
        this.noticeID = new SimpleStringProperty(inputArrayList.get(0));
        this.sender = new SimpleStringProperty(inputArrayList.get(1));
        this.time = new SimpleStringProperty(inputArrayList.get(2));

        if (inputArrayList.get(3) != null) {
            this.type = new SimpleStringProperty("공지");
        } else if (inputArrayList.get(4) != null) {
            this.type = new SimpleStringProperty("부서");
        } else if (inputArrayList.get(5) != null) {
            this.type = new SimpleStringProperty("프로젝트");
        }
    }



    public StringProperty noticeIDProperty() {
        return noticeID;
    }


    public StringProperty senderProperty() {
        return sender;
    }


    public StringProperty timeProperty() {
        return time;
    }


    public StringProperty typeProperty() {
        return type;
    }
}
