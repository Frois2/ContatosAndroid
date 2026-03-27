package com.leobottaro.uatizapinatoracme;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.leobottaro.uatizapinatoracme.dao.ConversanteDAO;
import com.leobottaro.uatizapinatoracme.models.Conversante;

public class DetalheActivity extends AppCompatActivity {
    EditText edtNome, edtCelular, edtEmail;

    Button btnConfirma, btnAtualizar, btnExcluir;

    Conversante conversanteDetalhe;
    ConversanteDAO cDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtCelular = (EditText) findViewById(R.id.edtCelular);
        edtEmail = (EditText) findViewById(R.id.edtEmail);

        btnAtualizar = (Button) findViewById(R.id.btnAtualizar);
        btnConfirma = (Button) findViewById(R.id.btnInserir);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);

        ConversanteDAO cDAO = new ConversanteDAO(DetalheActivity.this);
        conversanteDetalhe = (Conversante) getIntent().getSerializableExtra("Conversante");

        edtNome.setText(conversanteDetalhe.getNome());
        edtEmail.setText(conversanteDetalhe.getEmail());
        edtCelular.setText(conversanteDetalhe.getCelular());

        edtNome.requestFocus();

        boolean hasId = conversanteDetalhe.getId()>0;

        btnConfirma.setVisibility(hasId? View.GONE : View.VISIBLE);
        btnAtualizar.setVisibility(hasId? View.VISIBLE : View.GONE);
        btnExcluir.setVisibility(hasId? View.VISIBLE : View.GONE);

    }

    public void Inserir(View view){
        Conversante c = new Conversante(
                edtNome.getText().toString(),
                edtCelular.getText().toString(),
                edtEmail.getText().toString()
        );
        cDAO.Open();
        Long sqlId = cDAO.Insert(c);
        cDAO.Close();

        if (sqlId == 0){
            Toast.makeText(this, "Erro ao Inserir", Toast.LENGTH_LONG).show();
            return;

        }
        setResult(RESULT_OK);
        finish();

    }

    public void Excluir(View view){
        cDAO.Excluir(conversanteDetalhe);

        Toast.makeText(null, "Conversante excluido", Toast.LENGTH_LONG).show();
        setResult(RESULT_OK);
        finish();
    }

    public void Atualizar(View view){
        conversanteDetalhe.setNome(edtNome.getText().toString());
        conversanteDetalhe.setCelular(edtCelular.getText().toString());
        conversanteDetalhe.setEmail(edtEmail.getText().toString());
        cDAO.Atualizar(conversanteDetalhe);
        Toast.makeText(null, "Conversante Atualizado", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }
}

