package project.dao;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class DAO implements DAO_interface {
    Connection conn = null;
    PreparedStatement psmt = null;
    Controller controller;

    public DAO(Controller controller) {
        this.controller = controller;
    }

    // 데이터베이스 연결 : 완성
    @Override
    public void connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 자신 데이터베이스 설정에 따라 변경해야 함
            String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
            String oraId = "cheese";
            String oraPw = "12345";

            conn = DriverManager.getConnection(url, oraId, oraPw);

            if (conn != null) {
                System.out.println("연결성공!");
            } else {
                System.out.println("연결실패...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 행 삽입 : 완성
    @Override
    public int insert(String table, ArrayList<String> columns, ArrayList<String> values) {
        int result = -1;
        connect();
        String sql = "INSERT INTO "+table + " (";
        for (int i = 0; i < columns.size(); i++) {
            if (i == columns.size() - 1) {
                sql += columns.get(i)+") ";
            } else {
                sql += columns.get(i)+", ";
            }
        }
        sql += "VALUES (";
        for (int i = 0; i < values.size(); i++) {
            if (i == values.size() - 1) {
                sql += "?)";
            } else {
                sql += "?, ";
            }
        }
        try {
            psmt = conn.prepareStatement(sql);
            System.out.println(sql);


            for (int i = 0; i < values.size(); i++) {
                psmt.setString(i + 1, values.get(i));
            }
            result = psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return result;
    }

    @Override
    public void insertBlob(String table, int index, String fileName, String path) {
        try {
            File file = new File(path);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream(file);
            while(true) {
                int x = fis.read();
                if(x == -1) break;
                bos.write(x);
            }
            fis.close();
            bos.close();
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            connect();
            String sql = "insert into "+table + " (form_id, form_name, form_data) values (?,?,?)";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, index);
            psmt.setString(2, fileName);
            psmt.setBinaryStream(3,bis,bos.size());
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertBatch(String table, ArrayList<String> columns, ArrayList<ArrayList<String>> valueArrays) {
        System.out.println("insertBatch 시작...");
        int result = -1;
        connect();
        ArrayList<String> values = valueArrays.get(0);
        String sql = "INSERT INTO "+table + " (";

        for (int i = 0; i < columns.size(); i++) {
            if (i == columns.size() - 1) {
                sql += columns.get(i)+") ";
            } else {
                sql += columns.get(i)+", ";
            }
        }
        sql += "VALUES (";
        for (int i = 0; i < values.size(); i++) {
            if (i == values.size() - 1) {
                sql += "?)";
            } else {
                sql += "?, ";
            }
        }
        try {
            psmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int k = 0; k < valueArrays.size(); k++) {
            values = valueArrays.get(k);

            try {
                //psmt = conn.prepareStatement(sql);
                for (int i = 0; i < values.size(); i++) {
                    psmt.setString(i + 1, values.get(i));
                }
                psmt.addBatch();
                psmt.clearParameters();
                System.out.println((k+1)+" 번째 sql문 배치에 저장 완료...");
                //System.out.println((k+1)+" "+sql);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            psmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        close();
        System.out.println("insertBatch 종료...");
        return result;
    }

    // 테이블 내용 전체 조회 : 완성
    @Override
    public ArrayList<ArrayList<String>> select(String table) {
        connect();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM "+table;

        try {
            psmt = conn.prepareStatement(sql);
            selectedChanger(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return result;
    }

    // 테이블 내용 선택 컬럼 조회 : ArrayList<ArrayList<String>> result에 colums의 인자가 담기는이유?
    // -> prepareStatement 의 setString을 하면 전달받은 값에 ' '를 씌워서 sql문에 전달한다...
    @Override
    public ArrayList<ArrayList<String>> select(String table, ArrayList<String> columns, String limit) {
        connect();
        String sql = "SELECT ";
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        //ResultSet rs;

        for (int i = 0; i < columns.size(); i++) {

            if (i == columns.size() - 1) {
                sql += columns.get(i)+" FROM "+table+" WHERE "+limit;
            } else if (i == 0) {
                sql += columns.get(i)+", ";
            } else {
                sql += columns.get(i)+", ";
            }
        }

        System.out.println(sql);
        try {
            psmt = conn.prepareStatement(sql);
            selectedChanger(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return result;
    }

    // 행 업데이트 :
    @Override
    public int update(String table, String column, String value, String limit) {
        connect();
        int datum = limit.indexOf("=") + 1;

        String preDatum = limit.substring(0, datum);
        String postDatum = limit.substring(datum, limit.length()).trim();

        if (!controller.isNumber(postDatum)) {
            postDatum = "\'" + postDatum + "\'";
        }
        limit = preDatum + postDatum;
        String sql = "UPDATE "+table+" SET "+column+" = ? WHERE "+limit;
        int result = -1;
        System.out.println(sql);
        try {
            psmt = conn.prepareStatement(sql);
            if (controller.isNumber(value)) {
                psmt.setInt(1, Integer.parseInt(value));
            } else {
                psmt.setString(1, value);
            }

            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return result;
    }

    @Override
    public int delete(String table, String limit) {
        connect();
        String sql = "DELETE FROM "+table+" WHERE "+limit;
        int result = -1;

        try {
            psmt = conn.prepareStatement(sql);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return result;
    }

    @Override
    public void close() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("conn 종료!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (psmt != null) {
            try {
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("psmt 종료!");
            }
        }
    }

    // select() 메소드내 ResultSet을 ArrayList<ArrayList<String>> 타입으로 변환
    public void selectedChanger(ArrayList<ArrayList<String>> result) throws SQLException {
        ResultSet rs = psmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnNumber = rsmd.getColumnCount();

        while (rs.next()) {
            ArrayList<String> temp = new ArrayList<>();
            for (int i = 1; i <= columnNumber; i++) {
                temp.add(rs.getString(i));
            }
            result.add(temp);
        }
    }

    @Override
    public Connection getConnect() {
        return conn;
    }

    @Override
    public PreparedStatement getPsmt() {
        return psmt;
    }
}
