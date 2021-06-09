package com.example.hamzafood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin_Registration extends AppCompatActivity {//When you extend an existing Java class (such as the AppCompatActivity class), you create a new class with the existing class’s functionality
//passing Array
    String[] England = {"London","Manchester","Liverpool"};
    String[] Wales = {"Cardif","Swansea","Newport"};

    TextInputLayout Fname,Lname,Email,Pass,cpass,mobileno,houseno,area,pincode;
    Spinner Statespin,Cityspin;
    Button signup, Emaill, Phone;
    CountryCodePicker Cpp;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String fname,lname,emailid,password,confpassword,mobile,house,Area,Pincode,statee,cityy;
    String role="Admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {//Basically Bundle class is used to stored the data of activity whenever above condition occur in app.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__registration);//Set the screen content to an explicit view identified by id's
//View is a class where all the widgets are defined. findViewById is the method that finds the View by the ID it is given.
        Fname = (TextInputLayout)findViewById(R.id.fname);
        Lname = (TextInputLayout)findViewById(R.id.lname);
        Email = (TextInputLayout)findViewById(R.id.Emailid);
        Pass = (TextInputLayout)findViewById(R.id.password);
        cpass = (TextInputLayout)findViewById(R.id.confirmpassword);
        mobileno = (TextInputLayout)findViewById(R.id.mobileno);
        houseno = (TextInputLayout)findViewById(R.id.Houseno);
        pincode = (TextInputLayout)findViewById(R.id.Pincodee);
        Statespin = (Spinner) findViewById(R.id.State);
        Cityspin = (Spinner) findViewById(R.id.City);
        area = (TextInputLayout)findViewById(R.id.Areaa);

        signup = (Button)findViewById(R.id.Signupp);
        Emaill = (Button)findViewById(R.id.emaillid);
        Phone = (Button)findViewById(R.id.Phonenumber);

        Cpp = (CountryCodePicker)findViewById(R.id.ctrycode);

        Statespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//creating spinner 
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//using spinner to filter custom arrays

                Object value = parent.getItemAtPosition(position);//Where parent is a parent view for a spinner and position is the selected position from the spinner view
                statee = value.toString().trim();// returning textual representation of an object
                if(statee.equals("England")){
                    ArrayList<String> list = new ArrayList<>();
                    for (String cities : England){
                        list.add(cities);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Admin_Registration.this,android.R.layout.simple_spinner_item,list);
                    Cityspin.setAdapter(arrayAdapter);//set adapter Set a list of items, which are supplied by the given IListAdapter, to be displayed in the dialog as the content, you will be notified of the selected item via the supplied listener
                }
                if(statee.equals("Wales")){
                    ArrayList<String> list = new ArrayList<>();
                    for (String cities : Wales){
                        list.add(cities);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Admin_Registration.this,android.R.layout.simple_spinner_item,list);
                    Cityspin.setAdapter(arrayAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {//onNothingSelected is a Callback method to be invoked when the selection disappears from this view.

            }
        });

        Cityspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//Creating Spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//using spinner to filter custom arrays
                Object value = parent.getItemAtPosition(position);//Where parent is a parent view for a spinner and position is the selected position from the spinner view
                cityy = value.toString().trim();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        databaseReference = firebaseDatabase.getInstance().getReference("Chef");//‘getReference’ call will take care of passing the value if you store it in a variable.
        FAuth = FirebaseAuth.getInstance();// Creates a new KeyGenerator instance that provides the specified key algorithm from the firebase

        signup.setOnClickListener(new View.OnClickListener() {//do signup on clicking the button
            @Override
            public void onClick(View v) {
                //EditText is a standard entry widget in android apps. It is an overlay over TextView that configures itself to be editable. EditText is a subclass of TextView with text editing operations.
                fname = Fname.getEditText().getText().toString().trim();
                lname = Lname.getEditText().getText().toString().trim();
                emailid = Email.getEditText().getText().toString().trim();
                mobile = mobileno.getEditText().getText().toString().trim();
                password = Pass.getEditText().getText().toString().trim();
                confpassword = cpass.getEditText().getText().toString().trim();
                Area = area.getEditText().getText().toString().trim();
                house = houseno.getEditText().getText().toString().trim();
                Pincode = pincode.getEditText().getText().toString().trim();

                if (isValid()){//proccessing input data to check if its valid
                    final ProgressDialog mDialog = new ProgressDialog(Admin_Registration.this);
                    mDialog.setCancelable(false);//setCancelable (false) means you cannot close the alert dialog after it's getting already opened For more informations
                    mDialog.setCanceledOnTouchOutside(false);//Sets whether this dialog is canceled when touched outside the window's bounds
                    mDialog.setMessage("Registration in progress please wait......");//Message to be displayed in dialog box if the condition is true
                    mDialog.show();

                    FAuth.createUserWithEmailAndPassword(emailid,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {//Creates user with email and password
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {//means the task will returnan AuthResultobject when it succeeds:

                            if (task.isSuccessful()){
                                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference("User").child(useridd);
                                final HashMap<String , String> hashMap = new HashMap<>();
                                hashMap.put("Role",role);
                                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                     //input fields
                                        HashMap<String , String> hashMap1 = new HashMap<>();
                                        hashMap1.put("Mobile No",mobile);
                                        hashMap1.put("First Name",fname);
                                        hashMap1.put("Last Name",lname);
                                        hashMap1.put("EmailId",emailid);
                                        hashMap1.put("City",cityy);
                                        hashMap1.put("Area",Area);
                                        hashMap1.put("Password",password);
                                        hashMap1.put("Pincode",Pincode);
                                        hashMap1.put("State",statee);
                                        hashMap1.put("Confirm Password",confpassword);
                                        hashMap1.put("House",house);

                                        firebaseDatabase.getInstance().getReference("DeliveryPerson")// Creates a new KeyGenerator instance that provides the specified key algorithm from firebase
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                mDialog.dismiss();
        // will returnan AuthResultobject when it succeeds: To be notified when the task succeeds, attach an OnSuccessListener: To be notified when the task fails, attach an OnFailureListener: To handle success and failure in the same listener, attach anOnCompleteListener:
                                                FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    private Object ReusableCodeForAll;

                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        if(task.isSuccessful()){
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(Admin_Registration.this);
                                                            builder.setMessage("You Have Registered! Make Sure To Verify Your Email");
                                                            builder.setCancelable(false);
                                                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {

                                                                    dialog.dismiss();

                                                                    String phonenumber = Cpp.getSelectedCountryCodeWithPlus() + mobile;
                                                                    Intent b = new Intent(Admin_Registration.this,Delivery_VerifyPhone.class);
                                                                    b.putExtra("phonenumber",phonenumber);
                                                                    startActivity(b);

                                                                }
                                                            });
                                                            AlertDialog Alert = builder.create();
                                                            Alert.show();
                                                        }else{
                                                            mDialog.dismiss();
                                                            ReusableCodeForAll.ShowAlert(Admin_Registration.this,"Error",task.getException().getMessage());
                                                        }
                                                    }
                                                });

                                            }
                                        });

                                    }
                                });
                            }else{
                                mDialog.dismiss();
                                ReusableCodeForAll.ShowAlert(Admin_Registration.this,"Error",task.getException().getMessage());

                            }
                        }
                    });
                }
            }
        });

        Emaill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Admin_Registration.this,Admin_Login.class));
                finish();
            }
        });
        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Registration.this,Admin_Loginphone.class));
                finish();
            }
        });
    }
    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    //setting string format to be accepted

    public boolean isValid(){
        //we are calling setErrorEnabled(true) during the TextInputLayout construction forcing the creation of an empty errorView which is hidden
        Email.setErrorEnabled(false);
        Email.setError("");
        Fname.setErrorEnabled(false);
        Fname.setError("");
        Lname.setErrorEnabled(false);
        Lname.setError("");
        Pass.setErrorEnabled(false);
        Pass.setError("");
        mobileno.setErrorEnabled(false);
        mobileno.setError("");
        cpass.setErrorEnabled(false);
        cpass.setError("");
        area.setErrorEnabled(false);
        area.setError("");
        houseno.setErrorEnabled(false);
        houseno.setError("");
        pincode.setErrorEnabled(false);
        pincode.setError("");

        boolean isValid=false,isValidhouseno=false,isValidlname=false,isValidname=false,isValidemail=false,isValidpassword=false,isValidconfpassword=false,isValidmobilenum=false,isValidarea=false,isValidpincode=false;
        if(TextUtils.isEmpty(fname)){//TextUtils.isEmpty () is better in Android SDK because of inner null check, so you don't need to check string for null before checking its emptiness yourself. Using TextUtils.isEmpty () return false even if it is empty while running tests. It needs static mocking.
            Fname.setErrorEnabled(true);
            Fname.setError("Enter First Name");//message to be displayed if set error is empty
        }else{
            isValidname = true;
        }
        if(TextUtils.isEmpty(lname)){
            Lname.setErrorEnabled(true);
            Lname.setError("Enter Last Name");
        }else{
            isValidlname = true;
        }
        if(TextUtils.isEmpty(emailid)){
            Email.setErrorEnabled(true);
            Email.setError("Email Is Required");
        }else{
            if(emailid.matches(emailpattern)){
                isValidemail = true;
            }else{
                Email.setErrorEnabled(true);
                Email.setError("Enter a Valid Email Id");
            }
        }
        if(TextUtils.isEmpty(password)){
            Pass.setErrorEnabled(true);
            Pass.setError("Enter Password");
        }else{
            if(password.length()<8){
                Pass.setErrorEnabled(true);
                Pass.setError("Password is Weak");
            }else{
                isValidpassword = true;
            }
        }
        if(TextUtils.isEmpty(confpassword)){
            cpass.setErrorEnabled(true);
            cpass.setError("Enter Password Again");
        }else{
            if(!password.equals(confpassword)){
                cpass.setErrorEnabled(true);
                cpass.setError("Password Dosen't Match");
            }else{
                isValidconfpassword = true;
            }
        }
        if(TextUtils.isEmpty(mobile)){
            mobileno.setErrorEnabled(true);
            mobileno.setError("Mobile Number Is Required");
        }else{
            if(mobile.length()<10){
                mobileno.setErrorEnabled(true);
                mobileno.setError("Invalid Mobile Number");
            }else{
                isValidmobilenum = true;
            }
        }
        if(TextUtils.isEmpty(Area)){
            area.setErrorEnabled(true);
            area.setError("Area Is Required");
        }else{
            isValidarea = true;
        }
        if(TextUtils.isEmpty(Pincode)){
            pincode.setErrorEnabled(true);
            pincode.setError("Please Enter Pincode");
        }else{
            isValidpincode = true;
        }
        if(TextUtils.isEmpty(house)){
            houseno.setErrorEnabled(true);
            houseno.setError("Fields Can't Be Empty");
        }else{
            isValidhouseno = true;
        }

        isValid = (isValidarea && isValidconfpassword && isValidpassword && isValidpincode && isValidemail && isValidmobilenum && isValidname && isValidhouseno && isValidlname) ? true : false;
        return isValid;


    }
}