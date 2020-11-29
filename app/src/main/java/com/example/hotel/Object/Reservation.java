package com.example.hotel.Object;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Reservation implements Serializable {
    private int reservation_num;
    private String customer_id;
    private String room_num;
    private Calendar check_in_date;
    private Calendar check_out_date;

    Reservation(){
        this(0,"workin","101", Calendar.getInstance(), Calendar.getInstance());
    }
    Reservation(int reservation_num, String customer_id, String room_num, Calendar in, Calendar out){
        this.reservation_num = reservation_num;
        this.customer_id = customer_id;
        this.room_num = room_num;
        this.check_in_date = in;
        this.check_out_date = out;
    }
}
