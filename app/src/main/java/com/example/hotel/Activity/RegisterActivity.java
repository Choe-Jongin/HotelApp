package com.example.hotel.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hotel.ConnectServer;
import com.example.hotel.R;
import com.example.hotel.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText id = (EditText)findViewById(R.id.edit_registerid);
        EditText pw = (EditText)findViewById(R.id.edit_registerpw);
        EditText name = (EditText)findViewById(R.id.edit_registername);
        EditText card = (EditText)findViewById(R.id.edit_registercard);


    }


    public void btn_registerclick(View v){
        if( v.getId() == R.id.btn_register){

            EditText id = (EditText)findViewById(R.id.edit_registerid);
            EditText pw = (EditText)findViewById(R.id.edit_registerpw);
            EditText name = (EditText)findViewById(R.id.edit_registername);
            EditText card = (EditText)findViewById(R.id.edit_registercard);
            try {

                JSONObject o = new JSONObject();
                o.put("id",id.getText());
                o.put("password",pw.getText());
                o.put("name",name.getText());
                o.put("cardnum",card.getText());

                JSONArray re = ConnectServer.POST(ConnectServer.getAddress("/login/register"), o);
                int code = ConnectServer.getCode();

                if( code == 403){
                    Toast.makeText(getApplicationContext(), "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                }else if(code == 401 ){
                    Toast.makeText(getApplicationContext(), "아이디 or 비밀번호 or 이름 미입력", Toast.LENGTH_SHORT).show();
                }else if( code == 402){
                    Toast.makeText(getApplicationContext(), "알수 없는 이유로 가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                }else if(code == 200){
                    Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();
                    finish();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}