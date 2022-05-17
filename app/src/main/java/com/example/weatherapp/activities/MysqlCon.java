package com.example.weatherapp.activities;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlCon {

    // 資料庫定義
    String mysql_ip = "db4free.net";
    int mysql_port = 3306; // Port 預設為 3306
    String db_name = "linda0106";
    String url = "jdbc:mysql://"+mysql_ip+":"+mysql_port+"/"+db_name;
    String db_user = "lindada";
    String db_password = "lindaisme";


    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.v("DB","加載驅動成功");
        }catch( ClassNotFoundException e) {
            Log.e("DB","加載驅動失敗");
            return;
        }

        // 連接資料庫
        try {
            Connection con = DriverManager.getConnection(url,db_user,db_password);
            Log.v("DB","遠端連接成功");
        }catch(SQLException e) {
            Log.e("DB","遠端連接失敗");
            Log.e("DB", e.toString());
        }
    }

    public List<List<String>> getData() {
        String data = "";
        //String [][]array=new String[][];


        List<List<String>> outerArrayList = new ArrayList<List<String>>();
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT * FROM theweather";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int i=0;
            while (rs.next())
            {
                List<String> innerArraylist = new ArrayList<String>();
                String location = rs.getString("location");
                String bigLocation=location.substring(0,3);
                innerArraylist.add(bigLocation);
                String littleLocation=location.substring(3);
                innerArraylist.add(littleLocation);
                String temp = rs.getString("temp");
                innerArraylist.add(temp);
                String this_weather = rs.getString("thisWeather");
                innerArraylist.add(this_weather);
                String lowtemp = rs.getString("lowtemp");
                innerArraylist.add(lowtemp);
                String heighttemp = rs.getString("heighttemp");
                innerArraylist.add(heighttemp);
                outerArrayList.add(innerArraylist);

                i++;
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return outerArrayList;
    }


}