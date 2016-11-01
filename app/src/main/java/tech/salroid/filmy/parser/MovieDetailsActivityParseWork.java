package tech.salroid.filmy.parser;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tech.salroid.filmy.data_classes.CastDetailsData;
import tech.salroid.filmy.data_classes.CrewDetailsData;
import tech.salroid.filmy.data_classes.SimilarMoviesData;

/*
 * Filmy Application for Android
 * Copyright (c) 2016 Sajal Gupta (http://github.com/salroid).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class MovieDetailsActivityParseWork {

    private Context context;
    private String cast_result;

    public MovieDetailsActivityParseWork(Context context, String cast_result) {
        this.context = context;
        this.cast_result = cast_result;
    }

    public List<CastDetailsData> parse_cast() {


        final List<CastDetailsData> setterGettercastArray = new ArrayList<CastDetailsData>();
        CastDetailsData setterGettercast = null;

        final List<CrewDetailsData> setterGettercrewArray = new ArrayList<CrewDetailsData>();
        CrewDetailsData setterGettercrew = null;

        try {
            JSONObject jsonObject = new JSONObject(cast_result);

            JSONArray jsonArray = jsonObject.getJSONArray("cast");
            JSONArray crewArray=jsonObject.getJSONArray("crew");

            for (int i = 0; i < jsonArray.length(); i++) {

                setterGettercast = new CastDetailsData();

                String id = (jsonArray.getJSONObject(i)).getString("id");
                String character = (jsonArray.getJSONObject(i)).getString("character");
                String name = (jsonArray.getJSONObject(i)).getString("name");
                String cast_poster = (jsonArray.getJSONObject(i)).getString("profile_path");

                cast_poster="http://image.tmdb.org/t/p/w45"+cast_poster;


                setterGettercast.setCast_character(character);
                setterGettercast.setCast_name(name);
                setterGettercast.setCast_profile(cast_poster);
                setterGettercast.setCast_id(id);

                setterGettercastArray.add(setterGettercast);


            }

            for (int i=0;i<crewArray.length();i++){

                setterGettercrew=new CrewDetailsData();

                String crew_id = (crewArray.getJSONObject(i)).getString("id");
                String crew_job = (crewArray.getJSONObject(i)).getString("job");
                String crew_name = (crewArray.getJSONObject(i)).getString("name");
                String crew_poster = "http://image.tmdb.org/t/p/w45"+(crewArray.getJSONObject(i)).getString("profile_path");



                if (crew_poster.contains("null")){
                 //don't put it into the list .
                }
                else {
                    Toast.makeText(context,crew_job,Toast.LENGTH_SHORT).show();
                    setterGettercrew.setCrew_id(crew_id);
                    setterGettercrew.setCrew_job(crew_job);
                    setterGettercrew.setCrew_name(crew_name);
                    setterGettercrew.setCrew_profile(crew_poster);
                    setterGettercrewArray.add(setterGettercrew);

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return setterGettercastArray;
    }

    public List<SimilarMoviesData> parse_similar_movies() {

        final List<SimilarMoviesData> setterGettersimilarArray = new ArrayList<SimilarMoviesData>();
        SimilarMoviesData setterGettersimilar = null;

        try {
            JSONObject jsonObject = new JSONObject(cast_result);

            JSONArray jsonArray = jsonObject.getJSONArray("results");;

            for (int i = 0; i < jsonArray.length(); i++) {

                setterGettersimilar = new SimilarMoviesData();

                String id= (jsonArray.getJSONObject(i)).getString("id");
                String title = (jsonArray.getJSONObject(i)).getString("original_title");
                String movie_poster = "http://image.tmdb.org/t/p/w185"+(jsonArray.getJSONObject(i)).getString("poster_path");

                if(movie_poster.contains("null")){

                }

                else {
                    setterGettersimilar.setMovie_id(id);
                    setterGettersimilar.setMovie_banner(movie_poster);
                    setterGettersimilar.setMovie_title(title);

                    setterGettersimilarArray.add(setterGettersimilar);
                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }




        return setterGettersimilarArray;
    }
}
