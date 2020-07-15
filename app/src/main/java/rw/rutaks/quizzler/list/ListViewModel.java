package rw.rutaks.quizzler.list;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import rw.rutaks.quizzler.model.QuizListModel;
import rw.rutaks.quizzler.repository.FirebaseRepository;

public class ListViewModel extends ViewModel implements FirebaseRepository.OnFirestoreTaskComplete {
    private static final String TAG = "LIST_VIEW_MODEL";
    private MutableLiveData<List<QuizListModel>> quizListLiveData = new MutableLiveData<>();
    private FirebaseRepository firebaseRepository = new FirebaseRepository(this);

    public ListViewModel() {
        Log.d(TAG, "ListViewModel: Initiated");
        firebaseRepository.getQuizListData();
    }

    @Override
    public void quizListAdded(List<QuizListModel> quizListModels) {
        quizListLiveData.setValue(quizListModels);
    }

    @Override
    public void onError(Exception e) {
        Log.d(TAG, "onError: ", e.getCause());
    }

    public LiveData<List<QuizListModel>> getQuizListLiveData() {
        return quizListLiveData;
    }

}
