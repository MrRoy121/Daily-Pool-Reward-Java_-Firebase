package com.rafiq.poldailygiftawardlinks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;


import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class SpinnerActivity extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth fauth;
    TextView timex, timetx, pointx;
    ImageView sp;
    Button btn;
    //private AdView adView;
    int points = 0;
    private static final int [] s = {1000000, 30000, 500000,10000,250000,800000,40000,80000,700000,100000,400000,50000,20000};
    private static final int [] se = new int[s.length];
    private static final Random random = new Random();
    private int degree = 0;
    private boolean isSpining = false;
    private boolean spinable = false;
    private int day = 0;
    long l = 0;
    String DateandTime, userid, dt;
    public SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiner);
        getSupportActionBar().hide();

        pointx = findViewById(R.id.point);
        sp = findViewById(R.id.sp);
        btn = findViewById(R.id.btn);
        timetx = findViewById(R.id.timeT);
        timex = findViewById(R.id.time);
        db = FirebaseFirestore.getInstance();
        fauth = FirebaseAuth.getInstance();

        userid = fauth.getCurrentUser().getUid();
/*
Facebook..
        adView = new AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainer.addView(adView);
        adView.loadAd();
*/

        getdatetime f = new getdatetime(SpinnerActivity.this);

        f.getdatetime(new getdatetime.VolleyCallBack() {
            @Override
            public void onGetdatetime(String Datw) {
                String Dat = Datw.replace("T"," ");
                DateandTime = Dat;
                db.collection("spin").document(userid).collection("DateTime").document("DateTime").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            timex.setVisibility(View.VISIBLE);
                            timetx.setVisibility(View.VISIBLE);
                            try {
                                l = getTimeDiff(String.valueOf(documentSnapshot.getString("Data")), Dat);
                                if(l==0){
                                    spinable = true;
                                }
                                spinable = false;
                                timer(l);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else{
                            String data = "2021-04-01 23:37:02.3125982";
                            Map<String, Object> user = new HashMap<>();
                            user.put("Data", data);
                            Map<String, Object> noll = new HashMap<>();
                            noll.put("null", null);
                            DocumentReference t = db.collection("spin").document(userid);
                            t.set(noll);
                            t.collection("DateTime").document("DateTime").set(user);
                            finish();
                        }
                    }
                });
            }
        });
        db.collection("spin").document(userid).collection("Points").document("Points").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    pointx.setText(String.valueOf(documentSnapshot.get("Points")));
                    points = Integer.parseInt(String.valueOf(documentSnapshot.get("Points")));
                }
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getDegreeForSelector();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isSpining){
                    if(spinable){
                        if(!(DateandTime == null)){
                            spin(l);
                            isSpining = true;
                            day = day + 1;

                            String data = addHour(DateandTime,12);
                            Log.e("TAHS", data);
                            Map<String, Object> user = new HashMap<>();
                            user.put("Data", data);
                            Map<String, Object> noll = new HashMap<>();
                            noll.put("null", null);

                            DocumentReference t = db.collection("spin").document(userid);
                            t.set(noll);
                            t.collection("DateTime").document("DateTime").set(user);
                        }else
                            Toast.makeText(SpinnerActivity.this, "Please Wait To Load Data!!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(SpinnerActivity.this, "Please Wait "+String.valueOf(timex.getText())+ " Hours For Next Spin", Toast.LENGTH_LONG).show();

                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                StartAppAd.showAd(SpinnerActivity.this);
                            }
                        },1000);
                    }

                }
            }
        });
    }
/*
Facebook..

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
*/


    private long getTimeDiff(String time, String dat) throws Exception
    {
        Date currentTime =  ft.parse(dat);
        Log.e("TAgg", String.valueOf(currentTime));

        Date oldDate = ft.parse(time);
        Log.e("TAgg", String.valueOf(oldDate));
        long oldMillis = oldDate.getTime();
        long currentMillis = currentTime.getTime();
        long n = oldMillis - currentMillis;

        return n;
    }

    private void getDegreeForSelector(){
        int see = 360/s.length;
        for (int i = 0; i < s.length; i++){
            se[i] = (i+1) * see;
        }
    }


    private void spin(long ls){

        degree = random.nextInt(s.length-1);
        if(degree == 11){degree=10;}
        RotateAnimation r = new RotateAnimation(0,(360*s.length)+ se[degree], RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF,0.5f);

        r.setDuration(5000);
        r.setFillAfter(true);
        r.setInterpolator(new DecelerateInterpolator());
        r.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                Bundle bundle = new Bundle();
                bundle.putString("val",String.valueOf(s[s.length-(degree+1)]));
                Intent i = new Intent(getApplicationContext(), CongratsActivity.class);
                finish();
                i.putExtras(bundle);
                isSpining = false;
                spinable = false;
                timer(ls);

                points = points + s[s.length-(degree+1)];
                Map<String, Object> point = new HashMap<>();
                point.put("Points", points);
                Map<String, Object> noll1 = new HashMap<>();
                noll1.put("null", "null");

                DocumentReference t = db.collection("spin").document(userid);
                t.set(noll1);
                t.collection("Points").document("Points").set(point);

                timex.setVisibility(View.VISIBLE);
                timetx.setVisibility(View.VISIBLE);
                startActivity(i);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        sp.startAnimation(r);

    }
    public void timer(long l){

        new CountDownTimer( l, 1000) {
            public void onTick(long millisUntilFinished) {

                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                timex.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
            }
            // When the task is over it will print 00:00:00 there
            public void onFinish() {
                spinable = true;
                timetx.setVisibility(View.INVISIBLE);
                timex.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    public String addHour(String myTime,int number)
    {
        try
        {
            Date d = ft.parse(myTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.HOUR, number);
            return ft.format(cal.getTime());
        }
        catch(ParseException e)
        {
            System.out.println("Parsing Exception");
        }
        return null;

    }


    private Date getDatets(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);

        return cal.getTime();
    }
}