package project.view2.admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class AnnounceTable {
    private StringProperty annID;
    private StringProperty annName;
    private StringProperty annContent;
    private StringProperty annDate;

    public AnnounceTable(ArrayList<String> inputArrayList) {
        this.annID = new SimpleStringProperty(inputArrayList.get(0));
        this.annName = new SimpleStringProperty(inputArrayList.get(1));
        this.annContent = new SimpleStringProperty(inputArrayList.get(2));
        this.annDate = new SimpleStringProperty(inputArrayList.get(3));
    }

    public String getAnnID() {
        return annID.get();
    }

    public StringProperty annIDProperty() {
        return annID;
    }

    public String getAnnName() {
        return annName.get();
    }

    public StringProperty annNameProperty() {
        return annName;
    }

    public String getAnnContent() {
        return annContent.get();
    }

    public StringProperty annContentProperty() {
        return annContent;
    }

    public String getAnnDate() {
        return annDate.get();
    }

    public StringProperty annDateProperty() {
        return annDate;
    }
}
