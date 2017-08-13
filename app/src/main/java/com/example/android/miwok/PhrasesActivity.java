package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("jelou ma frand","tuta miseria porca", R.raw.phrase_are_you_coming));
        words.add(new Word("jau ar yu","kdise shulo", R.raw.phrase_come_here));
        words.add(new Word("in dis kountry","ente akapaka", R.raw.phrase_im_coming));
        words.add(new Word("aleat thungs","cosis de randomise", R.raw.phrase_lets_go));
        words.add(new Word("phrase 4 da life","lavida es dura", R.raw.phrase_where_are_you_going));

        //creamos el adaptador
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

        ///cargamos la vista que tenemos como viewlist para meterle el adaptador y hacerlo juay
        ListView listView = (ListView)findViewById(R.id.list);

        //le metemos el adapter
        listView.setAdapter(adapter);

        //pone un listener para saltar cuando se pulsr
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word word = words.get(position);
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getmAudioResourceId());
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
