package com.example.hamzafood;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class Admin_Loginphone extends AppCompatActivity {//When you extend an existing Java class (such as the AppCompatActivity class), you create a new class with the existing class’s functionality

    EditText num;
    Button sendotp,signinemail;
    TextView signup;
    CountryCodePicker cpp;
    FirebaseAuth Fauth;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//Basically Bundle class is used to stored the data of activity whenever above condition occur in app.
        super.onCreate(savedInstanceState);//the method is the best place to put initialization code
        setContentView(R.layout.activity_admin__loginphone);//method of activity class. It shows layout on screen. R.layout.main 
// findViewById is the method that finds the View by the ID it is given.
//View is a class where all the widgets are defined
        num = (EditText)findViewById(R.id.Dphonenumber);
        sendotp = (Button)findViewById(R.id.Sendotp);
        cpp=(CountryCodePicker)findViewById(R.id.countrycode);
        signinemail=(Button)findViewById(R.id.DbtnEmail);
        signup = (TextView)findViewById(R.id.Signupif);

        Fauth = FirebaseAuth.getInstance();//Creates a new KeyGenerator instance that provides the specified key algorithm from the firebase

        sendotp.setOnClickListener(new View.OnClickListener() {//setOnClickListener helps us to link a listener with certain attributes.
            @Override
            public void onClick(View v) {//on clicking show

                number=num.getText().toString().trim();
                String Phonenum = cpp.getSelectedCountryCodeWithPlus()+number;
                Intent b = new Intent(Admin_Loginphone.this,Admin_sendotp.class);

                b.putExtra("Phonenum",Phonenum);//placeholder text
                startActivity(b);
                finish();

            }
        });
     //OnClickListener has just one method which is OnClick (View v). Therefore, whatever action that needs to be performed on clicking the button must be coded within this method
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Loginphone.this,Admin_Registration.class));
                finish();
            }
        });
        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Loginphone.this,Admin_Login.class));
                finish();
            }
        });


    }
}