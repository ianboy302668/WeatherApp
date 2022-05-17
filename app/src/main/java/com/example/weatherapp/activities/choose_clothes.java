package com.example.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import com.example.weatherapp.R;

public class choose_clothes extends Activity {

    ImageButton back,next;
    Intent i=new Intent();
    CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18;
    int top=0,bottom=0;
    boolean[] topcloths= {false,false,false,false,false,false,false,false,false,false};
    boolean[] bottomcloths= {false,false,false,false};
    boolean[] other= {false,false,false};
    Bundle bundle=new Bundle();
    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_clothes);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.setClass(choose_clothes.this, MainActivity.class);
                startActivity(i);
            }
        });

        temp=getIntent().getIntExtra("temp", temp);
        next=findViewById(R.id.confirm);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("temp",temp);
                bundle.putInt("top",top);
                bundle.putInt("bottom",bottom);
                bundle.putBoolean("cap",other[0]);
                bundle.putBoolean("scarf",other[1]);
                bundle.putBoolean("groove",other[2]);
                bundle.putBooleanArray("topcloths",topcloths);
                bundle.putBooleanArray("bottomcloths",bottomcloths);

                i.setClass(choose_clothes.this, judge.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        c1=findViewById(R.id.checkBox);
        c2=findViewById(R.id.checkBox2);
        c3=findViewById(R.id.checkBox3);
        c4=findViewById(R.id.checkBox4);
        c5=findViewById(R.id.checkBox5);
        c6=findViewById(R.id.checkBox6);
        c7=findViewById(R.id.checkBox7);
        c8=findViewById(R.id.checkBox8);
        c9=findViewById(R.id.checkBox9);
        c10=findViewById(R.id.checkBox10);
        c11=findViewById(R.id.checkBox11);
        c12=findViewById(R.id.checkBox12);
        c13=findViewById(R.id.checkBox13);
        c14=findViewById(R.id.checkBox14);
        c15=findViewById(R.id.checkBox15);
        c16=findViewById(R.id.checkBox16);
        c17=findViewById(R.id.checkBox17);
        c18=findViewById(R.id.checkBox18);

        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){top+=0;topcloths[0]=true;}
                else {top-=0;topcloths[0]=false;}
            }
        });

        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){top+=1;topcloths[1]=true;}
                else {top-=1;topcloths[1]=false;}
            }
        });

        c3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){top+=2;topcloths[2]=true;}
                else {top-=2;topcloths[2]=false;}
            }
        });

        c4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){top+=4;topcloths[3]=true;}
                else {top-=4;topcloths[3]=false;}
            }
        });

        c5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){top+=3;topcloths[4]=true;}
                else {top-=3;topcloths[4]=false;}
            }
        });

        c6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){top+=2;topcloths[5]=true;}
                else {top-=2;topcloths[5]=false;}
            }
        });

        c7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){top+=4;topcloths[6]=true;}
                else {top-=4;topcloths[6]=false;}
            }
        });
        c8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){top+=4;topcloths[7]=true;}
                else {top-=4;topcloths[7]=false;}
            }
        });
        c9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){top+=3;topcloths[8]=true;}
                else {top-=3;topcloths[8]=false;}
            }
        });
        c10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){top+=9;topcloths[9]=true;}
                else {top-=9;topcloths[9]=false;}
            }
        });
        c11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){top+=3;topcloths[10]=true;}
                else {top-=3;topcloths[10]=false;}
            }
        });
        c12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){bottom+=1;bottomcloths[0]=true;}
                else {bottom-=1;bottomcloths[0]=false;}

            }
        });
        c13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){bottom+=0;bottomcloths[1]=true;}
                else {bottom-=0;bottomcloths[1]=false;}

            }
        });
        c14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){bottom+=1;bottomcloths[2]=true;}
                else {bottom-=1;bottomcloths[2]=false;}

            }
        });
        c15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){bottom+=0;bottomcloths[3]=true;}
                else {bottom-=0;bottomcloths[3]=false;}

            }
        });
        c16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)other[0]=true;
                else other[0]=false;
            }
        });
        c17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)other[1]=true;
                else other[0]=false;
            }
        });
        c18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)other[2]=true;
                else other[2]=false;
            }
        });

    }


}