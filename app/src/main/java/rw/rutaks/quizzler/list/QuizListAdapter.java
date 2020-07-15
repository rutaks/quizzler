package rw.rutaks.quizzler.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import rw.rutaks.quizzler.R;
import rw.rutaks.quizzler.model.QuizListModel;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.QuizViewHolder> {
    List<QuizListModel> quizListModels;

    public void setQuizListModels(List<QuizListModel> quizListModels) {
        this.quizListModels = quizListModels;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_layout_item, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.listTitle.setText(quizListModels.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (quizListModels == null){
            return 0;
        }
        return quizListModels.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        private ImageView listImage;
        private TextView listTitle;
        private TextView listDesc;
        private TextView listLevel;
        private Button listBtn;
        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);

            listImage = itemView.findViewById(R.id.quiz_item_image);
            listTitle = itemView.findViewById(R.id.quiz_item_title);
            listDesc = itemView.findViewById(R.id.quiz_item_description);
            listLevel = itemView.findViewById(R.id.quiz_item_difficulty);
            listBtn = itemView.findViewById(R.id.view_quiz_item_btn);

        }
    }
}
