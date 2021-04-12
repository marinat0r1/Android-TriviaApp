package com.example.trivia.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.trivia.controller.AppController;
import com.example.trivia.model.Question;
import com.example.trivia.util.ArrayUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {
    ArrayList<Question> questionArrayList = new ArrayList<>();
    private String url = "https://pub-quiz-game.herokuapp.com/history";

    public List<Question> getQuestions(final AnswerListAsyncResponse callBack) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                (JSONArray) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject jsonObject = response.getJSONObject(i);

                                ArrayList<String> incorrectAnswersList = ArrayUtil.convert(jsonObject.getJSONArray("incorrect_answers"));
                                ArrayList<String> possibleAnswerList = ArrayUtil.convert(jsonObject.getJSONArray("incorrect_answers"));
                                possibleAnswerList.add(jsonObject.getString("correct_answer"));
                                Collections.shuffle(possibleAnswerList);

                                Question question = new Question(
                                        jsonObject.getString("question"),
                                        jsonObject.getString("_id"),
                                        jsonObject.getString("category"),
                                        jsonObject.getString("type"),
                                        jsonObject.getString("difficulty"),
                                        possibleAnswerList,
                                        incorrectAnswersList,
                                        jsonObject.getString("correct_answer"),
                                        jsonObject.getInt("__v")
                                );

                                questionArrayList.add(question);

                                //System.out.println(question.toString());
                                //System.out.println(question.getDifficulty());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (callBack != null) callBack.processFinished(questionArrayList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return questionArrayList;
    }

}

// https://opentdb.com/api.php?amount=50&category=9&type=boolean