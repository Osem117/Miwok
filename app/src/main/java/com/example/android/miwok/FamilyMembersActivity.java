package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father","patter",R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother","motar",R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("broder","brudah",R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("sister","sistah",R.drawable.family_younger_sister, R.raw.family_older_sister));
        words.add(new Word("grandfather","bueloh",R.drawable.family_grandfather, R.raw.family_grandfather));
        words.add(new Word("grandmother","buelah",R.drawable.family_grandmother, R.raw.family_grandmother));

        Log.v("StateFam",words.get(0).toString());

        //Create the adapter
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);

        //Load the view that es una listView (un layout especial para adaptadores) ya creada y generica. Es un recurso
        ListView listView = (ListView)findViewById(R.id.list);

        //Add the adapter to the view (que lleva la custom layout normal
        listView.setAdapter(adapter);

        //pone un listener para saltar cuando se pulsr
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word word = words.get(position);
                mMediaPlayer = MediaPlayer.create(FamilyMembersActivity.this, word.getmAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if(mMediaPlayer != null){
                            mMediaPlayer.release();
                            mMediaPlayer = null;
                        }
                    }
                });
            }
        });

    }
}
