package com.example.ayush.imagedatabaseapplication;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

import static com.example.ayush.imagedatabaseapplication.R.id.image;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabase db = new myDatabase(this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        //converts the bitmap into a byte array
        SQLiteDatabase database = db.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from MyTable",null);
        db.addEntry(byteArray);
        //adds the bitmap to the database


        byte[] imageArray = new byte[0];
        if (cursor.getCount() != 0 && cursor != null) {
            if (cursor.moveToFirst()) while (cursor.moveToNext()) {
                //gets image and saves it in a new variable
                imageArray = cursor.getBlob(1);
            }
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageArray);
        Bitmap returnedBitmap = BitmapFactory.decodeStream(inputStream);
        //converts the byte array into a new bitmap which is then attached to the imageview
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(returnedBitmap);
    }
}
