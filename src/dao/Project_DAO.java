package dao;

import java.sql.*;
import java.util.ArrayList;

public class Project_DAO implements DAO_interface {
    Connection conn = null;
    PreparedStatement psmt = null;

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

    // 행 삽입
    @Override
    public int insert(String table, String[] columns, String[] values) {
        int result = -1;
        connect();
        String sql = "INSERT INTO "+table;
        for (int i = 0; i < columns.length; i++) {
            if (i == 0) {
                sql += "("+columns[i]+", ";
            } else if (i == columns.length - 1) {
                sql += columns[i]+") VALUES ";
            } else {
                sql += columns[i]+", ";
            }
        }
        for (int i = 0; i < values.length; i++) {
            if (i == 0) {
                sql += "("+values[i]+", ";
            } else if (i == values.length - 1) {
                sql += values[i]+")";
            } else {
                sql += values[i]+", ";
            }
        }
        System.out.println(sql);
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, table);
            System.out.println();
            result = psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
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

    // 테이블 내용 선택 컬럼 조회 : 완성
    @Override
    public ArrayList<ArrayList<String>> select(String table, String[] columns, String limit) {
        connect();
        String sql = "SELECT ";
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        //ResultSet rs = null;

        for (int i = 0; i < columns.length; i++) {
            if (i == 0) {
                sql += columns[i]+", ";
            } else if (i == columns.length - 1) {
                sql += columns[i]+" FROM "+table+" WHERE "+limit;
            } else {
                sql += columns[i]+", ";
            }
        }
        System.out.println(sql);
        try {
            psmt = conn.prepareStatement(sql);
            //psmt.setString(1, limit);
            selectedChanger(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return result;
    }

    @Override
    public int update(String table, String column, String value, String limit) {
        connect();
        String sql = "UPDATE ? SET ? = ? WHERE ?";
        int result = -1;

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, table);
            psmt.setString(2, column);
            psmt.setString(3, value);
            psmt.setString(4, limit);
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
        String sql = "DELETE FROM ? WHERE ?";
        int result = -1;

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, table);
            psmt.setString(2, limit);
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
}
