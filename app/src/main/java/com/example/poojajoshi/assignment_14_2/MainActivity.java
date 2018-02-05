package com.example.poojajoshi.assignment_14_2;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Button;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the handle of imageview
        ImageView imgView = findViewById(R.id.imageView);
        imgView.setImageResource(R.drawable.index);

        // get the handle of button and set the onclick listener
        Button button = findViewById(R.id.save_button);

        final ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the image drawable and save to external storage
                Drawable drawable = getResources().getDrawable(R.drawable.index, null);

                Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                bitmap.compress(Bitmap.CompressFormat.PNG, 60, bytearrayoutputstream);

                File file = new File( Environment.getExternalStorageDirectory() + "/SampleImage.png");

                try {
                    file.createNewFile();
                    FileOutputStream fileoutputstream = new FileOutputStream(file);
                    fileoutputstream.write(bytearrayoutputstream.toByteArray());
                    fileoutputstream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
