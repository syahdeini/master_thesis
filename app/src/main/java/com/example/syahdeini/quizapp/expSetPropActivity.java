package com.example.syahdeini.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class expSetPropActivity extends AppCompatActivity {

    private EditText studyName;
    private EditText participantId;
    private EditText researchId;
    private Spinner spinCategory;
    private Study st;
    private Button setButton;
    private Spinner spinExp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_set_prop);

        studyName = (EditText) findViewById(R.id.editStName);
        participantId = (EditText) findViewById(R.id.editPartName);
        researchId = (EditText) findViewById(R.id.editResearchId);
        spinCategory = (Spinner) findViewById(R.id.spinnerExperiment);
        setButton = (Button) findViewById(R.id.setButton);

        Intent i = getIntent();
        st = (Study)i.getSerializableExtra("studyObject");

        if(st.study_name!=null)
            studyName.setText(st.study_name);
        if(st.reseracher!=null)
            researchId.setText(st.reseracher);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, st.getExperimentsName());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinExp = (Spinner) findViewById(R.id.spinnerExperiment);
        spinExp.setAdapter(adapter);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedExp = spinExp.getSelectedItem().toString();
                st.selectedExperimentName = selectedExp;
                String experiment =
                st.study_name = studyName.getText().toString();
                st.reseracher = researchId.getText().toString();
                st.participantId = participantId.getText().toString();

                Intent i = new Intent(expSetPropActivity.this,expInitialActivity.class);
                Bundle bundle  = new Bundle();
                bundle.putSerializable("studyObject",st);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }
}
