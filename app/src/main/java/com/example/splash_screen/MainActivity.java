package com.example.splash_screen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {

 EditText nFullName,nEmail;
    ViewFlipper flipper,flipper2,flipper3;
    private Button btn, logoutbutton;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        nFullName = findViewById(R.id.fullname);
        nEmail = findViewById(R.id.email);
        logoutbutton = findViewById(R.id.logoutbtn);
        fAuth= FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();
        userId=fAuth.getCurrentUser().getUid();

        DocumentReference documentReference= fStore.collection("Users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    nFullName.setText(documentSnapshot.getString("fName"));
                    nEmail.setText(documentSnapshot.getString("email"));
                }
                else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });
        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();;
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

        int[] ImgArray ={R.drawable.k1,R.drawable.k2,R.drawable.k3,
                R.drawable.b1,R.drawable.b2,R.drawable.b3};
        int[] imgarray_sec={
                R.drawable.l1,R.drawable.l2,R.drawable.l3,
        };
        flipper=(ViewFlipper)findViewById(R.id.flipper);
        flipper2=(ViewFlipper)findViewById(R.id.flipper2);
        flipper3=(ViewFlipper)findViewById(R.id.flipper3);
        for(int i=0;i<3;i++)
        {
            showimg(ImgArray[i]);
        }
        for (int j : imgarray_sec) {
            showimg2(j);
        }
        for(int i=3;i<ImgArray.length;i++)
        {
            showimg3(ImgArray[i]);
        }
        btn = (Button) findViewById(R.id.buttonOne);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSecActivity();
            }
        });
    }
    public void showimg(int img)
    {
        ImageView imageView= new ImageView(this);
        imageView.setBackgroundResource(img);
        flipper.addView(imageView);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);
        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    public void showimg2(int img)
    {
        ImageView imageView= new ImageView(this);
        imageView.setBackgroundResource(img);
        flipper2.addView(imageView);
        flipper2.setFlipInterval(3000);
        flipper2.setAutoStart(true);
        flipper2.setInAnimation(this, android.R.anim.slide_in_left);
        flipper2.setOutAnimation(this, android.R.anim.slide_out_right);
    }
    public void showimg3(int img)
    {
        ImageView imageView= new ImageView(this);
        imageView.setBackgroundResource(img);
        flipper3.addView(imageView);
        flipper3.setFlipInterval(3000);
        flipper3.setAutoStart(true);
        flipper3.setInAnimation(this, android.R.anim.slide_in_left);
        flipper3.setOutAnimation(this, android.R.anim.slide_out_right);
    }
    public void OpenSecActivity()
    {
        Intent activity2Intent = new Intent(getApplicationContext(), Homescreen2.class);
        startActivity(activity2Intent);
    }
/*FirebaseAuth fauth= FirebaseAuth.getInstance();
    FirebaseUser currentuser= fauth.getCurrentUser();
    String uid=currentuser.getUid();
    String baseURl="https://example.com/profile/";
    String profilelink= baseURl+uid;*/
}