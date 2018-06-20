package br.usjt.deswebmob.sinbot;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.github.library.bubbleview.BubbleTextView;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;

/**
 * Created by supor on 30/04/2018.
 */

public class ChatAdapter extends BaseAdapter{
    private List<Chat> lista_chat;
    private Context context;
    private  int contador = 0;



    private LayoutInflater layoutInflater;

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public ChatAdapter(List<Chat> lista_chat, Context context) {
        this.lista_chat = lista_chat;
        this.context = context;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return lista_chat.size();
    }

    @Override
    public Object getItem(int position) {
        return lista_chat.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;

        if(view == null){
            if (lista_chat.get(position).isSend == 3) {
                view = layoutInflater.inflate(R.layout.lista_item, null);

                BubbleTextView text_message = (BubbleTextView) view.findViewById(R.id.txtMensagem);

                text_message.setText(lista_chat.get(position).mensagem);
            }if(lista_chat.get(position).isSend == 2){
                view = layoutInflater.inflate(R.layout.lista_item_resposta, null);

                BubbleTextView text_message = (BubbleTextView)view.findViewById(R.id.txtMensagem);

                text_message.setText(lista_chat.get(position).mensagem);

                Button btnNo = (Button) view.findViewById(R.id.reprovado);
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        contador = contador + 1;
                        if(contador >= 3){
                            String id = mDatabase.push().getKey();

                            lista_chat.get(position).setContador(contador);

                            mDatabase.child("chamadosSolucao").child(id).setValue(lista_chat.get(position));

                            System.out.println(lista_chat.get(position));

                            Intent intent = new Intent(parent.getContext(), DialogoDesculpas.class);
                            parent.getContext().startActivity(intent);
                        }else{
                            Intent intent = new Intent(parent.getContext(), DialogoReprovado.class);
                            parent.getContext().startActivity(intent);
                        }

                    }
                });

                Button btnYes = (Button) view.findViewById(R.id.aprovado);
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = mDatabase.push().getKey();

                        lista_chat.get(position).setContador(contador);

                        mDatabase.child("chamadosSolucao").child(id).setValue(lista_chat.get(position));

                        Intent intent = new Intent(parent.getContext(), DialogoAprovado.class);
                        parent.getContext().startActivity(intent);
                    }
                });
            }if(lista_chat.get(position).isSend == 1){
                view = layoutInflater.inflate(R.layout.lista_item_pergunta, null);

                BubbleTextView text_message = (BubbleTextView) view.findViewById(R.id.txtMensagem);

                text_message.setText(lista_chat.get(position).mensagem);
            }
        }
        return view;
    }
}
