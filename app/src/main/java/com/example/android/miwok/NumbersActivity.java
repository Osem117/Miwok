package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "tuti", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "dfdfty", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "arty", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "lurri", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "ytttssi", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "zxta", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "arg", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "dfyr", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "gudf", R.drawable.number_ten, R.raw.number_ten));

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        //contexto actual   /    estilo         /  conjunto a tratar
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);


        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_listyout file.

        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.

        listView.setAdapter(adapter);

        //pone un listener para saltar cuando se pulsr
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word word = words.get(position);
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmAudioResourceId());
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
