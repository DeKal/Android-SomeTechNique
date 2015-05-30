package hhp.fuse;

import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.view.View;


public class MainActivityMenu extends ActionBarActivity {

    private final MainActivityMenu THIS = this;
    private static final int REQ_CODE = 123;
    private String currentLang = "en";
    OrientationEventListener mOrientationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MyActivity", "onCreate");
        setViewByOrientation();
        setEventForControl();
        receiveDataFromIntent();
        changeOrientationManagement();
    }

    private void setViewByOrientation() {
        int orientationStates = getResources().getConfiguration().orientation;
        if (orientationStates==Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_activity_menu_horizontal);
        }
        else if (orientationStates==Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_main_activity_menu);
        }
        else return;
        setEventForControl();
    }

    private void changeOrientationManagement() {
        mOrientationListener = new OrientationEventListener(this,
                SensorManager.SENSOR_DELAY_NORMAL) {
            @Override
            public void onOrientationChanged(int orientation) {
                setViewByOrientation();
            }
        };
        mOrientationListener.enable();
    }

    private void receiveDataFromIntent() {
        Intent intent = getIntent();
        if (intent.getStringExtra("currentLanguage") != null)
            currentLang = intent.getStringExtra("currentLanguage");
    }

    private void setEventForControl() {
        // Click on Option button
        (findViewById(R.id.language_option_TextView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(THIS, SecondActivity.class);
                intent.putExtra("currentLanguage", currentLang);
                startActivityForResult(intent, REQ_CODE);
            }
        });
        ///Click on Start Button
        findViewById(R.id.play_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(THIS, LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onResume() {
        Log.i("MyActivity", "onResume");
        super.onResume();
    }

    public void onStart() {
        Log.i("MyActivity", "onStart");
        super.onStart();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_menu, menu);
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
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQ_CODE) {


        }
    }

}
