package es.upm.miw.tmdb.manager.services;

import android.provider.BaseColumns;

public class EntityContract {
    private EntityContract() {
    }

    public static final String DB_NAME = "themoviedb.db";

    public static abstract class movieTable implements BaseColumns {
        public static final String TABLE_NAME = "movies";
        public static final String COL_ID = "_id";
        public static final String COL_TITLE = "title";
        public static final String COL_OVERVIEW = "overview";
        public static final String COL_POPULARITY = "popularity";
        public static final String COL_POSTERIMAGE = "poster_image";
        public static final String COL_BACKDROPIMAGE = "backdrop_image";
        public static final String COL_DIRECTING = "directing";
        public static final String COL_WRITING = "writing";
        public static final String COL_RELEASEDATE = "release_date";
    }

    public static abstract class personTable implements BaseColumns {
        public static final String TABLE_NAME = "persons";
        public static final String COL_ID = "_id";
        public static final String COL_NAME = "name";
        public static final String COL_BIOGRAPHY = "biography";
        public static final String COL_BIRTHDAY = "birthday";
        public static final String COL_DEATHDAY = "deathday";
        public static final String COL_PLACEOFBIRTH = "place_of_birth";
        public static final String COL_POPULARITY = "popularity";
        public static final String COL_BIGIMAGE = "big_image";
        public static final String COL_SMALLIMAGE = "small_image";
    }

    public static abstract class knownForTable implements BaseColumns {
        public static final String TABLE_NAME = "knownfors";
        public static final String COL_ID = "_id";
        public static final String COL_PERSONID = "person_id";
        public static final String COL_PERSONPOPULARITY = "person_popularity";
        public static final String COL_MOVIEID = "movie_id";
        public static final String COL_MOVIETITLE = "movie_title";
        public static final String COL_MOVIEOVERVIEW = "movie_overview";
        public static final String COL_MOVIEPOPULARITY = "movie_popularity";
        public static final String COL_MOVIEPOSTERIMAGE = "movie_poster_image";
        public static final String COL_MOVIEBACKDROPIMAGE = "movie_backdrop_image";
        public static final String COL_MOVIEDIRECTING = "movie_directing";
        public static final String COL_MOVIEWRITING = "movie_writing";
        public static final String COL_MOVIERELEASEDATE = "movie_release_date";
    }

    public static abstract class castTable implements BaseColumns {
        public static final String TABLE_NAME = "cast";
        public static final String COL_ID = "_id";
        public static final String COL_MOVIEID = "movie_id";
        public static final String COL_PERSONID = "person_id";
        public static final String COL_PERSONNAME = "person_name";
        public static final String COL_PERSONCHARACTER = "person_character";
        public static final String COL_PERSONBIOGRAPHY = "person_biography";
        public static final String COL_PERSONBIRTHDAY = "person_birthday";
        public static final String COL_PERSONDEATHDAY = "person_deathday";
        public static final String COL_PERSONPLACEOFBIRTH = "person_place_of_birth";
        public static final String COL_PERSONPOPULARITY = "person_popularity";
        public static final String COL_PERSONBIGIMAGE = "big_image";
        public static final String COL_PERSONSMALLIMAGE = "small_image";
    }
}
