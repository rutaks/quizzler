package rw.rutaks.quizzler.quiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import rw.rutaks.quizzler.R;
import rw.rutaks.quizzler.model.QuizQuestionModel;
import rw.rutaks.quizzler.util.PlaceholderGenerator;

public class QuizFragment extends Fragment {
    private static final String TAG = "QuizFragment";
    private TextView questionStatus;
    private TextView question;
    private TextView questionNumber;
    private TextView questionTimer;
    private ProgressBar questionProgress;
    private Button option1;
    private Button option2;
    private Button option3;
    private Button option4;

    FirebaseFirestore firebaseFirestore;
    String quizId;
    private List<QuizQuestionModel> questions = new ArrayList<>();
    private int totalQuestions;
    private List<QuizQuestionModel> reAlignedQuestions = new ArrayList<>();
    private CountDownTimer countDownTimer;


    public QuizFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI(view);
        disableFields();
        firebaseFirestore = FirebaseFirestore.getInstance();
        quizId = QuizFragmentArgs.fromBundle(getArguments()).getQuizId();
        totalQuestions = QuizFragmentArgs.fromBundle(getArguments()).getTotalQuestions();
        firebaseDataSetup();
    }

    public void firebaseDataSetup() {
        firebaseFirestore
                .collection("QuizList")
                .document(quizId)
                .collection("Questions")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    questions = task.getResult().toObjects(QuizQuestionModel.class);
                    pickQuestions();
                    loadUI();
                } else {
                    questionStatus.setText("Error: " + task.getException().getMessage());
                }
            }
        });
    }

    private void pickQuestions() {
        for (int i = 0; i < totalQuestions; i++) {
            int randomNumber = getRandomNumber(0, questions.size());
            reAlignedQuestions.add(questions.get(randomNumber));
            questions.remove(randomNumber);
        }
    }

    private int getRandomNumber(int min, int max) {
        return ((int) (Math.random() * (max - min))) + min;
    }

    private void setupUI(View view) {
        questionStatus = view.findViewById(R.id.quiz_question_status);
        question = view.findViewById(R.id.quiz_question);
        questionNumber = view.findViewById(R.id.quiz_question_number);
        questionTimer = view.findViewById(R.id.quiz_question_time);
        questionProgress = view.findViewById(R.id.quiz_question_progress);
        option1 = view.findViewById(R.id.quiz_option_1);
        option2 = view.findViewById(R.id.quiz_option_2);
        option3 = view.findViewById(R.id.quiz_option_3);
        option4 = view.findViewById(R.id.quiz_option_4);
    }

    private void loadUI() {
        enableFields();
        loadingQuestions(1);
    }

    private void enableFields() {
        option1.setVisibility(View.VISIBLE);
        option2.setVisibility(View.VISIBLE);
        option3.setVisibility(View.VISIBLE);
        option4.setVisibility(View.VISIBLE);
        questionStatus.setText(R.string.question_is_ready);
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
        questionProgress.setVisibility(View.VISIBLE);
    }

    private void disableFields() {
        questionStatus.setText(PlaceholderGenerator.getLoadingMessage());

        option1.setVisibility(View.INVISIBLE);
        option2.setVisibility(View.INVISIBLE);
        option3.setVisibility(View.INVISIBLE);
        option4.setVisibility(View.INVISIBLE);

        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);

        questionProgress.setVisibility(View.INVISIBLE);
    }

    private void loadingQuestions(int question) {
//        questionNumber.setText(question);
        this.question.setText(reAlignedQuestions.get(question).getQuestion());
        option1.setText(reAlignedQuestions.get(question).getOption_a());
        option2.setText(reAlignedQuestions.get(question).getOption_b());
        option3.setText(reAlignedQuestions.get(question).getOption_c());
        option4.setText(reAlignedQuestions.get(question).getOption_d());

        startTimer(question);
    }

    private void startTimer(int question) {
        final Long timeToAnswer = reAlignedQuestions.get(question).getTimer();

        countDownTimer = new CountDownTimer(timeToAnswer * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                questionTimer.setText(millisUntilFinished / 1000 + "");
                Long progressPercent = millisUntilFinished / timeToAnswer * 10;
                questionProgress.setProgress(progressPercent.intValue());
            }

            @Override
            public void onFinish() {
                questionTimer.setText("0");
                disableFields();
            }
        };

        countDownTimer.start();
    }
}