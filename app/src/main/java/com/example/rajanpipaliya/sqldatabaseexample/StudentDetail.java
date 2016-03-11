package com.example.rajanpipaliya.sqldatabaseexample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentDetail extends ActionBarActivity implements android.view.View.OnClickListener
{


    Button save,delete,close;
    EditText name,age,email;
    private int _student_id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        save=(Button)findViewById(R.id.btnSave);
        delete=(Button)findViewById(R.id.btnDelete);
        close=(Button)findViewById(R.id.btnClose);
        name=(EditText)findViewById(R.id.editTextName);
        email=(EditText)findViewById(R.id.editTextEmail);
        age=(EditText)findViewById(R.id.editTextAge);
       save.setOnClickListener(StudentDetail.this);
        delete.setOnClickListener(StudentDetail.this);
        close.setOnClickListener(StudentDetail.this);
        _student_id=0;
        Intent intent=getIntent();
        _student_id=intent.getIntExtra("student_Id",0);
        StudentRepo repo=new StudentRepo(this);
        studentdata student=new studentdata();
        student=repo.getstudentById(_student_id);
        name.setText(String.valueOf(student.name));
        email.setText(String.valueOf(student.email));
        age.setText(String.valueOf(student.age));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_detail, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view==findViewById(R.id.btnSave)){
            studentdata student=new studentdata();
            StudentRepo repo=new StudentRepo(this);
            student.name=name.getText().toString();
            student.email=email.getText().toString();
            student.age=Integer.parseInt(age.getText().toString());

            if (_student_id==0){
                _student_id = repo.insert(student);

                Toast.makeText(this, "New Student Insert", Toast.LENGTH_SHORT).show();
            }else{

                repo.update(student);
                Toast.makeText(this,"Student Record updated",Toast.LENGTH_SHORT).show();
            }
        }

        else if(view==findViewById(R.id.btnDelete)){
            StudentRepo repo = new StudentRepo(this);
            repo.delete(_student_id);
            Toast.makeText(this, "Student Record Deleted", Toast.LENGTH_SHORT);
            finish();
        }
        else if (view==findViewById(R.id.btnClose)){
            finish();
        }
    }
}
