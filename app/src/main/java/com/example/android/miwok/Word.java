package com.example.android.miwok;

/**
 * Created by David on 03/08/2017.
 */

public class Word {

    //Default translation for the word
    private String mDefaultTranslation;

    //miwok trans 4 the word
    private String mMiwokTranslation;

    //image resource id
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    //Constant para saber que no hay imagen. -1 fuera de rango de id
    private static final int NO_IMAGE_PROVIDED = -1;

    //Audio resource id
    private int mAudioResourceId;

    //esto es un constructor. No tiene un return porque no retorna nada. Crea un objeto y se le establecen las propiedades.
    //los parametros son las palabras en ambos idiomas y el id de la imagen referenciada.
    public Word(String defaultTranslation, String miwokTranslation, int aundioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = aundioResourceId;
    }

    public Word(String defaultTransaction, String miwokTransaction, int imageResourceId, int audioResourceId) {
        mDefaultTranslation = defaultTransaction;
        mMiwokTranslation = miwokTransaction;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }


    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmImageResourceId(){return mImageResourceId;}

    public boolean hasImage(){return mImageResourceId != NO_IMAGE_PROVIDED;}

    public int getmAudioResourceId(){return mAudioResourceId;}
}
