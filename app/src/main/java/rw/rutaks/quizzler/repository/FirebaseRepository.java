package rw.rutaks.quizzler.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Objects;

import rw.rutaks.quizzler.model.QuizListModel;

public class FirebaseRepository {

    private static final String TAG = "FIREBASE_REPOSITORY";
    private OnFirestoreTaskComplete onFirestoreTaskComplete;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference quizListRef = firebaseFirestore.collection("QuizList");

    public FirebaseRepository(OnFirestoreTaskComplete onFirestoreTaskComplete) {
        this.onFirestoreTaskComplete = onFirestoreTaskComplete;
    }

    public void getQuizListData(){
        quizListRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "onSuccess: ");
                    onFirestoreTaskComplete.quizListAdded(Objects.requireNonNull(task.getResult()).toObjects(QuizListModel.class));
                } else {
                    Log.d(TAG, "onError: ");
                    onFirestoreTaskComplete.onError(task.getException());
                }
            }
        });
    }

    public interface OnFirestoreTaskComplete {
        void quizListAdded(List<QuizListModel> quizListModels);
        void onError(Exception e);
    }
}
