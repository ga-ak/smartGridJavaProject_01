package project.view2;

import project.dao.Controller;
import project.dao.DAO;

public class DAOContainer {
    public static DAO dao = new DAO(new Controller());
}
