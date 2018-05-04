package br.usjt.deswebmob.sinbot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class DialogoFinal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialogo_final);
    }
    public void finishDialog(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        DialogoFinal.this.finish();
    }
}
