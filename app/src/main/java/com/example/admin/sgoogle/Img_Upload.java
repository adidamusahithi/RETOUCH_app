package com.example.admin.sgoogle;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class Img_Upload extends AppCompatActivity implements View.OnClickListener{
    private Button btn_choose,btn_upload,btn_download;
    private ImageView imageView;
    private static final int PIC_IMAGE_REQUEST = 2;
    private Uri file_path;
    private StorageReference storageReference;
    StorageReference riversRef;
    String pic;
    File localFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img__upload);
        file_path = getIntent().getData();
        imageView = (ImageView)findViewById(R.id.imageView);
        btn_choose = (Button)findViewById(R.id.btn_choose);
        btn_upload = (Button)findViewById(R.id.btn_upload);
        btn_download = (Button)findViewById(R.id.btn_download);

        storageReference = FirebaseStorage.getInstance().getReference();

        btn_download.setOnClickListener(this);
        btn_choose.setOnClickListener(this);
        btn_upload.setOnClickListener(this);
    }

    private void shoeFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select an Image"),PIC_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PIC_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            file_path = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),file_path);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static int i = 1;
    private void uploadFile(){

        if(file_path != null) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();
            i = i+1;
            pic = "pic"+i;
            riversRef = storageReference.child("images/"+pic+".jpg");
            riversRef.putFile(file_path)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content


                            //Picasso.with(Img_upload.this).load(downloadUrl).into(imageView);
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"File Uploaded",Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage(((int) progress) + "% Uploded....");
                        }
                    })
            ;
        }else {
            //display a tost
            Toast.makeText(Img_Upload.this,"no filepath",Toast.LENGTH_SHORT).show();
        }
    }

    private void downloadFile() throws IOException {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("downloading...");
        progressDialog.show();
        localFile = File.createTempFile("images", "jpg");
        riversRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        // ...

                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        imageView.setImageBitmap(bitmap);
                        //String ImagePath =
                        MediaStore.Images.Media.insertImage(
                                getContentResolver(),
                                bitmap,
                                pic,
                                pic
                        );
                        progressDialog.dismiss();
                        Toast.makeText(Img_Upload.this,"downloaded",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Img_Upload.this,AccountActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                // ...
                Toast.makeText(Img_Upload.this,"not",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v == btn_choose){
            //open file choose
            shoeFileChooser();

        }else if(v == btn_upload){
            //upload file to firebase storage
            uploadFile();

        }else if (v == btn_download){

            try {
                downloadFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
