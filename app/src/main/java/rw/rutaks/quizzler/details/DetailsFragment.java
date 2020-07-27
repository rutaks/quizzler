package rw.rutaks.quizzler.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import rw.rutaks.quizzler.R;
import rw.rutaks.quizzler.list.ListViewModel;
import rw.rutaks.quizzler.model.QuizListModel;

public class DetailsFragment extends Fragment implements View.OnClickListener {

    NavController navController;
    private ListViewModel listViewModel;

    private ImageView quizImage;
    private TextView quizTitle;
    private TextView quizDescription;
    private TextView quizDifficulty;
    private TextView quizQuestion;
    private Button startQuiz;

    private static final String TAG = "DetailsFragment";
    private int position;

    public DetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        position = DetailsFragmentArgs.fromBundle(getArguments()).getPosition();

        quizImage = view.findViewById(R.id.quiz_image);
        quizTitle = view.findViewById(R.id.quiz_title);
        quizDescription = view.findViewById(R.id.quiz_description);
        quizDifficulty = view.findViewById(R.id.quiz_difficulty);
        quizQuestion = view.findViewById(R.id.quiz_total_question_no);
        startQuiz = view.findViewById(R.id.start_quiz_btn);
        startQuiz.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listViewModel = new ViewModelProvider(getActivity()).get(ListViewModel.class);
        listViewModel.getQuizListLiveData().observe(getViewLifecycleOwner(), new Observer<List<QuizListModel>>() {
            @Override
            public void onChanged(List<QuizListModel> quizListModels) {
                Glide.with(getContext())
                        .load(quizListModels.get(position).getImage())
                        .centerCrop()
                        .placeholder(R.drawable.placeholder_image).into(quizImage);
                quizTitle.setText(quizListModels.get(position).getName());
                quizDescription.setText(quizListModels.get(position).getDesc());
                quizDifficulty.setText(quizListModels.get(position).getLevel());
                quizQuestion.setText(String.valueOf(quizListModels.get(position).getQuestions()));
            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_quiz_btn:
                DetailsFragmentDirections.ActionDetailsFragmentToQuizFragment action = DetailsFragmentDirections.actionDetailsFragmentToQuizFragment();
                action.setPosition(position);
                navController.navigate(action);
                break;
        }
    }
}