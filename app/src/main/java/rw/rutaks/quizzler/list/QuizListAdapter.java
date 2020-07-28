package rw.rutaks.quizzler.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import rw.rutaks.quizzler.R;
import rw.rutaks.quizzler.model.QuizListModel;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.QuizViewHolder> {
    private List<QuizListModel> quizListModels;
    private OnItemClicked onItemClicked;

    public QuizListAdapter(OnItemClicked onItemClicked) {
        this.onItemClicked = onItemClicked;
    }

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
        String imageUrl = quizListModels.get(position).getImage();
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder_image).into(holder.listImage);
        String listDescription = quizListModels.get(position).getDesc();
        if(listDescription.length() > 100) {
            listDescription = quizListModels.get(position).getDesc().substring(0, 100);
        }
        holder.listDesc.setText(listDescription + "...");
        holder.listLevel.setText(quizListModels.get(position).getLevel());

    }

    @Override
    public int getItemCount() {
        if (quizListModels == null) {
            return 0;
        }
        return quizListModels.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            listBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClicked.onItemClicked(getAdapterPosition());
        }
    }

    public interface OnItemClicked{
        void onItemClicked(int position);
    }
}
