package rw.rutaks.quizzler.result;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rw.rutaks.quizzler.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {
    public ResultFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }
}