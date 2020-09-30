package model.BL;

import model.DA.Transactions;

import java.sql.ResultSet;
import java.util.Map;

/**
 * Created by asus on 4/30/2020.
 */
public class TblUsers {

    String query;
    Transactions transactions = new Transactions();
    Md5 md5= new Md5();
    public void insert(String username , String email, String password){


        username= md5.reciveMD5(username);
        System.out.println(username);

        query = "insert into users (name,email,password) values ('%s' ,'%s' , '%s')";
        query= String.format(query,username,email,password);

        transactions.execute(query);
    }

    public ResultSet select (String email, String password){
        password = md5.reciveMD5(password);
        query = "select * from users where email = '%s' and password = '%s'" ;
        query = String.format(query,email,password);
        return transactions.fetch(query);
    }

    public Map<String,String> fetchMap (String email, String password){
        return transactions.fetchMap(email,password);

    }
}
