package br.usjt.deswebmob.sinbot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.github.library.bubbleview.BubbleTextView;

import java.util.List;

/**
 * Created by supor on 30/04/2018.
 */

public class ChatAdapter extends BaseAdapter{
    private List<Chat> lista_chat;
    private Context context;
    private int contador = 0;
    private LayoutInflater layoutInflater;

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
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = convertView;

        if(view == null){
            if (lista_chat.get(position).isSend){
                view = layoutInflater.inflate(R.layout.lista_item_pergunta, null);

                BubbleTextView text_message = (BubbleTextView)view.findViewById(R.id.txtMensagem);

                text_message.setText(lista_chat.get(position).mensagem);

            }else {
                view = layoutInflater.inflate(R.layout.lista_item_resposta, null);

                BubbleTextView text_message = (BubbleTextView)view.findViewById(R.id.txtMensagem);

                text_message.setText(lista_chat.get(position).mensagem);

                Button btnNo = (Button) view.findViewById(R.id.reprovado);
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        contador = contador + 1;
                        if(contador >= 3){
                            Intent intent = new Intent(parent.getContext(), ChamadoAtendimento.class);
                            parent.getContext().startActivity(intent);
                            System.out.println(contador);
                        }else{
                            Intent intent = new Intent(parent.getContext(), DialogoReprovado.class);
                            parent.getContext().startActivity(intent);
                            System.out.println(contador);
                        }

                    }
                });

                Button btnYes = (Button) view.findViewById(R.id.aprovado);
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(parent.getContext(), DialogoAprovado.class);
                        parent.getContext().startActivity(intent);
                    }
                });
            }
        }
        return view;
    }
}
