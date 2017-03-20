package test.listviewtest;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener, MyQuestionDialogFragment.DialogListener {

    String[] pokemon_names;
    TypedArray pokemon_pics;
    String[] pokemon_desc;
    List<String> pokemon_images = new ArrayList<String>();

    List<RowItem> rowItems;
    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        rowItems = new ArrayList<RowItem>();

        pokemon_names = getResources().getStringArray(R.array.pokemon_name);
        pokemon_pics = getResources().obtainTypedArray(R.array.pokemon_pic);
        pokemon_desc = getResources().getStringArray(R.array.pokemon_desc);

        for (int i = 0; i < pokemon_names.length; i++) {
            RowItem item = new RowItem(pokemon_names[i], pokemon_pics.getResourceId(i, -1));
            rowItems.add(item);
        }

        mylistview = (ListView) findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(mylistview.getContext(), rowItems);
        mylistview.setAdapter(adapter);

        mylistview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position == 4) {
            MyQuestionDialogFragment fragment = new MyQuestionDialogFragment();
            fragment.show(getFragmentManager(), "question");
        }
        else {
            getDetails(position);
        }
    }

    public void initList(){
        pokemon_images.add("pokemon01.jpg");
        pokemon_images.add("pokemon02.jpg");
        pokemon_images.add("pokemon03.jpg");
        pokemon_images.add("pokemon04.jpg");
        pokemon_images.add("pokemon05.jpg");
    }

    public void getDetails(int position){
        Intent myIntent = new Intent(MainActivity.this, PokemonActivity.class);

        myIntent.putExtra("Name", pokemon_names[position]);
        myIntent.putExtra("Image", pokemon_images.get(position));

        startActivityForResult(myIntent, position);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.call) {
            Intent PokemonInfo = new Intent(MainActivity.this,PokemonInfo.class);
            startActivity(PokemonInfo);
            return true;
        }

        else if (id == R.id.uninstall) {
            Intent deleteApp = new Intent(Intent.ACTION_DELETE);
            deleteApp.setData(Uri.parse("package:test.listviewtest"));
            startActivity(deleteApp);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        getDetails(4);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
