package br.usjt.deswebmob.sinbot;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Chatbot extends AppCompatActivity {
    public static String PERGUNTA = "br.usjt.sinplancontrolchatboot.PERGUNTA";
    public static String QUEST = "";
    private ListView listView;
    private EditText editText;
    private List<Chat> list_chat = new ArrayList<>();
    private FloatingActionButton btn_send_message;
    private String answer, nome, questionStr;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        nome = getIntent().getStringExtra(MainActivity.NOME);

        listView = (ListView)findViewById(R.id.listaConversa);
        editText = (EditText)findViewById(R.id.pergunta);
        btn_send_message = (FloatingActionButton)findViewById(R.id.fab);

        String firstMessage = "Olá "+nome+" como posso ajudar?" ;

        Chat chat = new Chat(firstMessage, false);
        list_chat.add(chat);
        new AnwserQuestion().execute(list_chat);

    }


    public void sendQuestion(View view){
        questionStr = editText.getText().toString();


        Chat chat =  new Chat(questionStr, true);
        list_chat.add(chat);

        new AnwserQuestion().execute(list_chat);

        sendAnswer(questionStr);

        editText.setText("");
    }

    public void sendAnswer(String question){


        String nomeUsuario = nome;
        QUEST = question.replace("\"", "");
        new QnaMaker().execute("https://westus.api.cognitive.microsoft.com/qnamaker/v2.0/knowledgebases/73cb8ba0-8185-450d-9be8-99273ca9d21c/generateAnswer");

    }


    private class AnwserQuestion extends AsyncTask<List<Chat>, Void, String>{
        List<Chat> models;
        @Override
        protected String doInBackground(List<Chat>[] lists) {
            models = lists[0];
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            ChatAdapter chatAdapter = new ChatAdapter(list_chat, getApplicationContext());
            listView.setAdapter(chatAdapter);
        }
    }

    private class QnaMaker extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            OkHttpClient client = new OkHttpClient();

            try{
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, "{\"question\":\""+QUEST+"\"}");

                Request request = new Request.Builder().url(url[0])
                        .addHeader("Ocp-Apim-Subscription-Key", "f8e3ae8b4e404efa95ef0105af67b019")
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                Response response = client.newCall(request).execute();
                String json = response.body().string();

                System.out.println(json);
                return json;

            }catch (IOException e){
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String json) {

            try {

                double score = 0.0;
                int id = 0;
                JSONObject jObject = new JSONObject(json);
                JSONArray jArray = jObject.getJSONArray("answers");

                for (int i = 0; i < jArray.length(); i++) {

                    try {
                        JSONObject oneObject = jArray.getJSONObject(i);
                        // Pulling items from the array

                        if (oneObject.getDouble("score") > score){
                            score = oneObject.getDouble("score");
                            id = i;
                        }

                    } catch (JSONException e) {
                        // Oops
                    }

                }

                JSONObject oneObject = jArray.getJSONObject(id);
                String answer = (oneObject.getDouble("score")==0.0) ? getString(R.string.perguntaRetardada) : this.format(oneObject.getString("answer")) ;



                Chat chat = new Chat(answer, false);
                list_chat.add(chat);
                new AnwserQuestion().execute(list_chat);

            } catch (JSONException e) {
                // Oops
            }
        }

        public String format(String s){
            return s.replaceAll("&#227;", "ã")
                    .replaceAll("&#226;", "â")
                    .replaceAll("&#225;", "á")
                    .replaceAll("&#224;", "à")
                    .replaceAll("&#231;", "ç")
                    .replaceAll("&#201;", "É")
                    .replaceAll("&#233;", "é")
                    .replaceAll("&#232;", "è")
                    .replaceAll("&#234;", "ê")
                    .replaceAll("&#237;", "í")
                    .replaceAll("&#236;", "ì")
                    .replaceAll("&#243;", "ó")
                    .replaceAll("&#245;", "õ")
                    .replaceAll("&#244;", "ô")
                    .replaceAll("&#160;", " ")
                    .replaceAll("&#250;", "ú");
        }

    }
}
