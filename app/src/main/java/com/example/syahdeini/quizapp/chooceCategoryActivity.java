package com.example.syahdeini.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

public class chooceCategoryActivity extends AppCompatActivity {

    Study st;
    RadioGroup radioGroup;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooce_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        nextButton = (Button) findViewById(R.id.buttonNext);


        Intent i = getIntent();
        st = (Study)i.getSerializableExtra("studyObject");
//        for(Category j;j<st.num_categories();j++)
//        {
        for(Category cat: st.getCategories())
        {
            RadioButton rd = new RadioButton(this);
            rd.setLayoutParams(new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            rd.setText(cat.name);
            radioGroup.addView(rd);
        }


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int rdId = radioGroup.getCheckedRadioButtonId();
                if(rdId==-1)
                    Popup.short_toast(getApplicationContext(),"please select a category");
                else {
                    RadioButton selectedRd = (RadioButton) findViewById(rdId);
                    st.selectedCategoryName = (String) selectedRd.getText();
//                    st.setActive_catg((String) selectedRd.getText());

                    Intent i = new Intent(chooceCategoryActivity.this,QuizActivity.class);
                    Bundle bundle  = new Bundle();
                    bundle.putSerializable("studyObject",st);
                    i.putExtras(bundle);
                    startActivity(i);
                }



            }
        });
    }

}
