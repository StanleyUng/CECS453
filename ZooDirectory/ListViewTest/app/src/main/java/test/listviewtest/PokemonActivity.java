package test.listviewtest;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class PokemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_activity);

        Intent myIntent = getIntent();
        Bundle myBundle = myIntent.getExtras();
        TextView name = (TextView) findViewById(R.id.pokemon_name);
        TextView description = (TextView) findViewById(R.id.pokemon_text);
        String pokemonName = myBundle.getString("Name");
        String pokemonImage = myBundle.getString("Image");
        String pokemonDescription= "";
        name.setText(pokemonName);
        switch(pokemonName){
            case "Bulbasaur":
                pokemonDescription = "Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun\\'s rays, the seed grows progressively larger.";
                break;
            case "Charmander":
                pokemonDescription = "The flame that burns at the tip of its tail is an indication of its emotions. The flame wavers when Charmander is enjoying itself. If the Pokémon becomes enraged, the flame burns fiercely.";
                break;
            case "Squirtle":
                pokemonDescription = "Squirtle\\'s shell is not merely used for protection. The shell\\'s rounded shape and the grooves on its surface help minimize resistance in water, enabling this Pokémon to swim at high speeds.";
                break;
            case "Pikachu":
                pokemonDescription = "Whenever Pikachu comes across something new, it blasts it with a jolt of electricity. If you come across a blackened berry, it\\'s evidence that this Pokémon mistook the intensity of its charge.";
                break;
            case "Eevee":
                pokemonDescription = "Eevee has an unstable genetic makeup that suddenly mutates due to the environment in which it lives. Radiation from various stones causes this Pokémon to evolve.";
                break;
        }

        description.setText(pokemonDescription);

        ImageView imageView = (ImageView) findViewById(R.id.pokemon_img);

        try {
            InputStream inputStream = getAssets().open(pokemonImage);
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) { e.printStackTrace();}
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.call) {
            Intent PokemonInfo = new Intent(PokemonActivity.this,PokemonInfo.class);
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
