package com.example.splash_screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
public class BookAppointment extends AppCompatActivity {
    private Button btn;
    int filtercount=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        Chip filterChip = findViewById(R.id.chip1);
        filterChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              filtercount++;
              Intent intent = new Intent(BookAppointment.this, FirstItem.class);
              startActivity(intent);
              checkfiltercount();
            }
        });
        Chip filterChip2=findViewById(R.id.city1);
        filterChip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtercount++;
                Intent intent = new Intent(BookAppointment.this, ThirdItem.class);
                startActivity(intent);
                checkfiltercount();
            }
        });

        btn = (Button) findViewById(R.id.getprofilebtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenProfileActivity();
            }
        });
    }
    public void OpenProfileActivity()
    {
        Intent activity4Intent = new Intent(getApplicationContext(), Profile.class);
        startActivity(activity4Intent);
    }
    private void checkfiltercount()
    {
        if(filtercount==2)
        {
            Intent intent = new Intent(this, SecItem.class);
            startActivity(intent);
        }
    }
}

/**public class BookAppointment extends AppCompatActivity implements RecyclerViewInterface {
    ArrayList<item> items = new ArrayList<>();
    int[] itemImgs = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,
            R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_recycler);
        RecyclerView recyclerView= findViewById(R.id.recyclerview);

        setupitemmodels();
        Adapter adapter= new Adapter(this, items, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void setupitemmodels()
    {
        String[] names = getResources().getStringArray(R.array.Names);
        String[] emails = getResources().getStringArray(R.array.emails);
        for(int i=0;i<names.length;i++)
        {
            items.add(new item(names[i],emails[i], itemImgs[i]));
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent4 = new Intent(getApplicationContext(), Profiles.class);
        startActivity(intent4);
    }
}**/
