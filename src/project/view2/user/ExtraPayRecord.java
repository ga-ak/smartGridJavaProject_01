package project.view2.user;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class ExtraPayRecord {

    private SimpleStringProperty tc_userEPId;
    private SimpleStringProperty tc_userOvertime;
    private SimpleStringProperty tc_userBonus;
    private SimpleStringProperty tc_userCerNum;
    private SimpleStringProperty tc_userSum;
    private SimpleStringProperty tc_userEPDate;

    public ExtraPayRecord(ArrayList<String> extraArr){
        this.tc_userEPId = new SimpleStringProperty(extraArr.get(0));
        this.tc_userOvertime = new SimpleStringProperty(extraArr.get(1));
        this.tc_userBonus = new SimpleStringProperty(extraArr.get(2));
        this.tc_userCerNum = new SimpleStringProperty(extraArr.get(3));
        this.tc_userSum = new SimpleStringProperty(extraArr.get(4));
        this.tc_userEPDate = new SimpleStringProperty(extraArr.get(5));
    }

    public StringProperty gettc_userEPId(){
        return tc_userEPId;
    }
    public StringProperty gettc_userOvertime(){
        return tc_userOvertime;
    }
    public StringProperty gettc_userBonus(){
        return tc_userBonus;
    }
    public StringProperty gettc_userCerNum(){
        return tc_userCerNum;
    }
    public StringProperty gettc_userEPDate(){
        return tc_userEPDate;
    }
    public StringProperty gettc_userSum(){
        return tc_userSum;
    }

}
