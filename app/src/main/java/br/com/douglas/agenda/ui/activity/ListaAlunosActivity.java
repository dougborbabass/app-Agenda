package br.com.douglas.agenda.ui.activity;

import static br.com.douglas.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import br.com.douglas.agenda.R;
import br.com.douglas.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";

    private final ListaAlunosView listaAlunosView = new ListaAlunosView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(TITULO_APPBAR);

        configuraFabNovoAluno();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_remover) {
            listaAlunosView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
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
        listaAlunosView.atualizaAlunos();
    }

    private void configuraLista() {
        ListView listaDeAlunosListView = findViewById(R.id.listView_alunos);
        listaAlunosView.configuraAdapter(listaDeAlunosListView);
        configuraListenerDeCliquePorItem(listaDeAlunosListView);
        registerForContextMenu(listaDeAlunosListView);
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
}
