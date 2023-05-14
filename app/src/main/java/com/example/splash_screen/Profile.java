package com.example.splash_screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Profile extends AppCompatActivity implements RecyclerViewInterface {

    ArrayList<item> items = new ArrayList<>();
    int[] itemImgs = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,
            R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);
@SuppressLint({"MissingInflatedId", "LocalSuppress"})
RecyclerView recyclerView=findViewById(R.id.recyclerview);
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
        Intent intent;
        switch (position)
        {
            case 0:
                intent = new Intent(this, FirstItem.class);
                break;
            case 1: intent=new Intent(this,SecItem.class );
                break;
            case 2: intent=new Intent(this, ThirdItem.class);
                break;
            default:return;
        }
        startActivity(intent);
    }
}
