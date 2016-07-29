package tech.salroid.filmy.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import tech.salroid.filmy.CustomAdapter.CharacterDetailsActivityAdapter;
import tech.salroid.filmy.CustomAdapter.MovieDetailsActivityAdapter;
import tech.salroid.filmy.DataClasses.CharacterDetailsData;
import tech.salroid.filmy.DataClasses.MovieDetailsData;
import tech.salroid.filmy.Datawork.CharacterDetailActivityParseWork;
import tech.salroid.filmy.Datawork.MovieDetailsActivityParseWork;
import tech.salroid.filmy.R;

public class FullMovieActivity extends AppCompatActivity implements CharacterDetailsActivityAdapter.ClickListener {

    private RecyclerView full_movie_recycler;
    private String movie_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_movie);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        full_movie_recycler = (RecyclerView) findViewById(R.id.full_movie_recycler);
        full_movie_recycler.setLayoutManager(new LinearLayoutManager(FullMovieActivity.this));


        Intent intent = getIntent();
        if (intent != null) {
            movie_result = intent.getStringExtra("cast_json");
            getSupportActionBar().setTitle(intent.getStringExtra("toolbar_title"));
        }



        CharacterDetailActivityParseWork par= new CharacterDetailActivityParseWork(this,movie_result);
        List<CharacterDetailsData> char_list=par.char_parse_cast();
        Boolean size=false;
        CharacterDetailsActivityAdapter char_adapter= new CharacterDetailsActivityAdapter(this,char_list,size);
        char_adapter.setClickListener(this);
        full_movie_recycler.setAdapter(char_adapter);

    }

    @Override
    public void itemClicked(CharacterDetailsData setterGetterChar, int position) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("id",setterGetterChar.getChar_id());
        intent.putExtra("activity",false);
        startActivity(intent);

    }
}
