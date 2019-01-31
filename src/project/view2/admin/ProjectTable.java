package project.view2.admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import project.dao.DAO;
import project.view2.DAOContainer;

import java.util.ArrayList;

public class ProjectTable {

    DAO dao = DAOContainer.dao;
    private StringProperty projectID;
    private StringProperty projectName;
    private StringProperty projectLeader;
    private StringProperty startDate;
    private StringProperty endDate;

    public ProjectTable(ArrayList<String> inputArrayList) {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("employee_name");

        String leaderName = dao.select("employees", columns, "employee_id = " + inputArrayList.get(2)).get(0).get(0);
        this.projectID = new SimpleStringProperty(inputArrayList.get(0));
        this.projectName = new SimpleStringProperty(inputArrayList.get(1));

        this.projectLeader = new SimpleStringProperty(leaderName);
        this.startDate = new SimpleStringProperty(inputArrayList.get(3));
        this.endDate = new SimpleStringProperty(inputArrayList.get(4));
    }

    public String getProjectID() {
        return projectID.get();
    }

    public StringProperty projectIDProperty() {
        return projectID;
    }

    public String getProjectName() {
        return projectName.get();
    }

    public StringProperty projectNameProperty() {
        return projectName;
    }

    public String getProjectLeader() {
        return projectLeader.get();
    }

    public StringProperty projectLeaderProperty() {
        return projectLeader;
    }

    public String getStartDate() {
        return startDate.get();
    }

    public StringProperty startDateProperty() {
        return startDate;
    }

    public String getEndDate() {
        return endDate.get();
    }

    public StringProperty endDateProperty() {
        return endDate;
    }
}
