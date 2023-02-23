package com.supersonic.favoritemovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.supersonic.favoritemovies.model.AppRepository;
import com.supersonic.favoritemovies.model.Genre;
import com.supersonic.favoritemovies.model.Movie;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    AppRepository appRepository;
    private LiveData<List<Genre>> genres;
    private LiveData<List<Movie>> genreMovies;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        appRepository = new AppRepository(application);
    }

    public LiveData<List<Genre>> getGenres() {

        genres = appRepository.getGenres();
        return genres;
    }

    public LiveData<List<Movie>> getGenreMovies(int genreId) {

        genreMovies = appRepository.getGenreMovies(genreId);
        return genreMovies;
    }

    public void addNewMovie(Movie movie){

        appRepository.insertMovie(movie);

    }
    public void updateMovie(Movie movie){

        appRepository.updateMovie(movie);

    }
    public void deleteMovie(Movie movie){

        appRepository.deleteMovie(movie);

    }
}
