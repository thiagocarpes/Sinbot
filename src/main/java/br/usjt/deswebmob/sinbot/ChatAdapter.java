package br.usjt.deswebmob.sinbot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.github.library.bubbleview.BubbleTextView;

import java.util.List;

/**
 * Created by supor on 30/04/2018.
 */

public class ChatAdapter extends BaseAdapter{
    private List<Chat> lista_chat;
    private Context context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
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
            }
        }
        return view;
    }
}
