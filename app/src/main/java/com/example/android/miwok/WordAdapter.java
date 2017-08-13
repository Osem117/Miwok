package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by David on 03/08/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;
    // initialize the ArrayAdapter's internal storage for the context and the list.
    // the second argument is used when the ArrayAdapter is populating a single TextView.
    // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
    // going to use this second argument, so it can be any value. Here, we used 0.Como queremos meterle un color concreto del
    //values y es un id (int) metemos otra variable de entrada y asi cuando construyamos le podemos poner el color
    public WordAdapter(Activity context, ArrayList<Word> words, int colorResource) {
        super(context, 0, words);
        mColorResourceId = colorResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the string and set this text on the name TextView
        miwokTextView.setText(currentWord.getmMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the current value of the default word string object and set this text on the TextView
        defaultTextView.setText(currentWord.getmDefaultTranslation());


        //Find the imageView to put the image there
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        if(currentWord.hasImage()) {
            //Get the value and set to the view
            imageView.setImageResource(currentWord.getmImageResourceId());
            //nos aseguramos de que sea visible
            imageView.setVisibility(View.VISIBLE);
        }else{ //hide the image
            imageView.setVisibility(View.GONE);
        }

        //Set the theme color for the list item
        //find the view that contains text because is where we want to print color
        View textContainer = listItemView.findViewById(R.id.text_container);
        //find the color that the resource id maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        //set background color of the text container view
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}