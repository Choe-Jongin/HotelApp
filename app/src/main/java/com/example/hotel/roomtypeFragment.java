package com.example.hotel;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.ListFragment;

import com.example.hotel.Activity.MainActivity;
import com.example.hotel.Activity.reservationActivity;
import com.example.hotel.Object.RoomInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class roomtypeFragment extends ListFragment {

    ListViewAdapter adapter ;
    ArrayList<RoomInfo> roominfos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (android.os.Build.VERSION.SDK_INT > 9) { StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy); }
        roominfos = new ArrayList<RoomInfo>();

        JSONArray res = ConnectServer.GET("http://jonginfi.iptime.org:5000/room/info");

        // Adapter 생성 및 Adapter 지정.
        adapter = new ListViewAdapter() ;
        setListAdapter(adapter) ;

        try {
            //반복문을 돌며 객실 정보 추가
            for (int i = 0; i < res.length(); i++) {
                JSONObject o = res.getJSONObject(i);
                Drawable d = ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background);

                String k[] = new String[o.length()];
                int index = 0;
                String key = null;
                for (Iterator<String> it = o.keys(); it.hasNext();)
                    k[index++] = it.next();
                RoomInfo info = new RoomInfo(o.getInt(k[0]),o.getString(k[1]),o.getString(k[2]),o.getInt(k[3]),o.getInt(k[4]),o.getInt(k[5]),o.getInt(k[6]), o.getString(k[7]));
                info.setThumbnailUrl("http://jonginfi.iptime.org:5000/room/thumbnail/" + info.getThumbnailUrl() + ".png");
                roominfos.add(info);
                adapter.addItem(info.getDrawableThumbnail(), info.getType(), "$" +info.getBase_price() + " per day");
                adapter.addItem(info.getDrawableThumbnail(), info.getType(), "$" +info.getBase_price() + " per day");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {
        // get TextView's Text.
        ListViewItem item = (ListViewItem) l.getItemAtPosition(position) ;

//        String titleStr = item.getTitle() ;
//        String descStr = item.getDesc() ;
//        Drawable iconDrawable = item.getIcon() ;

        Intent intent = new Intent(getActivity(), reservationActivity.class);
        RoomInfo info = roominfos.get(position);
        intent.putExtra("preiod", (Serializable) MainActivity.getPeriod());
        intent.putExtra("roominfo", info);
        startActivity(intent);
    }

}
