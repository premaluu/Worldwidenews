package com.example.amitvikram.worldwidenews;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private TextInputLayout txtName;
    private TextInputLayout txtEmail;
    private TextInputLayout txtPassword;
    private TextInputLayout txtconfirmPassword;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtName = (TextInputLayout) findViewById(R.id.txtName);
        txtEmail = (TextInputLayout) findViewById(R.id.txtEmail);
        txtPassword = (TextInputLayout) findViewById(R.id.txtPassword);
        txtconfirmPassword = (TextInputLayout) findViewById(R.id.txtConfirmPassword);


    }
    public void confirmInput(View v){
        if(!validateName() && !validateEmail() && !validatePassword() && !validateConfirmPassword()){
            return;
        }
        else {

            String input = "Name : " + txtName.getEditText().getText().toString();
            input += "\n\n\n";

            input += "Email : " + txtEmail.getEditText().getText().toString();
            input += "\n\n\n";


            input += "Password : " + txtPassword.getEditText().getText().toString();
            input += "\n\n\n";


            input += "Confirmpassword : " + txtconfirmPassword.getEditText().getText().toString();
            input += "\n\n\n";

            Toast.makeText(this, input, Toast.LENGTH_LONG).show();
        }
    }

    private Boolean validateEmail(){
        String emailInput = txtEmail.getEditText().getText().toString().trim();
        if(emailInput.isEmpty()){
            txtEmail.setError("Field can't be empty");
            return false;
        }
        else if(isValidEmail(emailInput.subSequence(0,emailInput.length())) == false){
            txtEmail.setError("Email is not valid");
            return false;
        }
        else{
            txtEmail.setError(null);
            return true;
        }
    }
    private Boolean validateName(){
        String nameInput = txtName.getEditText().getText().toString().trim();
        if(nameInput.isEmpty()){
            txtName.setError("Field can't be empty");
            return false;
        }
        else if(nameInput.length() > 15){
            txtName.setError("Name too long");
            return false;
        }
        else{
            txtName.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword(){
        String passwordInput = txtPassword.getEditText().getText().toString().trim();
        if(passwordInput.isEmpty()){
            txtPassword.setError("Field can't be empty");
            return false;
        }
        else if(passwordInput.length() < 8){
            txtPassword.setError("Your password is'nt valid use more than 8 character");
            return false;
        }
        else{
            txtPassword.setError(null);
            return true;
        }
    }
    private Boolean validateConfirmPassword(){
        String confirmPasswordInput = txtconfirmPassword.getEditText().getText().toString().trim();
        if(confirmPasswordInput.isEmpty()){
            txtconfirmPassword.setError("Field can't be empty");
            return false;
        }
        else if(!txtconfirmPassword.getEditText().getText().toString().trim().equals(txtPassword.getEditText().getText().toString().trim())){
            txtconfirmPassword.setError("Password is not match");
            return false;
        }
        else{
            txtconfirmPassword.setErrorEnabled(false);
            return true;
        }
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


}
