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

public class Chefloginphone extends AppCompatActivity {
    //Means you have a incomplete class and you are completing it through extending it 

    EditText num;
    Button sendotp,signinemail;
    TextView signup;
    CountryCodePicker cpp;
    FirebaseAuth Fauth;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceCity) {//is a reference to a Bundle object that is passed into the onCreate method of every Android Activity.
        super.onCreate(savedInstanceCity);
        setContentView(R.layout.activity_chefloginphone);//display the Layout created thorugh XML
//findViewById function is used to access or update properties of Views (Direct and Indirect Classes of android.view.View).
        num = (EditText)findViewById(R.id.number);
        sendotp = (Button)findViewById(R.id.otp);
        cpp=(CountryCodePicker)findViewById(R.id.CountryCode);
        signinemail=(Button)findViewById(R.id.btnEmail);
        signup = (TextView)findViewById(R.id.acsignup);

        Fauth = FirebaseAuth.getInstance();//It is used for singleton class creation. That means only one instance of that class will be created and others will get reference of that class. 

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number=num.getText().toString().trim();
                String Phonenumber = cpp.getSelectedCountryCodeWithPlus()+number;//gets phone no
                Intent b = new Intent(Chefloginphone.this,Chefsendotp.class);

                b.putExtra("Phonenumber",Phonenumber);
                startActivity(b);
                finish();

            }
        });
        //setOnClickListener method which helps us to link a listener with certain attributes.
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Chefloginphone.this,ChefRegistration.class));
                finish();
            }
        });
        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Chefloginphone.this,Cheflogin.class));
                finish();
            }
        });

    }
}