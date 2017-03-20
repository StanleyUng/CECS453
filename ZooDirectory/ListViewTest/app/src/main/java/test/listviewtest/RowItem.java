package test.listviewtest;

/**
 * Created by Stanley Ung on 3/2/2017.
 */

public class RowItem {

    private String pokemon_name;
    private int pokemon_pic_id;


    public RowItem(String name, int id) {
        this.pokemon_name = name;
        this.pokemon_pic_id = id;
    }

    public String getPokemon_name() { return pokemon_name; }

    public int getPokemon_pic_id() { return pokemon_pic_id; }
}
