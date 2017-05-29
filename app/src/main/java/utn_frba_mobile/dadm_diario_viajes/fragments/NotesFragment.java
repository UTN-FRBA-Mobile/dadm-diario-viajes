package utn_frba_mobile.dadm_diario_viajes.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utn_frba_mobile.dadm_diario_viajes.R;
import utn_frba_mobile.dadm_diario_viajes.activities.MainActivity;
import utn_frba_mobile.dadm_diario_viajes.adapters.NotesAdapter;
import utn_frba_mobile.dadm_diario_viajes.models.Note;

public class NotesFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Note> notes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLayoutManager = new LinearLayoutManager(getActivity());

        // specify an adapter (see also next example)
        Date date = new Date();
        Note note1 = new Note("City Tour","Barcelona",date);
        Note note2 = new Note("Circuito Gastronómico","Barcelona",date);
        Note note3 = new Note("Recorrido Histórico","Barcelona",date);
        Note note4 = new Note("Circuito de Bares","Barcelona",date);

        notes = new ArrayList<>();
        notes.add(note1);
        notes.add(note2);
        notes.add(note3);
        notes.add(note4);

        mAdapter = new NotesAdapter((MainActivity) getActivity(), notes);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.notes_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        // TODO should be a way to create layout manager onCreate
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                notes.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }

            @Override
            public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
                super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        return view;
    }
}
