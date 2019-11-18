package com.example.elecentlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        //create fields as to not cause lag when clicking save event
        final Spinner EVENTTYPE = (Spinner) findViewById(R.id.eventTypeSP);
        final Spinner EVENTCOLOR = (Spinner) findViewById(R.id.eventColorSP);
        final Spinner STIMEAMPM = (Spinner) findViewById(R.id.eventSTimeAmPm);
        final Spinner ETIMEAMPM = (Spinner) findViewById(R.id.eventETimeAmPm);
        final EditText EVENTNAME = (EditText) findViewById(R.id.eventNameET);
        final EditText EVENTNOTE = (EditText) findViewById(R.id.eventNoteET);
        final EditText EVENTSDATE = (EditText) findViewById(R.id.eventSDateET);
        final EditText EVENTSTIME = (EditText) findViewById(R.id.eventSTimeET);
        final EditText EVENTETIME = (EditText) findViewById(R.id.eventETimeET);
        final EditText EVENTRHOUR = (EditText) findViewById(R.id.remindHourET);
        final EditText EVENTRMIN = (EditText) findViewById(R.id.remindMinET);

        Button saveChanges = (Button) findViewById(R.id.saveButton);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidationHelper inputValidation = new ValidationHelper();
                Events EventClass = new Events(getApplicationContext());
                String newEvent;

                //check date and times for valid input
                if (inputValidation.isValidDate(EVENTSDATE.getText().toString()) && inputValidation.isValidTime(EVENTSTIME.getText().toString())
                    && inputValidation.isValidTime(EVENTETIME.getText().toString())) {
                    //events are stored as <eventtype>|<eventcolor>|<eventname>|<eventnote>|<eventsdate>|<eventstime>|<eventetime>|<eventrtime>
                    newEvent = EVENTTYPE.getSelectedItem().toString() + "|";
                    newEvent = newEvent + EVENTCOLOR.getSelectedItem().toString() + "|";
                    newEvent = newEvent + EVENTNAME.getText().toString() + "|";
                    newEvent = newEvent + EVENTNOTE.getText().toString() + "|";
                    newEvent = newEvent + EVENTSDATE.getText().toString() + "|";
                    newEvent = newEvent + EVENTSTIME.getText().toString() + " " + STIMEAMPM.getSelectedItem().toString() + "|";
                    newEvent = newEvent + EVENTETIME.getText().toString() + " " + ETIMEAMPM.getSelectedItem().toString() + "|";
                    newEvent = newEvent + EVENTRHOUR.getText().toString() + " " + EVENTRMIN.getText().toString();

                    //call addEvent function to save new event
                    EventClass.addEvent(newEvent);

                    Intent intent = new Intent(NewEventActivity.this, MainActivity.class);
                    startActivity(intent);
                    }

                else {
                    if (!inputValidation.isValidDate(EVENTSDATE.getText().toString())) {
                        Toast toast = Toast.makeText(getApplicationContext(),"Event date incorrect (MM/DD/YYYY)",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    if (!inputValidation.isValidTime(EVENTSTIME.getText().toString())) {
                        Toast toast = Toast.makeText(getApplicationContext(),"Event start time incorrect (HH:MM)",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    if (!inputValidation.isValidTime(EVENTETIME.getText().toString())) {
                        Toast toast = Toast.makeText(getApplicationContext(),"Event end time incorrect (HH:MM)",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
    }
}
