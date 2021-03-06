/*package com.example.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.weatherapp.R;

public class judge extends AppCompatActivity {

    Bundle bundle;
    int a,b;
    boolean c,d,e;
    TextView textView;
    ImageButton back,home;
    Intent intent=new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge);

        bundle=getIntent().getExtras();

        textView=findViewById(R.id.textView6);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(judge.this,choose_clothes.class);
                startActivity(intent);
            }
        });
        home=findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(judge.this,MainActivity.class);
                startActivity(intent);
            }
        });

        a=bundle.getInt("top");
        b=bundle.getInt("bottom");
        c=bundle.getBoolean("cap");
        d=bundle.getBoolean("scarf");
        e=bundle.getBoolean("groove");

        textView.setText(a+" "+b+" "+c+" "+d+" "+e);
    }
}*/
package com.example.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.R;

import java.util.ArrayList;
import java.util.List;

public class judge extends AppCompatActivity {

    Bundle bundle;
    int a,b;
    boolean c,d,e;
    TextView textView;
    ImageButton back,home;
    Intent intent=new Intent();
    int temp=13;
    int distance;
    String string;
    boolean[] topcloths,bottomcloths;

    private ImageView up,down,object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge);

        up=(ImageView)findViewById(R.id.up);
        down=(ImageView)findViewById(R.id.down);
        object=(ImageView)findViewById(R.id.object);




        bundle=getIntent().getExtras();

        textView=findViewById(R.id.textView8);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(judge.this,choose_clothes.class);
                startActivity(intent);
            }
        });
        home=findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(judge.this,MainActivity.class);
                startActivity(intent);
            }
        });

        a=bundle.getInt("top");
        b=bundle.getInt("bottom");
        c=bundle.getBoolean("cap");
        d=bundle.getBoolean("scarf");
        e=bundle.getBoolean("groove");
        topcloths=bundle.getBooleanArray("topcloths");
        bottomcloths=bundle.getBooleanArray("bottomcloths");
        temp = bundle.getInt("temp");
        distance=26-(temp+a+b);


        if (distance>0) {
            if (distance < 2) {
                if (topcloths[10] == false) {
                    string += "????????????????????????!";
                    up.setVisibility(View.VISIBLE);
                    up.setImageResource(R.drawable.up10);
                }
            }
            if (distance < 6) {
                string = "?????????????????????????????????????????????";
                if (bottomcloths[0]==false)
                {
                    string += "??????????????????";
                    down.setVisibility(View.VISIBLE);
                    down.setImageResource(R.drawable.up0);
                }
                if (topcloths[7] == false) {
                    string += "??????????????????????????????!";
                    up.setVisibility(View.VISIBLE);
                    up.setImageResource(R.drawable.up7);
                } else if (topcloths[6] == false) {
                    string += "??????????????????????????????!";
                    up.setVisibility(View.VISIBLE);
                    up.setImageResource(R.drawable.up6);
                } else if (topcloths[8] == false) {
                    string += "???????????????????????????!";
                    up.setVisibility(View.VISIBLE);
                    up.setImageResource(R.drawable.up8);
                }
                if(c==false){
                    string += "??????????????????!";
                    object.setVisibility(View.VISIBLE);
                    object.setImageResource(R.drawable.object0);
                }
            }
            else if (distance < 10) {
                string = "????????????????????????????????????";
                if (bottomcloths[0]==false)
                {
                    string += "??????????????????";
                    down.setVisibility(View.VISIBLE);
                    down.setImageResource(R.drawable.down0);
                }
                if (topcloths[7] == false) {
                    string += "??????????????????????????????!";
                    up.setVisibility(View.VISIBLE);
                    up.setImageResource(R.drawable.up7);
                } else if (topcloths[6] == false) {
                    string += "??????????????????????????????!";
                    up.setVisibility(View.VISIBLE);
                    up.setImageResource(R.drawable.up6);
                } else if (topcloths[9] == false) {
                    string += "???????????????????????????!";
                    up.setVisibility(View.VISIBLE);
                    up.setImageResource(R.drawable.up9);
                }
                if(d==false){
                    string += "????????????!";
                    object.setVisibility(View.VISIBLE);
                    object.setImageResource(R.drawable.object1);
                }
            }
            else {
                string = "???????????????????????????";
                up.setVisibility(View.VISIBLE);
                up.setImageResource(R.drawable.up9);
            }

        }
        else if (distance < -2) {
            if (distance > -6) {
                string = "????????????????????????????????????";
                if (bottomcloths[0]==true||bottomcloths[3]==true)
                {
                    string += "????????????/??????!";
                    down.setVisibility(View.VISIBLE);
                    down.setImageResource(R.drawable.down0);
                }
            } else if (distance > -10) {
                string = "????????????????????????????????????";
                if (bottomcloths[0]==true||bottomcloths[3]==true)
                {
                    string += "????????????/??????!";
                    down.setVisibility(View.VISIBLE);
                    down.setImageResource(R.drawable.down0);
                }
            } else {
                string = "???????????????????????????";
                up.setVisibility(View.VISIBLE);
                up.setImageResource(R.drawable.hot);
            }
        } else {
            string = "????????????";
            up.setVisibility(View.VISIBLE);
            up.setImageResource(R.drawable.good);
        }




        textView.setText(string);
    }
}