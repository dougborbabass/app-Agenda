package br.com.douglas.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.douglas.agenda.R;
import br.com.douglas.agenda.dao.AlunoDAO;
import br.com.douglas.agenda.model.Aluno;

import static br.com.douglas.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";

    private final AlunoDAO alunoDAO = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();

        alunoDAO.salva(new Aluno("Fran", "123456", "gmail@gmail"));
        alunoDAO.salva(new Aluno("Marta", "9879874", "gmail@gmail"));
        alunoDAO.salva(new Aluno("Fabio", "3210984", "gmail@gmail"));
        alunoDAO.salva(new Aluno("Dagoberto", "687120", "gmail@gmail"));
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton fabNovoAluno = findViewById(R.id.fab);
        fabNovoAluno.setOnClickListener(v -> abreFormularioModoInsereAluno());
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView listaDeAlunosListView = findViewById(R.id.listView_alunos);
        List<Aluno> todosAlunos = alunoDAO.todos();

        configuraAdapter(listaDeAlunosListView, todosAlunos);
        configuraListenerDeCliquePorItem(listaDeAlunosListView);

        listaDeAlunosListView.setOnItemLongClickListener((adapterView, view, position, id) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
            alunoDAO.remove(alunoEscolhido);
            adapter.remove(alunoEscolhido);
            return true;
        });
    }

    private void configuraListenerDeCliquePorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener((adapterView, view, position, id) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
            abreFormularioModoEditaAluno(alunoEscolhido);
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent vaiParaFormulario = new Intent(this, FormularioAlunoActivity.class);
        vaiParaFormulario.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormulario);
    }

    private void configuraAdapter(ListView listaDeAlunos, List<Aluno> todosAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                todosAlunos);
        listaDeAlunos.setAdapter(adapter);
    }
}
