package br.usjt.deswebmob.sinbot;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;


public class ChamadoAtendimento extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamado_atendimento);

        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword("usjt.sinbot@gmail.com", "usjt.sinbot!!").addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Log.w("AUTH", "Falha ao efetuar o Login: ", task.getException());
                }else{
                    Log.d("AUTH", "Login Efetuado com sucesso!!!");
                }
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("AUTH", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d("AUTH", "onAuthStateChanged:signed_out");
                }

            }
        };

        database = FirebaseDatabase.getInstance().getReference("chamados");
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void sendToDB(View v){

        String id = database.push().getKey();

        EditText nome = findViewById(R.id.nome);
        EditText email = findViewById(R.id.email);
        EditText msg = findViewById(R.id.msg);

        Chamado chamado = new Chamado();
        chamado.setNome(nome.getText().toString());
        chamado.setEmail(email.getText().toString());
        chamado.setMsg(msg.getText().toString());

        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        chamado.setData(mydate);
        database.child(id).setValue(chamado);

        nome.setText("");
        email.setText("");
        msg.setText("");
        Toast.makeText(this, "Dados enviados com sucesso.", Toast.LENGTH_SHORT).show();


    }

}
