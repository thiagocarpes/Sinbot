package br.usjt.deswebmob.sinbot;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class DialogoDesculpas extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialogo_desculpas);
    }

    public void finishDialog(View v) {
        DialogoDesculpas.this.finish();

        Intent intent = new Intent(this, ChamadoAtendimento.class);
        startActivity(intent);
    }
}
