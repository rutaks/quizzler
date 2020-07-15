package rw.rutaks.quizzler.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import rw.rutaks.quizzler.R;
import rw.rutaks.quizzler.model.QuizListModel;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    private static final String TAG = "LIST_FRAGMENT";
    private ListViewModel listViewModel;
    private RecyclerView recyclerView;
    private QuizListAdapter adapter;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "VIEW_CREATED::");
        recyclerView = view.findViewById(R.id.list_view);
        adapter = new QuizListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listViewModel = new ViewModelProvider(getActivity()).get(ListViewModel.class);
        listViewModel.getQuizListLiveData().observe(getViewLifecycleOwner(), new Observer<List<QuizListModel>>() {
            @Override
            public void onChanged(List<QuizListModel> quizListModels) {
                Log.d(TAG, "DATA_CHANGED::");
                adapter.setQuizListModels(quizListModels);
                adapter.notifyDataSetChanged();
            }
        });
    }
}