package hhp.fuse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

// change in the manifest file to keep the Orientation of the activity
public class MainGame extends ActionBarActivity {
    private GridLayout game_GridLayout;
    private static final int numRow = 8;
    private static final int numColumn = 5;
    private ArrayList<ImageView> listImages = new ArrayList<>();
    private ImageView previousRightImage;
    private View.OnClickListener titleClick= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Context context = getApplicationContext();
            CharSequence text1 = "You Win!";
            CharSequence text2 = "You Lose!";
            int duration = Toast.LENGTH_SHORT;

            if (previousRightImage.getId() == v.getId())
                Toast.makeText(context, text1, duration).show();
            else
                Toast.makeText(context, text2, duration).show();
        }
    };

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(this, MainActivityMenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        setResult(RESULT_OK, intent);
        startActivity(intent);
        this.finish();
    }

    private final MainGame THIS = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        loadResources();
    }

    private void loadResources() {
        game_GridLayout = (GridLayout) findViewById(R.id.mainGame_GridLayOut);
        game_GridLayout.setColumnCount(numColumn);
        game_GridLayout.setRowCount(numRow);
        int i = 0;
        while (i < numColumn*numRow) {
            ImageView newImage = new ImageView(THIS);
            newImage.setImageResource(R.drawable.profile);
            newImage.setId(View.generateViewId());
            newImage.setOnClickListener(titleClick);
            newImage.setAdjustViewBounds(true);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(125,125);
            newImage.setLayoutParams(layoutParams);
            game_GridLayout.addView(newImage);
            listImages.add(newImage);
            ++i;
        }
        previousRightImage = listImages.get(randomNumber(numColumn * numRow-1));
        previousRightImage.setImageResource(R.drawable.back);

    }
    private int randomNumber(int range) {
        Random rand = new Random();
        return rand.nextInt(range);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_game, menu);
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
