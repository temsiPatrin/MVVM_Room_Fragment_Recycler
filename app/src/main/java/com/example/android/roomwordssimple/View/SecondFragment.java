package com.example.android.roomwordssimple.View;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.android.roomwordssimple.R;

public class SecondFragment extends Fragment {

    ListenerSecondFragment listenerSecondFragment;
    EditText editText;

    interface ListenerSecondFragment {
        void saveNewWord(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() instanceof ListenerSecondFragment) {
            listenerSecondFragment = (ListenerSecondFragment) getActivity();
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        editText = view.findViewById(R.id.edit_word);
        Button button = view.findViewById(R.id.button_save);

        button.setOnClickListener(v->{
            if (listenerSecondFragment != null) {
                listenerSecondFragment.saveNewWord(editText.getText().toString());
            }
        });

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDetach() {
        listenerSecondFragment = null;
        super.onDetach();
    }
}
