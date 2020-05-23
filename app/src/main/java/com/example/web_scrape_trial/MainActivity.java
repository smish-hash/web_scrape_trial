package com.example.web_scrape_trial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ArrayList<ParseItem> parseItems = new ArrayList<>();
    private ParseAdapter parseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        parseAdapter = new ParseAdapter(parseItems,this);
        recyclerView.setAdapter(parseAdapter);

        Content content = new Content();
        content.execute();
    }

    private class Content extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                    android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                    android.R.anim.fade_out));
            parseAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://www.imdb.com/chart/top/";
                Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("tbody.lister-list").select("tr");

                int size = data.size();
                for (int i =0;i<size;i++){
                    String imgUrl = data.select("td.posterColumn")
                            .select("img")
                            .eq(i)
                            .attr("src");

                    String title = data.select("td.titleColumn")
                            .select("a")
                            .eq(i)
                            .text();

                    String year = data.select("td.titleColumn").select("span.secondaryInfo")
                            .eq(i)
                            .text();
                    parseItems.add(new ParseItem(imgUrl,title,year));
                    Log.d("items","img: "+ imgUrl + ", title: "+title + ",year: " + year);
                }


            } catch (IOException e) {
                Toast.makeText(MainActivity.this,"No Internet!",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            return null;
        }
    }
}
