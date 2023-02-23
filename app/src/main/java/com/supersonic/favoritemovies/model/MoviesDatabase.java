package com.supersonic.favoritemovies.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Genre.class, Movie.class}, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {

    private static MoviesDatabase instance;

    public abstract GenreDao getGenreDao();

    public abstract MovieDao getMovieDao();

    public static synchronized MoviesDatabase getInstance(Context context) {
        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(), MoviesDatabase.class, "moviesDB")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new InitialDataAsyncTask(instance).execute();
        }
    };

    private static class InitialDataAsyncTask extends AsyncTask<Void, Void, Void>{

        private GenreDao genreDao;
        private MovieDao movieDao;

        public InitialDataAsyncTask(MoviesDatabase database){
            genreDao = database.getGenreDao();
            movieDao = database.getMovieDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Genre comedyGenre = new Genre();
            comedyGenre.setGenreName("Comedy");

            Genre actionGenre = new Genre();
            actionGenre.setGenreName("Action");

            Genre dramaGenre = new Genre();
            dramaGenre.setGenreName("Drama");

            genreDao.insert(comedyGenre);
            genreDao.insert(actionGenre);
            genreDao.insert(dramaGenre);


            Movie movie1 = new Movie();
            movie1.setMovieName("SHREK");
            movie1.setMovieDescription("Once upon a time, in a far away swamp, there lived an ogre named Shrek (Mike Myers) whose precious solitude is suddenly shattered by an invasion of annoying fairy tale characters.");
            movie1.setGenreId(1);

            Movie movie2 = new Movie();
            movie2.setMovieName("Parasite");
            movie2.setMovieDescription("All unemployed, Ki-taek and his family take peculiar interest in the wealthy and glamorous Parks, as they ingratiate themselves into their lives and get entangled in an unexpected incident.");
            movie2.setGenreId(1);

            Movie movie3 = new Movie();
            movie3.setMovieName(" Once Upon a Time... in Hollywood");
            movie3.setMovieDescription("A faded television actor and his stunt double strive to achieve fame and success in the film industry during the final years of Hollywood's Golden Age in 1969 Los Angeles.");
            movie3.setGenreId(1);

            Movie movie4 = new Movie();
            movie4.setMovieName("Thor: Love and Thunder");
            movie4.setMovieDescription("\"Thor: Love and Thunder\" finds Thor on a journey unlike anything he's ever faced -- a quest for inner peace. But his retirement is interrupted by a galactic killer known as Gorr the God Butcher, who seeks the extinction of the gods. To combat the threat, Thor enlists the help of King Valkyrie, Korg and ex-girlfriend Jane Foster, who -- to Thor's surprise -- inexplicably wields his magical hammer, Mjolnir, as the Mighty Thor. Together, they embark upon a harrowing cosmic adventure to uncover the mystery of the God Butcher's vengeance and stop him before it's too late.");
            movie4.setGenreId(2);

            Movie movie5 = new Movie();
            movie5.setMovieName("TOP GUN: MAVERICK");
            movie5.setMovieDescription("After more than thirty years of service as one of the Navy’s top aviators, Pete “Maverick” Mitchell (Tom Cruise) is where he belongs, pushing the envelope as a courageous test pilot and dodging the advancement in rank that would ground him.");
            movie5.setGenreId(2);

            Movie movie6 = new Movie();
            movie6.setMovieName("INTERSTELLAR");
            movie6.setMovieDescription("In Earth's future, a global crop blight and second Dust Bowl are slowly rendering the planet uninhabitable.");
            movie6.setGenreId(2);

            Movie movie7 = new Movie();
            movie7.setMovieName("1917");
            movie7.setMovieDescription("Two young British soldiers during the First World War are given an impossible mission: deliver a message deep in enemy territory that will stop 1,600 men, and one of the soldiers' brothers, from walking straight into a deadly trap.");
            movie7.setGenreId(3);

            Movie movie8 = new Movie();
            movie8.setMovieName("The Witcher");
            movie8.setMovieDescription("Geralt of Rivia, a solitary monster hunter, struggles to find his place in a world where people often prove more wicked than beasts.");
            movie8.setGenreId(3);

            Movie movie9 = new Movie();
            movie9.setMovieName("The Outsider");
            movie9.setMovieDescription("Investigators are confounded over an unspeakable crime that's been committed.");
            movie9.setGenreId(3);

            movieDao.insert(movie1);
            movieDao.insert(movie2);
            movieDao.insert(movie3);
            movieDao.insert(movie4);
            movieDao.insert(movie5);
            movieDao.insert(movie6);
            movieDao.insert(movie7);
            movieDao.insert(movie8);
            movieDao.insert(movie9);

            return null;
        }
    }
}
