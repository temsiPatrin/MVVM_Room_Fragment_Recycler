package com.example.android.roomwordssimple.View;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.roomwordssimple.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity implements SecondFragment.ListenerSecondFragment {
    FirstFragment firstFragment;
    SecondFragment secondFragment;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstFragment = new FirstFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame, firstFragment)
                .commit();


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secondFragment = new SecondFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, secondFragment)
                        .commit();

                fab.setVisibility(View.GONE);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.clear_data) {
            Toast.makeText(this, "Clearing the data...", Toast.LENGTH_SHORT).show();
            firstFragment.delete();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public void saveNewWord(String text) {
        firstFragment.addNewWord(text);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, firstFragment)
                .commit();
        fab.setVisibility(View.VISIBLE);
    }
}
