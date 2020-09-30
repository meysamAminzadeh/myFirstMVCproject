package model.DA;

import com.mockrunner.mock.jdbc.MockResultSet;
import model.BL.Md5;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by asus on 4/30/2020.
 */
public class Transactions {
    Connection connection;
    PreparedStatement preparedStatement;

    public Transactions() {

    /* try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "mysql123");

        } catch (ClassNotFoundException e) {
            System.out.println("khata dar class for name");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("khata dar masir connections");
            e.printStackTrace();
        }*/
    }

    public void execute(String query) {
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            System.out.println(" INSERT ANJAM shod ");

        } catch (Exception e) {
            System.out.println("khata dar methode execute");
            e.printStackTrace();
        }
    }


    public ResultSet fetch(String query) {

        ResultSet rs = null;
       /* try {
            preparedStatement=connection.prepareStatement(query);
            r=preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("khata dar methode fetch");
            e.printStackTrace();
        }*/

        try {
            rs = makeMyResultSet();

        } catch (Exception e) {

            System.out.println(" khata dar rs ");
            System.out.println(e.getMessage());
        }

        return rs;
    }


    public ResultSet makeMyResultSet() throws Exception {
        Md5 md5 = new Md5();
        List<String> headers = new ArrayList<String>();
        headers.add("id");
        headers.add("email");
        headers.add("username");
        headers.add("password");

        List<List<Object>> data = new ArrayList<List<Object>>();
        for (int i = 0; i < 4; i++) {
            List<Object> objects = new ArrayList<Object>();
            objects.add(new Integer(i));
            objects.add("aminzadeh_m_t" + i + "@yahoo.com");
            objects.add("meysam" + i);
            objects.add(md5.reciveMD5(String.valueOf(i)));
            data.add(objects);
        }

        ResultSet rs = getResultSet(headers, data);
        return rs;
    }

    public ResultSet getResultSet
            (List<String> headers, List<List<Object>> data)
            throws Exception {

        // validation
        if (headers == null || data == null) {
            throw new Exception("null parameters");
        }

        if (headers.size() != data.size()) {
            throw new Exception("parameters size are not equals");
        }

        // create a mock result set
        MockResultSet mockResultSet = new MockResultSet("myResultSet");

        // add header
        for (String string : headers) {
            mockResultSet.addColumn(string);
        }

        // add data
        for (List<Object> list : data) {
            mockResultSet.addRow(list);
        }

        return mockResultSet;
    }


    public Map<String, String> fetchMap(String email, String password) {

        Map<String, String> map = new HashMap<>();
        Md5 md5 = new Md5();

        map.put("aminzadeh_m_t@yahoo.com", md5.reciveMD5("123"));
        map.put("aminzadeh_m_t@yahoo.com", md5.reciveMD5("123456"));
        map.put("aminzadeh_m_t@yahoo.com", md5.reciveMD5("123567"));
        map.put("saeed@gmail.com", md5.reciveMD5("123456"));
        map.put("test@gmail.com", md5.reciveMD5("mey@123"));


        map = map.entrySet().stream().filter(k -> email.contains(k.getKey()))
                .filter(v -> md5.reciveMD5(password).equals(v.getValue()))
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        return map;
    }


}
