package test.listviewtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Stanl_000 on 3/3/2017.
 */

public class PokemonInfo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_info_layout);
        TextView PokemonInfo = (TextView) findViewById(R.id.call);
        PokemonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PokemonInfo = new Intent(Intent.ACTION_DIAL);
                PokemonInfo.setData(Uri.parse("tel:8888888"));
                startActivity(PokemonInfo);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.call) {
            Intent PokemonInfo = new Intent(PokemonInfo.this, PokemonInfo.class);
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
}
