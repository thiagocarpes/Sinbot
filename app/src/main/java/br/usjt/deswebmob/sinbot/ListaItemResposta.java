package br.usjt.deswebmob.sinbot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ListaItemResposta extends AppCompatActivity {
    private int contador = 0;
    private Button btnNo, btnYes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_item_resposta);

        btnNo = (Button)findViewById(R.id.reprovado);
        btnYes = (Button)findViewById(R.id.aprovado);

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador = contador + 1;
                if(contador >= 3){
                    Intent intent = new Intent(ListaItemResposta.this, DialogoDesculpas.class);
                    startActivity(intent);
                    System.out.println(contador);
                }else{
                    Intent intent = new Intent(ListaItemResposta.this, DialogoReprovado.class);
                    startActivity(intent);
                    System.out.println(contador);
                }
                System.out.println(contador);
            }
        });

    }
}
