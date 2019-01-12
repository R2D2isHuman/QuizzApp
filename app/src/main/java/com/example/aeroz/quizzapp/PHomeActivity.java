package com.example.aeroz.quizzapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aeroz.quizzapp.notActivities.Quiz;
import com.example.aeroz.quizzapp.notActivities.QuizAdapter;
import com.example.aeroz.quizzapp.notActivities.Teacher;
import com.example.aeroz.quizzapp.notActivities.Util;

import java.util.ArrayList;
import java.util.List;

public class PHomeActivity extends AppCompatActivity {

    private Teacher teacher;
    private List<Quiz> privateQuizes;
    private List<Quiz> publicQuizes;

    private ImageView imgProfile;
    private ListView listView1;
    private ListView listView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phome);
        //INITIALIZE FIELDS
        listView1 = findViewById(R.id.list_shome_publicq);
        listView2 = findViewById(R.id.list_phome_privateq);
        teacher = (Teacher)getIntent().getExtras().getSerializable("teacher");
        privateQuizes = new ArrayList<>();
        publicQuizes = new ArrayList<>();
        for(Quiz q:teacher.getQuizes())
            if(q.isPrivat()&&privateQuizes.size()<=3)
                privateQuizes.add(q);
            else if(!q.isPrivat()&&publicQuizes.size()<=3)
                publicQuizes.add(q);
        ///////////////////
        //FILL LISTVIEWS
        listView1.setAdapter(new QuizAdapter(PHomeActivity.this,android.R.layout.simple_list_item_2,publicQuizes));
        listView2.setAdapter(new QuizAdapter(PHomeActivity.this,android.R.layout.simple_list_item_2,privateQuizes));
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView text1 = view.findViewById(android.R.id.text2);
                Quiz quiz = Util.getQuizById(Integer.parseInt(text1.getText().toString()),teacher.getQuizes());
                startActivity(new Intent(PHomeActivity.this,PQuizDetailActivity.class).putExtra("quiz",quiz));
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView text1 = view.findViewById(android.R.id.text2);
                Quiz quiz = Util.getQuizById(Integer.parseInt(text1.getText().toString()),teacher.getQuizes());
                startActivity(new Intent(PHomeActivity.this,PQuizDetailActivity.class).putExtra("quiz",quiz));
            }
        });
        findViewById(R.id.ic_phome_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PHomeActivity.this,PProfileActivity.class)
                .putExtra("teacher",teacher));
            }
        });
        //////////////////
//        imgArrowPublic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(PHomeActivity.this,PPublicActivity.class).putExtra("teacher",teacher));
//            }
//        });
//        imgArrowPrivate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(PHomeActivity.this,PPublicActivity.class).putExtra("teacher",teacher));
//            }
//        });

    }
}
