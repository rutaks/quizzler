package rw.rutaks.quizzler.start;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import rw.rutaks.quizzler.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class StartFragment extends Fragment {
    private static final String TAG = "START_TAG";
    private ProgressBar progressBar;
    private TextView textView;
    private NavController navController;
    private FirebaseAuth firebaseAuth;

    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        navController = Navigation.findNavController(view);
        progressBar = view.findViewById(R.id.start_progress);
        textView = view.findViewById(R.id.initializing_text);

        textView.setText("Checking if you exist bro...");
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            textView.setText("First time I see, Creating a new account...");
            firebaseAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        navController.navigate(R.id.action_startFragment_to_listFragment);
                    } else {
                        Log.d(TAG, "Failed: Exception: " + task.getException());
                    }
                }
            });
        } else {
            textView.setText("Yay All set up...");
            navController.navigate(R.id.action_startFragment_to_listFragment);
        }
    }
}