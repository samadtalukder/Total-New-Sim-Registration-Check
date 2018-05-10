package com.samadtalukder.totalsimregistrationcheck;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout linLayGP,linLayBL,linLayRobi,linLayAirtel,linLayTT;
    private String ussd;
    private String endcodeHash = Uri.encode("#");
    private static final int REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initID();
        //
        buttonAction();
    }

    private void buttonAction() {
        linLayGP.setOnClickListener(this);
        linLayBL.setOnClickListener(this);
        linLayRobi.setOnClickListener(this);
        linLayAirtel.setOnClickListener(this);
        linLayTT.setOnClickListener(this);
    }

    private void initID() {
        linLayGP = findViewById(R.id.linLayGP);
        linLayBL = findViewById(R.id.linLayBL);
        linLayRobi = findViewById(R.id.linLayRobi);
        linLayAirtel = findViewById(R.id.linLayAirtel);
        linLayTT = findViewById(R.id.linLayTT);
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        int checkPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (checkPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PHONE);
        }
        else {
            int id = v.getId();
            switch (id) {
                case R.id.linLayGP:
                    Uri uri = Uri.parse("smsto:4949");
                    Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
                    intent.putExtra("sms_body:","info");
                    startActivity(intent);
                    break;
                case R.id.linLayBL:
                    ussd = "*" + "1600" + "*" + "2" + endcodeHash;
                    startActivityForResult(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussd)), 1);
                    break;
                case R.id.linLayRobi:
                    ussd = "*" + "1600" + "*" + "3" + endcodeHash;
                    startActivityForResult(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussd)), 1);
                    break;
                case R.id.linLayAirtel:
                    ussd = "*" + "121" + "*" + "4444" + endcodeHash;
                    startActivityForResult(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussd)), 1);
                    break;
                case R.id.linLayTT:
                    Uri uriTT = Uri.parse("smsto:1600");
                    Intent intentTT = new Intent(Intent.ACTION_SENDTO,uriTT);
                    intentTT.putExtra("sms_body:","info");
                    startActivity(intentTT);
                    break;

            }
        }
    }
}
