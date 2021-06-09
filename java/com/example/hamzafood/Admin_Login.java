package com.example.hamzafood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Admin_Login extends AppCompatActivity {

    TextInputLayout email,pass;//The primary use of a TextInputLayout is to act as a wrapper for EditText (or its descendant) and enable floating hint animations.
    Button Signin,Signinphone;//A clicking button
    TextView Forgotpassword , signup;//TextView displays text to the user and optionally allows them to edit it programmatically.
    FirebaseAuth Fauth;//Facebook authenticate
    String emailid,pwd;//gets Email

    @Override
    protected void onCreate(Bundle savedInstanceState) {//When an Activity first call or launched then onCreate (Bundle savedInstanceState) method is responsible to create the activity.
        super.onCreate(savedInstanceState);// Bundle class is used to stored the data of activity whenever above condition occur in app
        setContentView(R.layout.activity_admin__login);//At the run time device will pick up their layout based on the id given in setcontentview () method

        try{//try catch statement
//findViewById is a method that finds the view from the layout resource file that are attached with current Activity. 
            email = (TextInputLayout)findViewById(R.id.Demail);
            pass = (TextInputLayout)findViewById(R.id.Dpassword);
            Signin = (Button)findViewById(R.id.Loginbtn);
            signup = (TextView) findViewById(R.id.donot);
            Forgotpassword = (TextView)findViewById(R.id.Dforgotpass);
            Signinphone = (Button)findViewById(R.id.Dbtnphone);

            Fauth = FirebaseAuth.getInstance();//Creates a new KeyGenerator instance that provides the specified key algorithm from firebase

            Signin.setOnClickListener(new View.OnClickListener() {//OnClickListener has just one method which is OnClick (View v). Therefore, whatever action that needs to be performed on clicking the button must be coded within this method
                @Override
                public void onClick(View v) {//do something when button is clicked

                    emailid = email.getEditText().getText().toString().trim();
                    pwd = pass.getEditText().getText().toString().trim();

                    if(isValid()){//if email is valid

                        final ProgressDialog mDialog = new ProgressDialog(Admin_Login.this);//provides methods to work on progress bar
                        mDialog.setCanceledOnTouchOutside(false);// Sets whether this dialog is canceled when touched outside the window's bounds. 
                        mDialog.setCancelable(false);//means you cannot close the alert dialog after it's getting already opened For more informations 
                        mDialog.setMessage("Sign In Please Wait.......");//message in pop up dialog box
                        mDialog.show();//show message

                        Fauth.signInWithEmailAndPassword(emailid,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {//signup with email and password action
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {//action on complete

                                if(task.isSuccessful()){//if signup is successful
                                    mDialog.dismiss();//dismiss dialog box

                                    if(Fauth.getCurrentUser().isEmailVerified()){//gets current user verified by email
                                        mDialog.dismiss();
                                        Toast.makeText(Admin_Login.this, "Congratulation! You Have Successfully Login", Toast.LENGTH_SHORT).show();//message displayed after succefully login in
                                        Intent Z = new Intent(Admin_Login.this,AdminFoodPanel_BottomNavigation.class);//An intent is to perform an action on the screen.
                                        startActivity(Z);
                                        finish();

                                    }else{
                                        ReusableCodeForAll.ShowAlert(Admin_Login.this,"Verification Failed","You Have Not Verified Your Email");//if Email verifictaion fails

                                    }
                                }else{
                                    mDialog.dismiss();
                                    ReusableCodeForAll.ShowAlert(Admin_Login.this,"Error",task.getException().getMessage());//else if no email and password is provided
                                }
                            }
                        });
                    }
                }
            });
            //OnClickListener has just one method which is OnClick (View v). Therefore, whatever action that needs to be performed on clicking the button must be coded within this method
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Admin_Login.this,Admin_Registration.class));
                    finish();
                }
            });
            Forgotpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Admin_Login.this,AdminForgotPassword.class));
                    finish();
                }
            });
            Signinphone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Admin_Login.this,Admin_Loginphone.class));
                    finish();
                }
            });
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }//catch any exception raised

    }
    String emailpattern  = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";//email format

    public boolean isValid(){

        email.setErrorEnabled(false);//we are calling setErrorEnabled(false) during the TextInputLayout construction forcing the creation of an empty errorView which is hidden
        email.setError("");
        pass.setErrorEnabled(false);
        pass.setError("");

        boolean isvalid=false,isvalidemail=false,isvalidpassword=false;//yes/No condition
        if(TextUtils.isEmpty(emailid)){
            email.setErrorEnabled(true);
            email.setError("Email is required");//if no email is provided show this message
        }else{
            if(emailid.matches(emailpattern)){
                isvalidemail=true;
            }else{
                email.setErrorEnabled(true);
                email.setError("Invalid Email Address");//if Email is used before show this email
            }
        }
        if(TextUtils.isEmpty(pwd)){

            pass.setErrorEnabled(true);
            pass.setError("Password is Required");//if password field is empty show this message
        }else{
            isvalidpassword=true;
        }
        isvalid=(isvalidemail && isvalidpassword)?true:false;
        return isvalid;
    }
}