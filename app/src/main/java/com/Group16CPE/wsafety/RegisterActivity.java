package com.Group16CPE.wsafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText username,password,cnfrmpass;
    Button register,login;
    DBHelper Db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        cnfrmpass=findViewById(R.id.cnfrmpass);
        register=findViewById(R.id.register);
        login=findViewById(R.id.rlogin);
        Db=new DBHelper(this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String cpass=cnfrmpass.getText().toString();

                if(user.equals("")||pass.equals("")||cpass.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please fill all the input fields", Toast.LENGTH_SHORT).show();
                }else {
                    if(pass.equals(cpass)){
                        Boolean checkuser= Db.checkusername(user);
                        if (checkuser==false){
                            Boolean insert= Db.insertData(user,pass);
                            if (insert==true){
                                Toast.makeText(RegisterActivity.this, "Registered succesfully.", Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }


                    }else{
                        Toast.makeText(RegisterActivity.this, "Two passwords didn't match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
