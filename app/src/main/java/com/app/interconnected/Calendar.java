package com.app.interconnected;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Calendar extends Fragment {

    CalendarView calendarView;
    TextView dateDisplay;

    public Calendar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lihat = inflater.inflate(R.layout.activity_calendar, container, false);
        super.onCreate(savedInstanceState);

        calendarView = (CalendarView) lihat.findViewById(R.id.calendarView);
        /*dateDisplay = (TextView) lihat.findViewById(R.id.dateDisplay);
        dateDisplay.setText("Date: ");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                dateDisplay.setText("Date: " + i2 + " / " + i1 + " / " + i);

                Toast.makeText(getContext(), "Selected Date:\n" + "Day = " + i2 + "\n" + "Month = " + i1 + "\n" + "Year = " + i, Toast.LENGTH_SHORT).show();
            }
        });
        */
        return lihat;
    }

}
