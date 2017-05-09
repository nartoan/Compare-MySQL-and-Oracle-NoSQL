package com.fit.NoBase.Model;

/**
 * Created by nartoan on 09/05/2017.
 */
public class Table {

    public static final String title_table = "CREATE TABLE title (" +
            "id INTEGER ," +
            "title STRING ," +
            "imdb_index STRING ," +
            "kind STRING ," +
            " production_year INTEGER ," +
            "  imdb_id INTEGER ," +
            "  phonetic_code STRING ," +
            "  episode_of_id INTEGER ," +
            "   season_nr INTEGER ," +
            "    episode_nr INTEGER ," +
            "    series_years STRING ," +
            "    md5sum STRING," +
            "   keyword ARRAY(STRING)," +
            "    PRIMARY KEY (id))";

    public static final String title_complete_cast_table = "CREATE TABLE title.complete_cast(" +
            "id_cast INTEGER ," +
            "subject STRING," +
            "status STRING," +
            "PRIMARY KEY (id_cast))";

    public static final String title_company_table = "CREATE TABLE title.company(" +
            "id_company INTEGER ," +
            " country_code INTEGER ," +
            " imdb_id INTEGER ," +
            "  md5sum STRING," +
            " name STRING," +
            " name_pcode_nf STRING," +
            " name_pcode_sf STRING," +
            " kind STRING," +
            " PRIMARY KEY (id_company))";

    public static final String title_infor_movie_table = "CREATE TABLE title.infor_movie ( " +
            "id_movie INTEGER," +
            "infor_type STRING," +
            "infor STRING," +
            "note STRING," +
            "PRIMARY KEY (id_movie))";

    public static final String title_aka_title_table = "CREATE TABLE title.aka_title (" +
            "id_aka_title INTEGER ," +
            " title STRING ," +
            " imdb_index STRING ," +
            " kind STRING ," +
            "production_year INTEGER ," +
            "  phonetic_code STRING ," +
            " episode_of_id INTEGER ," +
            " season_nr INTEGER ," +
            " episode_nr INTEGER ," +
            " md5sum STRING," +
            " PRIMARY KEY (id_aka_title))";

    public static final String title_cast_info_table = "  CREATE TABLE title.cast_info( " +
            "id_cast_info INTEGER, " +
            " char_name RECORD(name STRING , imdb_index STRING , imdb_id INTEGER ,  name_pcode_nf STRING , surname_pcode STRING, md5sum STRING)," +
            "  role STRING, " +
            " name RECORD( name STRING, imdb_index STRING, imdb_id INTEGER , gender STRING ,  name_pcode_cf STRING ,   name_pcode_nf STRING , surname_pcode STRING ,  md5sum STRING), " +
            " PRIMARY KEY(id_cast_info))";
}
