package com.example.admin.sgoogle;

/**
 * Created by admin on 2/11/2017.
 */


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class Col_line extends Activity implements View.OnClickListener {

    private DrawerView myDrawView;
    private EraseView erase_view;
    private RelativeLayout dashBoard;
    private final static String TAG = "Main";
    ImageView iv;
    Bitmap thumbnail;
    static int i = 0;
    String p, colorpath;
    AttributeSet ar;
    Bitmap bitmap;
    Paint bitmapPaint;
    Button stroke, fill, eraser;
    Paint paint;
    Path path;
    Canvas canvas;
    Uri uri;
    private ArrayList<Path> paths = new ArrayList<Path>();
    private ArrayList<Path> undonePaths = new ArrayList<Path>();
    //paths.add()

    private boolean erase = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myDrawView = new DrawerView(this, ar);
        setContentView(R.layout.col_line);
        findViewById(R.id.draw);

        erase_view = new EraseView(this);
        findViewById(R.id.draw);

        iv = (ImageView) findViewById(R.id.iv);
        // myDrawView.addView(myDrawView);
        View btnSave = (Button) findViewById(R.id.save);
        View btnGreen = (Button) findViewById(R.id.green);
        View btnBlue = (Button) findViewById(R.id.blue);
        View btnOrange = (Button) findViewById(R.id.orange);
        View btnRed = (Button) findViewById(R.id.red);
        View btnBlack = (Button) findViewById(R.id.black);
        View btnyellow = (Button) findViewById(R.id.yellow);
        View btnmaroon = (Button) findViewById(R.id.maroon);
        View btnpurple = (Button) findViewById(R.id.purple);
        View btnbrown = (Button) findViewById(R.id.brown);
        View btnolive = (Button) findViewById(R.id.olive);
        View btnlime = (Button) findViewById(R.id.lime);
        View btnwhite = (Button) findViewById(R.id.white);
        View btnsilver = (Button) findViewById(R.id.silver);
        View btnpink = (Button) findViewById(R.id.pink);
        View stroke = (Button) findViewById(R.id.stroke);
        View fill = (Button) findViewById(R.id.fill);
        View back = (Button) findViewById(R.id.back);
        View eraser = (Button) findViewById(R.id.eraser);

        Intent intent = getIntent();
        colorpath = intent.getStringExtra("imagepath");
        btnSave.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
        btnBlack.setOnClickListener(this);
        btnRed.setOnClickListener(this);
        btnBlue.setOnClickListener(this);
        btnOrange.setOnClickListener(this);
        btnbrown.setOnClickListener(this);
        btnolive.setOnClickListener(this);
        btnlime.setOnClickListener(this);
        btnwhite.setOnClickListener(this);
        btnsilver.setOnClickListener(this);
        stroke.setOnClickListener(this);
        fill.setOnClickListener(this);
        btnyellow.setOnClickListener(this);
        btnmaroon.setOnClickListener(this);
        btnpurple.setOnClickListener(this);
        btnpink.setOnClickListener(this);
        eraser.setOnClickListener(this);

        //  btnClear.setOnClickListener(this);
        dashBoard = (RelativeLayout) findViewById(R.id.draw);
        // image = (ImageView) findViewById(R.id.myImage);
        dashBoard.addView(myDrawView);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.red:
                myDrawView.setPathColor(Color.RED);
                break;

            case R.id.blue:
                myDrawView.setPathColor(Color.BLUE);
                break;

            case R.id.green:
                myDrawView.setPathColor(Color.rgb(0,128,0));
                break;

            case R.id.black:
                myDrawView.setPathColor(Color.BLACK);
                break;

            case R.id.orange:
                myDrawView.setPathColor(Color.rgb(255, 87, 51));
                break;

            case R.id.yellow:
                myDrawView.setPathColor(Color.rgb(255, 195, 0));
                break;

            case R.id.maroon:
                myDrawView.setPathColor(Color.rgb(128, 0, 0));
                break;

            case R.id.purple:
                myDrawView.setPathColor(Color.rgb(128, 0, 128));
                break;


            case R.id.brown:
                myDrawView.setPathColor(Color.rgb(165, 42, 42));
                break;

            case R.id.lime:
                myDrawView.setPathColor(Color.rgb(0, 255, 0));
                break;

            case R.id.white:
                myDrawView.setPathColor(Color.rgb(255, 255, 255));
                break;

            case R.id.silver:
                myDrawView.setPathColor(Color.rgb(192, 192, 192));
                break;

            case R.id.pink:
                myDrawView.setPathColor(Color.rgb(255, 0, 255));
                break;

            case R.id.olive:
                myDrawView.setPathColor(Color.rgb(128, 128, 0));
                break;

            case R.id.save:
                savingFile();
                final CharSequence[] options = { "Continue Editing", "Select a photo","save to cloud","quit" };

                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Col_line.this);
                builder.setTitle("GO TO..");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Continue Editing")) {
                            System.out.println("before cont. ediit");
                            dialog.dismiss();
                            // finish();
                            // Intent intent = new   Intent(Compare.this,Edit_p.class);
                            //startActivity(intent);
                            //System.out.println("after cont. ediit");

                        } else if (options[item].equals("Select photo")) {
                            Intent intent = new   Intent(Col_line.this,Middle.class);
                            startActivity(intent);
                        } else if (options[item].equals("save to cloud")) {
                            Intent intent = new Intent(Col_line.this, MainActivity.class);
                            intent.setData(uri);
                            startActivity(intent);
                        } else if (options[item].equals("quit")) {
                           /* Intent intent = new Intent(getApplicationContext(), Splash_screen.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);*/
                            finish();
                            moveTaskToBack(true);
                            dialog.dismiss();
                            finish();
                        }
                    }
                });
                builder.show();
                break;

            case R.id.back:
                finish();
                Intent back = new Intent(Col_line.this, Edit_i.class);
                startActivity(back);
                break;

            case R.id.stroke:
                //savingFile();
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeJoin(Paint.Join.ROUND);
                paint.setStrokeCap(Paint.Cap.SQUARE);
                paint.setStrokeWidth(25);
                break;

            case R.id.fill:
                //savingFile();
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeJoin(Paint.Join.ROUND);
                paint.setStrokeCap(Paint.Cap.SQUARE);
                paint.setStrokeWidth(25);
                break;

            case R.id.eraser:
              // myDrawView.clear();
                   // erase_view.onClickUndo();
                //bitmap.eraseColor(Color.TRANSPARENT);
                //path.reset();
               // myDrawView.invalidate();
               // canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                BitmapFactory.Options decode_options = new BitmapFactory.Options();

                decode_options.inMutable = true;
                Bitmap bmp = BitmapFactory.decodeFile(colorpath, decode_options);
                bitmap = Bitmap.createScaledBitmap(bmp, 700, 1060, false);
                canvas = new Canvas(bitmap);
                final int width = (int) (1f * bitmap.getWidth() / bitmap.getHeight() * 500);
                final int height = 500;
                final Bitmap scaled = Bitmap.createScaledBitmap(bitmap, width, height, false);
                final int leftOffset = (bitmap.getWidth() - scaled.getWidth()) * 3;
                final int topOffset = 30;
                canvas.drawBitmap(scaled, leftOffset, topOffset, null);
                break;

            default:
                break;
        }
    }

    private void savingFile() {

        Bitmap well = myDrawView.getBitmap();
        Bitmap save = Bitmap.createBitmap(700, 800,
                Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        Canvas now = new Canvas(save);
        now.drawRect(new Rect(0, 0, 690, 820), paint);
        now.drawBitmap(well,
                new Rect(0, 0, 700, 1060),
                new Rect(0, 0, 690, 820), null);

        String ImagePath = MediaStore.Images.Media.insertImage(
                getContentResolver(), save
                ,
                "demo_image" + i,
                "demo_image" + i

        );
        i = i + 1;
        uri = Uri.parse(ImagePath);
        String selectedimagepath = Imagefilepath.getPath(getApplicationContext(), uri);

        final CharSequence[] options = { "Continue Editing", "Select a photo","save to cloud","quit" };

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Col_line.this);
        builder.setTitle("GO TO..");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Continue Editing")) {
                    System.out.println("before cont. ediit");
                    dialog.dismiss();
                    // finish();
                    // Intent intent = new   Intent(Compare.this,Edit_p.class);
                    //startActivity(intent);
                    //System.out.println("after cont. ediit");
                } else if (options[item].equals("Select photo")) {
                    Intent intent = new   Intent(Col_line.this,Middle.class);
                    startActivity(intent);
                } else if (options[item].equals("save to cloud")) {
                    Intent intent = new Intent(Col_line.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);
                    startActivity(intent);
                } else if (options[item].equals("quit")) {
                           /* Intent intent = new Intent(getApplicationContext(), Splash_screen.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);*/
                    finish();
                    moveTaskToBack(true);
                    dialog.dismiss();
                    finish();
                }
            }
        });
        builder.show();
    }

    private class DrawerView extends View {
       // private Canvas canvas,pcanvas;
        //private Path path;
        //private Paint paint;
        String p;

        public DrawerView(Context context, AttributeSet attrs) {
            super(context, attrs);

            path = new Path();
            bitmapPaint = new Paint(Paint.DITHER_FLAG);

            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setColor(Color.TRANSPARENT);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setStrokeWidth(25);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            //String b=p;
            // bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            // bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tq).copy(Bitmap.Config.ARGB_8888, true);
            // decode_options.inMutable = true;
            BitmapFactory.Options decode_options = new BitmapFactory.Options();

            decode_options.inMutable = true;
            Bitmap bmp = BitmapFactory.decodeFile(colorpath, decode_options);
            bitmap = Bitmap.createScaledBitmap(bmp, 700, 1060, false);
            canvas = new Canvas(bitmap);
            final int width = (int) (1f * bitmap.getWidth() / bitmap.getHeight() * 500);
            final int height = 500;
            final Bitmap scaled = Bitmap.createScaledBitmap(bitmap, width, height, false);
            final int leftOffset = (bitmap.getWidth() - scaled.getWidth()) * 3;
            final int topOffset = 30;
            canvas.drawBitmap(scaled, leftOffset, topOffset, null);
        }

        @Override
        protected void onDraw(Canvas canvas){
            canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
           /* for (Path p : paths) {
                canvas.drawPath(p, paint);
            }*/
            canvas.drawPath(path, paint);
            canvas.restore();
        }

        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 4;

        private void touchStart(float x, float y) {
            path.reset();
            path.moveTo(x, y);
            mX = x;
            mY = y;
        }

        private void touchMove(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
                mX = x;
                mY = y;
            }
        }

        private void touchUp() {
            path.lineTo(mX, mY);
            // commit the path to our offscreen
            canvas.drawPath(path, paint);
            // kill this so we don't double draw
            path.reset();
          //  paths.add(path);
            //path = new Path();
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchStart(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchMove(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touchUp();
                    invalidate();
                    break;
            }
            return true;
        }

        public Bitmap getBitmap() {
            this.setDrawingCacheEnabled(true);
            this.buildDrawingCache();
            Bitmap bmp = Bitmap.createBitmap(this.getDrawingCache());
            this.setDrawingCacheEnabled(false);
            return bmp;
        }

        //Clear screen
        public void clear() {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        }
        /**
         * change path color here
         */
        public void setPathColor(int color) {
            paint.setColor(color);
        }
    }
}