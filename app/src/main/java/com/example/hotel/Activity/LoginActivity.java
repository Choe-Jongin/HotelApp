package com.example.hotel.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotel.ConnectServer;
import com.example.hotel.R;
import com.example.hotel.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private enum Result{fail, succ, notfoundid, nomath}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editid = (EditText)findViewById(R.id.username);
                EditText editpw = (EditText)findViewById(R.id.password);

                String id = editid.getText().toString();
                String pw = editpw.getText().toString();

                Result result = Login(id,pw);
                if(result == Result.succ){
                    ((TextView)findViewById(R.id.loginmsg)).setText("");
                    //Toast.makeText(getApplicationContext(), User.getInstance().getName()+"님 로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }else if(result == Result.notfoundid){
                    ((TextView)findViewById(R.id.loginmsg)).setText("아이디를 찾을 수 없습니다.");
                }else if(result == Result.nomath){
                    ((TextView)findViewById(R.id.loginmsg)).setText("비밀번호가 일치하지 않습니다.");
                }else{
                    ((TextView)findViewById(R.id.loginmsg)).setText("로그인 실패");
                }
            }
        });
    }

    private Result Login(String id, String pw) {
        try {
            JSONArray arr = ConnectServer.POST(ConnectServer.getAddress("/login"), new JSONObject("{id:" + id + ", password:" + pw + "}"));
            int reCode = ConnectServer.getCode();

            if( reCode == 200 ){
                JSONObject o = arr.getJSONObject(0);
                User.getInstance().Login(id, pw, o.getString("name"), o.getInt("authority"), o.getString("cardnum"));
                return Result.succ;
            }else if( reCode == 404){
                return Result.notfoundid;
            }
            else if( reCode == 403) {
                return Result.nomath;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Result.fail;
    }
}