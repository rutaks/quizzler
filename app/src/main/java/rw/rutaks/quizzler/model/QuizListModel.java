package rw.rutaks.quizzler.model;

import com.google.firebase.firestore.DocumentId;

public class QuizListModel {

    @DocumentId
    private String quiz_id;
    private String desc, image, level, visibility, name;
    private int questions;

    public QuizListModel() {
    }

    public QuizListModel(String quiz_id, String desc, String image, String level, String visibility, String name, int questions) {
        this.quiz_id = quiz_id;
        this.desc = desc;
        this.image = image;
        this.level = level;
        this.visibility = visibility;
        this.name = name;
        this.questions = questions;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }
}
