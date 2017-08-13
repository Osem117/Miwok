package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RemoteControlClient;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;
/**ESTE ES EL UNICO QUE TIENE BIEN IMPLEMENTADO LOS PERMISOS DE AUDIO */
public class ColorsActivity extends AppCompatActivity {


    //Variables globales
    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    //listener para cuando cambia el derecho de reproduccion
    AudioManager.OnAudioFocusChangeListener afCangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        //pause playback
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);//porque como son palabras y frases cortas mejor que las repita desde el principio
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        //resume playback tenemos de nuevo el power
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        //stop playback
                        releaseMediaPlayer();
                    }
                }
            };

    //cuando el mediaplayer termina de reproducir salta este disparador
    private MediaPlayer.OnCompletionListener mCompretionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //crear y poner a punto el audioManager parapedir el audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("black", "nero", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "vranco", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("rojo", "rouso", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("marron", "morono", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("verde", "berd", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("amarillo", "amaianto", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Word("gris", "greo", R.drawable.color_gray, R.raw.color_gray));

        //creo el adaptador custom para mi tipo custom
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);

        //buso la vista que necesito
        ListView listView = (ListView) findViewById(R.id.list);

        //añado a esa vista el adaptador
        listView.setAdapter(adapter);


        //pone un listener para saltar cuando se pulse
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //obtengo la palabra que necesito en estemomento.
                Word word = words.get(position);

                //libero el mediaplayer para no tener basura previa
                releaseMediaPlayer();


                /**genero el result con una formula obteniendo un int.
                 * En esta formula hay ints que son:
                 * 1- un listener (instanciado o preparado)
                 * 2- el tipo de audio (?)
                 * 3- la prioridad del audio
                 * Si este coincide con la var global prefefinida AUDIOFOCUS REQUEST GRANTED
                 * entonces se le concede el permiso de uso*/

                //pedir el audiofocus para playback
                int result = mAudioManager.requestAudioFocus(afCangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) { //si tengo el audiofocus (derecho de uso)
                    //ahora tenemos audiofocus

                    //Creo y preparo el media playerpara el recurso asociado
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getmAudioResourceId());

                    //start audio file
                    mMediaPlayer.start();

                    //pongo un listener que he creado arriba para parar y liberar el media player una vez que el sonido ha acabado
                    mMediaPlayer.setOnCompletionListener(mCompretionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop(); //cuando la app pasa a stop (segundo plano) se liberan los recursos
        releaseMediaPlayer();
    }


    private void releaseMediaPlayer() {
        //si el media player no es null es que está instanciado y ocupa memoria
        if (mMediaPlayer != null) {
            //lo libero
            mMediaPlayer.release();
            //y lo pongo a null
            mMediaPlayer = null;

            //abandonar audioFocus cuando termina.
            // De este modo no tenemos mas llamadas ni realimentacion porque tambien desregistra el changeListener
            mAudioManager.abandonAudioFocus(afCangeListener);
        }
    }
}
