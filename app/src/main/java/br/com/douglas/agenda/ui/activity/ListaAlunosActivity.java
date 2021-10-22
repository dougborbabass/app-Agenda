package br.com.douglas.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.douglas.agenda.R;
import br.com.douglas.agenda.dao.AlunoDAO;
import br.com.douglas.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";
    private final AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();

        alunoDAO.salva(new Aluno("Fran","123456","gmail@gmail"));
        alunoDAO.salva(new Aluno("Marta","9879874","gmail@gmail"));
        alunoDAO.salva(new Aluno("Fabio","3210984","gmail@gmail"));
        alunoDAO.salva(new Aluno("Dagoberto","687120","gmail@gmail"));
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton fabNovoAluno = findViewById(R.id.fab);
        fabNovoAluno.setOnClickListener(v -> abreFormularioAlunoActivity());
    }

    private void abreFormularioAlunoActivity() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.listView_alunos);
        List<Aluno> todosAlunos = alunoDAO.todos();
        listaDeAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                todosAlunos));

        listaDeAlunos.setOnItemClickListener((parent, view, position, id) -> {
            Aluno alunoEscolhido = todosAlunos.get(position);
            Intent vaiParaFormulario = new Intent(this, FormularioAlunoActivity.class);
            vaiParaFormulario.putExtra("aluno", alunoEscolhido);
            startActivity(vaiParaFormulario);
        });
    }
}
