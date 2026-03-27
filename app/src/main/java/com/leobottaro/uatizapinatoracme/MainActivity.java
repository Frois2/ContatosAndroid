package com.leobottaro.uatizapinatoracme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.leobottaro.uatizapinatoracme.adapter.ConversanteAdapter;
import com.leobottaro.uatizapinatoracme.dao.ConversanteDAO;
import com.leobottaro.uatizapinatoracme.models.Conversante;

import java.util.List;
public class MainActivity extends AppCompatActivity {

    ListView lsvConversantes;
    List<Conversante> lista;
    ConversanteDAO cDAO;

    ActivityResultLauncher<Intent> detalheLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lsvConversantes = findViewById(R.id.lsvconversante); // ⚠️ importante

        cDAO = new ConversanteDAO(this);
        cDAO.Open();
        Listar();
        cDAO.Close();

        detalheLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result-> {
                    if (result.getResultCode() == RESULT_OK) {
                        cDAO.Open();
                        Listar();
                        cDAO.Close();
                    }

                });
    }


    public void Listar(){
        ConversanteAdapter adapter =
                new ConversanteAdapter(this, R.layout.item_detalhe, lista);

        lsvConversantes.setAdapter(adapter);

        lsvConversantes.setOnItemClickListener((view, list, posicao, id)->{
            Intent detalheDados = new Intent(this, DetalheActivity.class);
            detalheDados.putExtra("conversante", lista.get(posicao));
            detalheLauncher.launch(detalheDados);

        });
    }

    public void NovoConversante(View view){
        Intent intent = new Intent(MainActivity.this, DetalheActivity.class);
        intent.putExtra("conversante", new Conversante());
        detalheLauncher.launch(intent);
        }

}