package com.example.hotel.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotel.R;
import com.example.hotel.User;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    static Calendar checkin = Calendar.getInstance();
    static Calendar checkout = Calendar.getInstance();
    Format formatter = new SimpleDateFormat("yyyy-MM-dd");

    private TextView checkintext;
    private TextView checkouttext;
    private enum check{in, out};
    private check type;

    static MenuItem menuItem;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkintext = (TextView) findViewById(R.id.checkindate);
        checkouttext = (TextView) findViewById(R.id.checkoutdate);
        checkout.add(Calendar.DATE,1);
        checkintext.setText(formatter.format(checkin.getTime()));
        checkouttext.setText(formatter.format(checkout.getTime()));
        type = check.in;

        Button btn_checkin = (Button)findViewById(R.id.btn_checkin);
        Button btn_checkout = (Button) findViewById(R.id.btn_checkout) ;
        btn_checkin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = check.in;
                DatePickerDialog dialog = new DatePickerDialog(view.getContext(), listener, 2020, 11, 10);
                dialog.show();
            }
        }) ;
        btn_checkout.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = check.out;
                DatePickerDialog dialog = new DatePickerDialog(view.getContext(), listener, 2020, 11, 10);
                dialog.show();
            }
        }) ;

    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //수정 대상(기본:체크인)
            TextView moditxt    = checkintext;
            Calendar modidate   = checkin;
            //반대 대상(기본:체크아웃)
            TextView countxt    = checkouttext;
            Calendar countdate  = checkout;

            //체크 아웃 날짜를 설정할 경우 반대로
            if(type == check.out){
                moditxt = checkouttext;
                modidate = checkout;
                countxt = checkintext;
                countdate = checkin;
            }
            modidate.set(year, monthOfYear, dayOfMonth);
            moditxt.setText(formatter.format(modidate.getTime()));
            //체크아웃이 체크인 이전 일 경우
            if (checkin.compareTo(checkout) >= 0) {
                Toast.makeText(getApplicationContext(), "체크아웃 날짜는 체크인 이후여야 합니다.", Toast.LENGTH_SHORT).show();
                countdate.set(modidate.get(Calendar.YEAR), modidate.get(Calendar.MONTH), modidate.get(Calendar.DATE));
                countdate.add(Calendar.DATE, type==check.in ? 1:-1);
                countxt.setText(formatter.format(countdate.getTime()));
            }
        }
    };

    public static Calendar[] getPeriod(){
        return new Calendar[]{checkin,checkout};
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_login:
                try {
                    if(User.getInstance().isLogind() == false) {
                        menuItem = item;
                        Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);
                    }else{
                        item.setTitle("로그인");
                        User.getInstance().LogOut();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}