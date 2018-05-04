package br.usjt.deswebmob.sinbot;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.github.library.bubbleview.BubbleTextView;

import java.util.ArrayList;
import java.util.List;

public class Chatbot extends AppCompatActivity {
    public static String PERGUNTA = "br.usjt.sinplancontrolchatboot.PERGUNTA";
    private ListView listView;
    private EditText editText;
    private List<Chat> list_chat = new ArrayList<>();
    private FloatingActionButton btn_send_message;
    private String answer, nome, questionStr;
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        nome = getIntent().getStringExtra(MainActivity.NOME);

        listView = (ListView)findViewById(R.id.listaConversa);
        editText = (EditText)findViewById(R.id.pergunta);
        btn_send_message = (FloatingActionButton)findViewById(R.id.fab);


    }


    public void upCount(View view){
        contador++;
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
        String oi = "Oi";
        String td = "Tudo bem?";
        String chupa = "chupa";
        String piroca = "piroca";

        if(question.equalsIgnoreCase(oi)){
            answer = "Olá";
        }
        if(question.equalsIgnoreCase(td)){
            answer = "Tudo e você?";
        }
        if(question.equalsIgnoreCase(chupa)){
            answer = "Chupo mesmo!!!";
        }
        if(question.equalsIgnoreCase(piroca)){
            answer = "O Vini gosta!!!";
        }
        Chat chat = new Chat(answer, false);
        if(contador != 2) {
            list_chat.add(chat);

            new AnwserQuestion().execute(list_chat);
        }else{
            Intent intent = new Intent(this, Dialogo.class);
            startActivity(intent);
        }
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
}
