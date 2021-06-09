package com.example.hamzafood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class Admin_sendotp extends AppCompatActivity {//create a new class with the existing class’s functionality

    String verificationId;
    FirebaseAuth FAuth;
    Button verify , Resend ;
    TextView txt;
    EditText entercode;
    String phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//recreate the activity and load all data from savedInstanceState
        super.onCreate(savedInstanceState);//Bundle class is used to stored the data of activity whenever above condition occur in app
        setContentView(R.layout.activity_admin_sent_sendotp);//R means Resource. layout means design . main is the xml you have created under res->layout->main.xml. Whenever you want to change the current look 

        phoneno = getIntent().getStringExtra("Phonenum").trim();// An Intent is a messaging object that can be used to request an action from one app component to another app component.
//findViewById is a method that finds the view from the layout resource file that are attached with current Activity. 
        entercode = (EditText) findViewById(R.id.code1);
        txt = (TextView) findViewById(R.id.text1);
        Resend = (Button)findViewById(R.id.Resendotp1);
        verify = (Button) findViewById(R.id.Verify1);
        FAuth = FirebaseAuth.getInstance();
//.setVisibility This view is invisible, but it still takes up space for layout purposes.
        Resend.setVisibility(View.INVISIBLE);
        txt.setVisibility(View.INVISIBLE);

        sendverificationcode(phoneno);//verifys number

        verify.setOnClickListener(new View.OnClickListener() {//setOnClickListener() is a method that needs a object of type onClickListener. 
            @Override
            public void onClick(View v) {//onclicking do /  get

                String code = entercode.getText().toString().trim();
                Resend.setVisibility(View.INVISIBLE);
           //catching execption
                if (code.isEmpty() && code.length()<6){
                    entercode.setError("Enter code");
                    entercode.requestFocus();//it will automatically select defined Requestfocus editText and open keypad so application user can directly insert data into editText box
                    return;
                }
                verifyCode(code);verify code
            }
        });

        new CountDownTimer(60000,1000){

            @Override
            public void onTick(long millisUntilFinished) {//It updates time info and checks whether the application is in a preparation mode.
//.setVisibility This view is invisible, but it still takes up space for layout purposes.
                txt.setVisibility(View.VISIBLE);
                txt.setText("Resend Code Within "+millisUntilFinished/1000+" Seconds");

            }

            /**
             * Callback fired when the time is up.
             */
            @Override
            public void onFinish() {
                Resend.setVisibility(View.VISIBLE);
                txt.setVisibility(View.INVISIBLE);

            }
        }.start();

        Resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//on click view phone no

                Resend.setVisibility(View.INVISIBLE);//.setVisibility This view is invisible, but it still takes up space for layout purposes.
                Resendotp(phoneno);

                new CountDownTimer(60000,1000){

                    @Override
                    public void onTick(long millisUntilFinished) {

                        txt.setVisibility(View.VISIBLE);
                        txt.setText("Resend Code Within "+millisUntilFinished/1000+" Seconds");

                    }

                    /**
                     * Callback fired when the time is up.
                     */
                    @Override
                    public void onFinish() {
                        Resend.setVisibility(View.VISIBLE);
                        txt.setVisibility(View.INVISIBLE);

                    }
                }.start();
            }
        });


    }

    private void Resendotp(String phonenum) {//Resend verification no action

        sendverificationcode(phonenum);
    }


    private void sendverificationcode(String number) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(//gets another code from fire base
//time taken
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mcallBack
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallBack=new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
        //if verification is successful
            String code = phoneAuthCredential.getSmsCode();
            if(code != null){
                entercode.setText(code);  // Auto Verification
                verifyCode(code);
            }
        }
       //if verification fails
        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(Admin_sendotp.this , e.getMessage(),Toast.LENGTH_LONG).show();

        }

        @Override
        public void onCodeSent(String s , PhoneAuthProvider.ForceResendingToken forceResendingToken){
            super.onCodeSent(s,forceResendingToken);

            verificationId = s;

        }
    };

    private void verifyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId , code);
        signInWithPhone(credential);
    }

    private void signInWithPhone(PhoneAuthCredential credential) {

        FAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            startActivity(new Intent(Admin_sendotp.this,AdminFoodPanel_BottomNavigation.class));
                            finish();

                        }else{
                            ReusableCodeForAll.ShowAlert(Admin_sendotp.this,"Error",task.getException().getMessage());
                        }

                    }
                });

    }
}