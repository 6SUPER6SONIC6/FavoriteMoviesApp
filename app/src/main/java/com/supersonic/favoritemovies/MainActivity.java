package com.supersonic.favoritemovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.supersonic.favoritemovies.databinding.ActivityMainBinding;
import com.supersonic.favoritemovies.model.Genre;
import com.supersonic.favoritemovies.model.Movie;
import com.supersonic.favoritemovies.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers clickHandlers;
    private Genre selectedGenre;
    private ArrayList<Genre> genreArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModel.class);

        clickHandlers = new MainActivityClickHandlers();
        activityMainBinding.setClickHandlers(clickHandlers);

        mainActivityViewModel.getGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {

                genreArrayList = (ArrayList<Genre>) genres;

                for (Genre genre : genres){
                    Log.d("Tag", genre.getGenreName());
                }

                showInSpinner();
            }
        });

        mainActivityViewModel.getGenreMovies(2).observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                for (Movie movie : movies){
                    Log.d("Tag", movie.getMovieName());
                }
            }
        });
    }

    private void showInSpinner() {

        ArrayAdapter<Genre> genreArrayAdapter = new ArrayAdapter<Genre>(this, R.layout.spinner_item, genreArrayList);
        genreArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(genreArrayAdapter);

    }

    public class MainActivityClickHandlers {

        public void onFabClicked(View view){

            Toast.makeText(MainActivity.this, "Button is clicked", Toast.LENGTH_SHORT).show();
            
        }

        public void onSelectedItem(AdapterView<?> parent, View view, int position, long id){

            selectedGenre = (Genre) parent.getItemAtPosition(position);

            String message = "id is " + selectedGenre.getId() + "\n name is " + selectedGenre.getGenreName();

            Toast.makeText(parent.getContext(), message, Toast.LENGTH_SHORT).show();

        }
    }

}