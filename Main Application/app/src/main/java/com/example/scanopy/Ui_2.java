package com.example.scanopy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class Ui_2 extends AppCompatActivity {
    Button cam_btn;
    ImageView img_hi;
    Button gal_btn;
    Button qr_btn;
    Button scanny_btn;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_2);

        cam_btn = (Button) findViewById(R.id.cam_btn);
        img_hi = (ImageView) findViewById(R.id.img_hello);
        gal_btn = (Button) findViewById(R.id.gal_btn);
        qr_btn = (Button) findViewById(R.id.qr_btn);
        scanny_btn=(Button)findViewById(R.id.scanny_btn);

        scanny_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity4();

            }
        });

        qr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity3();
            }
        });

        cam_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);

            }
        });
        gal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

                        //permission not granted, request it
                        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for runtime permission
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        //permission already granted
                        pickImageFromGallery();
                    }
                } else {
                    //system os is less then marshmallow
                    pickImageFromGallery();
                }

            }
        });
    }

    private void pickImageFromGallery() {
        //intent to pick image
        Intent intent = new Intent((Intent.ACTION_PICK));
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted

                } else {
                    //permission was denied
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                    pickImageFromGallery();
                }
            }
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE)
            img_hi.setImageURI(data.getData());
        else {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img_hi.setImageBitmap(bitmap);
        }


    }


    public void openNewActivity3() {
        Intent intent = new Intent(this, ui_3.class);
        startActivity(intent);
    }
    public void openNewActivity4() {
        Intent intent = new Intent(this, ui_4.class);
        startActivity(intent);
}}









