package utn_frba_mobile.dadm_diario_viajes.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import utn_frba_mobile.dadm_diario_viajes.R;

public class NoteFragment extends Fragment {

    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        //Intent intent = getIntent();
        //String noteName = intent.getStringExtra(Intent.EXTRA_TEXT);
        String noteName = "cargame de un intent vago";

        textView = (TextView) view.findViewById(R.id.note_name);
        textView.setText(noteName);

        return view;
    }

}
