package project.view2.user;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class WorkRecord {
    private SimpleStringProperty tc_userIn;
    private SimpleStringProperty tc_userOut;

    WorkRecord(ArrayList<String> tempArr){
        this.tc_userIn = new SimpleStringProperty(tempArr.get(0));
        this.tc_userOut = new SimpleStringProperty(tempArr.get(1));
    }

    public StringProperty gettc_userIn(){
        return tc_userIn;
    }

    public void settc_userIn(String tc_userIn){
        this.tc_userIn.set(tc_userIn);
    }

    public StringProperty gettc_userOut(){
        return tc_userOut;
    }

    public void settc_userOut(String tc_userOut) {
        this.tc_userIn.set(tc_userOut);
    }
}
