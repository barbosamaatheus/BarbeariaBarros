package com.example.barbosa.myapplication.Activitys;

import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.barbosa.myapplication.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.util.Calendar;

public class ReservaActivity extends AppCompatActivity {

    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    private TextView text;
    private MaterialCalendarView mCalendario;
    private Button btn_time, btn_reservar, btn_horario;
    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Marcar Reserva");
        setSupportActionBar(myToolbar);
        text = (TextView) findViewById(R.id.txt_TextDateTime);

        btn_horario = (Button) findViewById(R.id.btn_hotarios);
        btn_time = (Button) findViewById(R.id.btn_timePicker);
        btn_reservar = (Button) findViewById(R.id.btn_reservar);
        mCalendario = (MaterialCalendarView) findViewById(R.id.calendarView);
        mCalendario.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(Calendar.getInstance()))
                .setMaximumDate(CalendarDay.from(2030, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        mCalendario.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                updateDate();
            }
        });
        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTime();
            }
        });
        btn_reservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ReservaActivity.this, "Em Breve.", Toast.LENGTH_SHORT).show();
            }
        });
        btn_horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHorarios();
            }
        });
    }

    private void updateDate() {
        CalendarDay data = mCalendario.getSelectedDate();

        dateTime.set(Calendar.YEAR, data.getYear());
        dateTime.set(Calendar.MONTH, data.getMonth());
        dateTime.set(Calendar.DAY_OF_MONTH, data.getDay());
        updateTextLabel();
    }

    private void updateTime() {
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            updateTextLabel();
        }
    };

    private void updateTextLabel() {

        text.setText(formatDateTime.format(dateTime.getTime()));

    }
    public void getHorarios(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ReservaActivity.this);
        builder.setTitle("Horarios")
                .setMessage(R.string.horario);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
 /*private void updateDate(){
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }
      DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextLabel();
        }
    };*/

