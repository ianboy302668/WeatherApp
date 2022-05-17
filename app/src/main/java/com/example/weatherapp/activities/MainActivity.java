package com.example.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.weatherapp.R;
import com.example.weatherapp.database.NotesDatabase;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_NOTE=1;
    public static final int REQUEST_CODE_UPDATE_NOTE=2;
    public static final int REQUEST_CODE_SHOW_NOTE=3;
    public static final int REQUEST_CODE_SELECT_IMAGE=4;
    public static final int REQUEST_CODE_STORAGE_PERMISSION=5;
    private NotesDatabase notesDatabase;

    private String selectedImagePath;
    ImageView photo;

    FrameLayout diary,camera;
    Intent intent=new Intent();
    int a=0;
    TextView text_view,temp,advice,highlow,hint;
    Spinner spinner1,spinner2;
    //List<String>Biglocationstr=new ArrayList<String>();
    //List<List<String>>Smalllocationstr=new ArrayList<List<String>>();
    ArrayAdapter<String>locationList;
    ArrayAdapter<String>slocationList;
    String bigtext,smalltext,description;
    ImageView imageView;
    int tempint;

    List<List<String>>Allarray=new ArrayList<List<String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_view = findViewById(R.id.locate);
        temp=findViewById(R.id.temprature);
        advice=findViewById(R.id.advice);
        spinner1=findViewById(R.id.locationspinner);
        spinner2=findViewById(R.id.slocationspinner);
        highlow=findViewById(R.id.highandlow);
        imageView=findViewById(R.id.weather);
        hint=findViewById(R.id.hint);

        text_view.setVisibility(View.INVISIBLE);

        new Thread(new Runnable(){
            @Override
            public void run(){
                MysqlCon con = new MysqlCon();
                con.run();
                final  List<List<String>> array = con.getData();
                int creat =0;
                List<String>bufferlocationstr=new ArrayList<String>();
                for(int i=0;i<array.size();i++){
                    List<String>buffer=new ArrayList<String>();
                    buffer.add(array.get(i).get(0));
                    buffer.add(array.get(i).get(1));
                    buffer.add(array.get(i).get(2));
                    buffer.add(array.get(i).get(3));
                    buffer.add(array.get(i).get(4));
                    buffer.add(array.get(i).get(5));
                    Allarray.add(buffer);
                   /* if(!Biglocationstr.contains(array.get(i).get(0))){
                        if (!bufferlocationstr.isEmpty()){
                            List<String>buffer2locationstr=new ArrayList<String>();
                            for(int j=0;j<bufferlocationstr.size();j++)
                            {
                                buffer2locationstr.add(bufferlocationstr.get(j));
                            }
                            Smalllocationstr.add(buffer2locationstr);
                            bufferlocationstr.clear();
                        }
                        Biglocationstr.add(array.get(i).get(0));
                        bufferlocationstr.add(array.get(i).get(0));
                        bufferlocationstr.add(array.get(i).get(1));
                    }
                    else{
                        bufferlocationstr.add(array.get(i).get(1));
                    }*/
                }

                //縣市 地區 溫度 描述 最低 最高
                Log.v("OK", Allarray.get(55).get(3)+Allarray.get(55).get(1)+String.valueOf(Allarray.get(0).get(2)));
                temp.post(new Runnable() {
                    @Override
                    public void run() {
                        temp.setText(Allarray.get(55).get(2)+"°C");
                        highlow.setText("最低溫" +Allarray.get(55).get(4)+"°C 最高溫"+Allarray.get(55).get(5)+"°C");
                        description=Allarray.get(55).get(3);
                        tempint=Integer.parseInt(Allarray.get(55).get(2));
                        hint.setVisibility(View.INVISIBLE);
                        text_view.setVisibility(View.VISIBLE);
                        if(description.equals("晴"))imageView.setImageResource(R.drawable.sun);
                        else if(description.equals("陰"))imageView.setImageResource(R.drawable.cloudy);
                        else if(description.equals("晴有霾"))imageView.setImageResource(R.drawable.sun_and_foggy);
                        else if(description.equals("有霧"))imageView.setImageResource(R.drawable.foggy);
                        else if(description.equals("多雲"))imageView.setImageResource(R.drawable.manycloud);
                        else if(description.equals("陰有雨"))imageView.setImageResource(R.drawable.cloudandrain);

                        if(Integer.parseInt(Allarray.get(55).get(4))-Integer.parseInt(Allarray.get(55).get(4))>7)
                        {
                            advice.setText("溫差很大，建議洋蔥式穿搭!");
                        }
                        else  if(Integer.parseInt(Allarray.get(55).get(2))<=15)
                        {
                            if(description.equals("陰"))
                            {
                                advice.setText("好冷喔，穿多一點喔，不要感冒了喔!\n阿天都灰灰的，可以穿亮一點，會比較顯眼!");
                            }
                            else
                            {
                                advice.setText("好冷喔，穿多一點喔，不要感冒了喔!");
                            }

                        }
                        else  if(Integer.parseInt(Allarray.get(55).get(2))>=30)
                        {

                            advice.setText("好熱喔，穿少一點喔，不要中暑了喔!");
                        }
                        else
                        {
                            advice.setText("風和日麗，上衣適合穿一件舒服的衣服配上薄外套喔!");
                        }
                    }
                });

            }
        }).start();

        locationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.biglocate));
        slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate1));
        locationList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        slocationList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(locationList);
        spinner2.setAdapter(slocationList);
        //spinner1.setSelection(0);
        //spinner2.setSelection(0);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                bigtext=spinner1.getSelectedItem().toString();
                smalltext=spinner2.getSelectedItem().toString();
                text_view.setText(bigtext+smalltext);

                for(int j=0;j<100;j++){
                    if(Allarray.size()==100){
                        if(bigtext.equals(Allarray.get(j).get(0)) && smalltext.equals(Allarray.get(j).get(1))){
                            temp.setText(Allarray.get(j).get(2)+"°C");
                            highlow.setText("最低溫" +Allarray.get(j).get(4)+"°C 最高溫"+Allarray.get(j).get(5)+"°C");
                            description=Allarray.get(j).get(3);
                            if(description.equals("晴"))imageView.setImageResource(R.drawable.sun);
                            else if(description.equals("陰"))imageView.setImageResource(R.drawable.cloudy);
                            else if(description.equals("晴有霾"))imageView.setImageResource(R.drawable.sun_and_foggy);
                            else if(description.equals("有霧"))imageView.setImageResource(R.drawable.foggy);
                            else if(description.equals("多雲"))imageView.setImageResource(R.drawable.manycloud);
                            else if(description.equals("陰有雨"))imageView.setImageResource(R.drawable.cloudandrain);

                            tempint=Integer.parseInt(Allarray.get(j).get(2));

                            if(Integer.parseInt(Allarray.get(j).get(4))-Integer.parseInt(Allarray.get(j).get(4))>7)
                            {
                                advice.setText("溫差很大，建議洋蔥式穿搭!");
                            }
                            else  if(Integer.parseInt(Allarray.get(j).get(2))<=15)
                            {
                                if(description.equals("陰"))
                                {
                                    advice.setText("好冷喔，穿多一點喔，不要感冒了喔!\n阿天都灰灰的，可以穿亮一點，會比較顯眼!");
                                }
                                else
                                {
                                    advice.setText("好冷喔，穿多一點喔，不要感冒了喔!");
                                }

                            }
                            else  if(Integer.parseInt(Allarray.get(j).get(2))>=30)
                            {

                                advice.setText("好熱喔，穿少一點喔，不要中暑了喔!");
                            }
                            else
                            {
                                advice.setText("風和日麗，上衣適合穿一件舒服的衣服配上薄外套喔!");
                            }
                        }
                    }
                }

                a=i;
                if(i==0) slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate1));
                else if(i==1)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate2));
                else if(i==2)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate3));
                else if(i==3)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate4));
                else if(i==4)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate5));
                else if(i==5)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate6));
                else if(i==6)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate7));
                else if(i==7)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate8));
                else if(i==8)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate9));
                else if(i==9)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate10));
                else if(i==10)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate11));
                else if(i==11)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate12));
                else if(i==12)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate13));
                else if(i==13)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate14));
                else if(i==14)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate15));
                else if(i==15)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate16));
                else if(i==16)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate17));
                else if(i==17)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate18));
                else if(i==18)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate19));
                else if(i==19)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate20));
                else if(i==20)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate21));
                else if(i==21)slocationList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locate22));

                spinner2.setAdapter(slocationList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                smalltext=spinner2.getSelectedItem().toString();
                text_view.setText(bigtext+smalltext);

                for(int j=0;j<100;j++){
                    if(Allarray.size()==100){
                        if(bigtext.equals(Allarray.get(j).get(0)) && smalltext.equals(Allarray.get(j).get(1))){
                            temp.setText(Allarray.get(j).get(2)+"°C");
                            highlow.setText("最低溫" +Allarray.get(j).get(4)+"°C 最高溫"+Allarray.get(j).get(5)+"°C");
                            description=Allarray.get(j).get(3);
                            tempint=Integer.parseInt(Allarray.get(j).get(2));

                            if(description.equals("晴"))imageView.setImageResource(R.drawable.sun);
                            else if(description.equals("陰"))imageView.setImageResource(R.drawable.cloudy);
                            else if(description.equals("晴有霾"))imageView.setImageResource(R.drawable.sun_and_foggy);
                            else if(description.equals("有霧"))imageView.setImageResource(R.drawable.foggy);
                            else if(description.equals("多雲"))imageView.setImageResource(R.drawable.manycloud);
                            else if(description.equals("陰有雨"))imageView.setImageResource(R.drawable.cloudandrain);

                            if(Integer.parseInt(Allarray.get(j).get(4))-Integer.parseInt(Allarray.get(j).get(4))>7)
                            {
                                advice.setText("溫差很大，建議洋蔥式穿搭!");
                            }
                            else  if(Integer.parseInt(Allarray.get(j).get(2))<=15)
                            {
                                if(description.equals("陰"))
                                {
                                    advice.setText("好冷喔，穿多一點喔，不要感冒了喔!\n阿天都灰灰的，可以穿亮一點，會比較顯眼!");
                                }
                                else
                                {
                                    advice.setText("好冷喔，穿多一點喔，不要感冒了喔!");
                                }

                            }
                            else  if(Integer.parseInt(Allarray.get(j).get(2))>=30)
                            {

                                advice.setText("好熱喔，穿少一點喔，不要中暑了喔!");
                            }
                            else
                            {
                                advice.setText("風和日麗，上衣適合穿一件舒服的衣服配上薄外套喔!");
                            }
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        diary=findViewById(R.id.diaryFrame);
        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(MainActivity.this,diary.class);
                startActivity(intent);
            }
        });

        camera=findViewById(R.id.cameraFrame);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(MainActivity.this,choose_clothes.class);
                intent.putExtra("temp",tempint);
                startActivity(intent);
            }
        });

        selectedImagePath="";
        getNotes();
        /*photo = (ImageView)findViewById(R.id.newphoto);
        photo.setVisibility(View.VISIBLE);
        ImageView i = (ImageView)findViewById(R.id.temp);
        i.setVisibility(View.GONE);
        photo.setImageResource(R.drawable.ca);
        /*
        getNotes();
        selectImage();*/
    }



    private void getNotes(){

        @SuppressLint("StaticFieldLeak")
        class GetNotesTask extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids){
                selectedImagePath= NotesDatabase
                        .getNotesDatabase(getApplicationContext())
                        .noteDao().getLastNotesUri();
                return null;
            }

        }

        new GetNotesTask().execute();
    }






}