package com.example.splash_screen;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
        EditText nEmail,npassword;
        Button nloginBtn;
        TextView nCreatebtn,forgetTextLink;
        ProgressBar progressBar;

        FirebaseAuth fauth;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login);
            nEmail= findViewById(R.id.email);
            npassword= findViewById(R.id.password);
            progressBar= findViewById(R.id.progressbar);
            nloginBtn = findViewById(R.id.loginBtn);
            nCreatebtn= findViewById(R.id.Createtext);
            forgetTextLink=findViewById(R.id.forgotpassword);
            fauth=FirebaseAuth.getInstance();

            nCreatebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),Register.class));
                }
            });

            nloginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = nEmail.getText().toString().trim();
                    String password= npassword.getText().toString().trim();
                    if(TextUtils.isEmpty(email))
                    {
                        nEmail.setError("Email is Required");
                        return;
                    }
                    if(TextUtils.isEmpty(password))
                    {
                        nEmail.setError("Password is Required");
                        return;
                    }
                    if(password.length()<6)
                    {
                        npassword.setError("Password mus be >= 6 character");
                        return;
                    }

                    progressBar.setVisibility(View.VISIBLE);
                    fauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                FirebaseUser fuser= fauth.getCurrentUser();
                                assert fuser!=null;
                                if(fuser.isEmailVerified())
                                {
                                    progressBar.setVisibility(View.GONE);
                                    startActivityLogin();
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(Login.this, "Please verify your email",Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }

                            else {
                                Toast.makeText(getApplicationContext(), "Error!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                    });
                }

            });

            forgetTextLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EditText resetMail = new EditText(v.getContext());
                    final AlertDialog.Builder passwordResertDialog = new AlertDialog.Builder(v.getContext());
                    passwordResertDialog.setTitle("Reset Password?");
                    passwordResertDialog.setMessage("Enter Your Email To Receive Reset Link");
                    passwordResertDialog.setView(resetMail);
                    passwordResertDialog.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String mail = resetMail.getText().toString();
                            fauth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    // Toast.makeText(Login.this, "Reset Link Sent To Your Email",Toast.LENGTH_SHORT).show();
                                    Toast.makeText(Login.this, "Reset Link Sent To Your Email", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Login.this, "Error!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    passwordResertDialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //close the dialog
                        }
                    });  passwordResertDialog.show();
                }
            });


            nCreatebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),Register.class));
                }
            });

        }
        private void startActivityLogin()
        {
            Intent intent= new Intent(Login.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

}
