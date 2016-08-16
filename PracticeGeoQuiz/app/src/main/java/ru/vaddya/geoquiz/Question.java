package ru.vaddya.geoquiz;

/**
 * Created by Vadim on 8/7/2016.
 */
public class Question {

    private int testResId;
    private boolean answerTrue;

    public int getTextResId() {
        return testResId;
    }

    public void setTestResId(int testResId) {
        this.testResId = testResId;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }

    public Question(int mTestResId, boolean mAnswerTrue) {
        this.testResId = mTestResId;
        this.answerTrue = mAnswerTrue;

    }
}
