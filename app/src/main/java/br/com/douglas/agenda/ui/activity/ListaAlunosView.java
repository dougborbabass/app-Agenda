package br.com.douglas.agenda.ui.activity;

import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.room.Room;

import br.com.douglas.agenda.dao.AlunoDAO;
import br.com.douglas.agenda.database.AgendaDatabase;
import br.com.douglas.agenda.database.dao.RoomAlunoDAO;
import br.com.douglas.agenda.model.Aluno;
import br.com.douglas.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final ListaAlunosAdapter adapter;
    private final RoomAlunoDAO alunoDAO;
    private Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);
        alunoDAO = Room
                .databaseBuilder(context, AgendaDatabase.class, "agenda.db")
                .allowMainThreadQueries()
                .build()
                .getRoomAlunoDAO();
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que deseja remover o aluno?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                    remove(alunoEscolhido);
                })
                .setNegativeButton("Nao", null)
                .show();
    }

    public void atualizaAlunos() {
        adapter.atualiza(alunoDAO.todos());
    }

    private void remove(Aluno aluno) {
        alunoDAO.remove(aluno);
        adapter.remove(aluno);
    }

    public void configuraAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(this.adapter);
    }
}
