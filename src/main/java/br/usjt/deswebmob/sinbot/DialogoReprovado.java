package br.usjt.deswebmob.sinbot;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class DialogoReprovado extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialogo_reprovado);
    }

    public void finishDialog(View v) {
        DialogoReprovado.this.finish();
    }
}
