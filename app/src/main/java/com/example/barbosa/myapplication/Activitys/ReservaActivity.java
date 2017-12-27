package com.example.barbosa.myapplication.Activitys;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaCas;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.barbosa.myapplication.Objetos.Cliente;
import com.example.barbosa.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ReservaActivity extends AppCompatActivity {

    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    private Cliente cliente;
    private TextView text;
    private MaterialCalendarView mCalendario;
    private Button btn_time, btn_reservar, btn_horario;
    private Toolbar myToolbar;
    Session session;
    String email, senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Marcar Reserva");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

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
                sendEmail();
            }
        });
        btn_horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHorarios();
            }
        });

        ref.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                cliente = dataSnapshot.getValue(Cliente.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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

    public void getHorarios() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReservaActivity.this);
        builder.setView(R.layout.horarios_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void sendEmail() {
        email = "matheustdd@gmail.com";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        try {
            session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email, senha);

                }
            });

            if (session != null) {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(email));
                message.setSubject("Reserva via APP");
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setContent("UMA NOVA RESERVA FOI SOLICITADA"
                        + " --> " + "Data e horario: " + text.getText().toString()
                        + " --> " + "Nome: " + cliente.getNome()
                        + " --> " + "Telefone: " + cliente.getTelefone()
                        + " --> " + "Email: " + cliente.getEmail(), "text/html; charset=utf-8");
                Transport.send(message);


            }
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Toast.makeText(ReservaActivity.this, "Reserva Enviada, Aguarde Confirmação em breve", Toast.LENGTH_SHORT).show();


    }

    private void sendEmailWithApp() {
        String to = "matheustdd@gmail.com";
        String subject = "Reserva";
        String mensage = text.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, "Olá! Você tem uma nova reserva feita pelo aplicativo. " + mensage);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Select Email app"));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        int id = item.getItemId();
        if(id == android.R.id.home){
            intent = new Intent(getApplicationContext(), MainActivity.class);

        }
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);

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

