package com.leobottaro.uatizapinatoracme;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.leobottaro.uatizapinatoracme.dao.ConversanteDAO;
import com.leobottaro.uatizapinatoracme.models.Conversante;
import com.leobottaro.uatizapinatoracme.models.ConversanteArrayAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtNome, edtCelular, edtEmail;
    ListView lstConversantes;
    ConversanteDAO cDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtCelular = (EditText)findViewById(R.id.edtCelular);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        lstConversantes = (ListView) findViewById(R.id.listConversas);
        cDAO = new ConversanteDAO(this);

        ListarContatinhos();
    }

    public void InserirTap(View view){
        Conversante contatinho = new Conversante(
            edtNome.getText().toString(),
            edtCelular.getText().toString(),
            edtEmail.getText().toString()
        );
        long novoId = cDAO.Insert(contatinho);

        // Fail early
        if(novoId == 0) {
            Toast.makeText(this, "Erro ao inserir", Toast.LENGTH_SHORT).show();
            return;
        }

        // Aqui deu tudo certo, segue o bonde
        ListarContatinhos();
    }

    public void NovoTap(View view){
        edtNome.setText("");
        edtCelular.setText("");
        edtEmail.setText("");
        edtNome.requestFocus();
    }

    public void ListarContatinhos(){
        List<Conversante> todosContatinhos = cDAO.GetAll();


        ConversanteArrayAdapter adapter =
            new ConversanteArrayAdapter(
                this,
                R.layout.contatinho_layout,
                todosContatinhos
        );
        lstConversantes.setAdapter(adapter);

    }
}
