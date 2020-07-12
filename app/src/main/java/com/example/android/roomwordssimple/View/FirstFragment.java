package com.example.android.roomwordssimple.View;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.roomwordssimple.R;
import com.example.android.roomwordssimple.WordViewModel;
import com.example.android.roomwordssimple.database.Word;

import java.util.List;

public class FirstFragment extends Fragment {

    private WordViewModel mWordViewModel;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);


        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(context);

        mWordViewModel.getAllWords().observe(getViewLifecycleOwner(), new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Word myWord = adapter.getWordAtPosition(position);
                Toast.makeText(context, "Deleting   "+myWord.getWord(), Toast.LENGTH_SHORT).show();
                mWordViewModel.deleteWord(myWord);
            }
        });
        helper.attachToRecyclerView(recyclerView);

        return view;
    }

    public void addNewWord(String text) {
        Word word = new Word(text);
        mWordViewModel.insert(word);
    }

    public void delete() {
        mWordViewModel.deleteAll();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
