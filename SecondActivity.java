package hhp.fuse;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Locale;


public class SecondActivity extends ActionBarActivity {
    private String currentLang = "en";
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(this, MainActivityMenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("objectName","Option");
        intent.putExtra("currentLanguage", currentLang);

        setResult(RESULT_OK, intent);

        startActivity(intent);
        this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);
        Intent intent = getIntent();

        currentLang = intent.getStringExtra("currentLanguage");
        addLangToSpinner();

        //android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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
        else if (id== android.R.id.home){
            // app icon in action bar clicked; go home
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeLocale(String lang) {
        if (lang!=null){
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
            restartActivity();
        }
    }

    private void restartActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("currentLanguage", currentLang);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
    }

    private void addLangToSpinner() {
        final HashMap<String,String> match= new HashMap<>();
        Resources res = getResources();
        String[] lang_name = res.getStringArray(R.array.Languages);
        match.put(lang_name[0],"en");
        match.put(lang_name[1], "vn");
        final Spinner spin=(Spinner) findViewById(R.id.spinner);

        if (currentLang.equals("vn"))
            spin.setSelection(1,false);
        else
            spin.setSelection(0,false);

        spin.setPromptId(R.string.language_option);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentLang = match.get(spin.getSelectedItem());
                changeLocale(currentLang);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                changeLocale("en");
            }
        });
    }
}
