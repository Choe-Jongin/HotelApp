package com.example.hotel.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotel.Object.RoomInfo;
import com.example.hotel.R;

import org.w3c.dom.Text;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class reservationActivity extends AppCompatActivity {

    Calendar checkin;
    Calendar checkout;
    RoomInfo roomInfo;
    int preiod = 1;
    int addperson = 0;
    Format preiodformatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        Intent intent = getIntent();

        checkin     = ((Calendar[]) intent.getSerializableExtra("preiod"))[0];
        checkout    = ((Calendar[]) intent.getSerializableExtra("preiod"))[1];
        roomInfo    = (RoomInfo) intent.getSerializableExtra("roominfo");

        preiod = (int)(checkout.getTimeInMillis()/(24*60*60*1000))- (int)(checkin.getTimeInMillis()/(24*60*60*1000));
        addperson = 0;

        ArrayList<Integer> items = new ArrayList<Integer>();
        for( int i = 0 ; i <= roomInfo.getAdd_person_num() ; ++i)
            items.add(i);
        ArrayAdapter<Integer> spinnerAdap = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,items);

        //뷰 얻기
        ImageView ivThumbnail = (ImageView)findViewById(R.id.roomthumb);
        TextView tvPreiodstart = (TextView)findViewById(R.id.tv_period_start);
        TextView tvPreiodsend = (TextView)findViewById(R.id.tv_period_end);
        TextView tvPreiod = (TextView)findViewById(R.id.tv_period);
        TextView tvRoomtype = (TextView)findViewById(R.id.tv_roomtype);
        TextView tvCharge = (TextView)findViewById(R.id.tv_room_charge);
        TextView tvDayprice = (TextView)findViewById(R.id.tv_dayprice);
        TextView tvAddperson = (TextView)findViewById(R.id.tv_add_person);
        TextView tvMaxaddperson = (TextView)findViewById(R.id.tv_max_add_person);
        TextView tvAddprice = (TextView)findViewById(R.id.tv_addtionalprice);
        TextView tvTotalprice = (TextView)findViewById(R.id.tv_totalprice);
        Spinner spAddperson = (Spinner)findViewById(R.id.spinner_add_person);
        
        //값 설정
        ivThumbnail.setImageDrawable(roomInfo.getDrawableThumbnail());
        tvPreiodstart.setText(preiodformatter.format(checkin.getTime()));
        tvPreiodsend.setText(preiodformatter.format(checkout.getTime()));
        tvPreiod.setText(Integer.toString(preiod));
        tvPreiod.setText(preiod+"박");
        tvRoomtype.setText(roomInfo.getType());
        tvCharge.setText("$"+preiod*roomInfo.getBase_price());
        tvDayprice.setText("$"+roomInfo.getBase_price());
        tvAddperson.setText(Integer.toString(addperson));
        tvMaxaddperson.setText(roomInfo.getBase_person_num()+"명");
        tvAddprice.setText("$"+addperson*roomInfo.getAdd_price_per_person());
        tvTotalprice.setText("$"+(preiod*roomInfo.getBase_price()+addperson*roomInfo.getAdd_price_per_person()));
        spAddperson.setAdapter(spinnerAdap);
        spAddperson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ChangeAddPerson(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void ChangeAddPerson(int i){

        TextView tvAddperson = (TextView)findViewById(R.id.tv_add_person);
        TextView tvAddprice = (TextView)findViewById(R.id.tv_addtionalprice);
        TextView tvTotalprice = (TextView)findViewById(R.id.tv_totalprice);

        addperson = i;
        tvAddperson.setText(Integer.toString(addperson));
        tvAddprice.setText("$"+addperson*roomInfo.getAdd_price_per_person());
        tvTotalprice.setText("$"+(preiod*roomInfo.getBase_price()+addperson*roomInfo.getAdd_price_per_person()));
    }

}