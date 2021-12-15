package br.com.douglas.agenda.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.douglas.agenda.R;
import br.com.douglas.agenda.asynctask.BuscaPrimeiroTelefoneDoAlunoTask;
import br.com.douglas.agenda.database.AgendaDatabase;
import br.com.douglas.agenda.database.dao.TelefoneDAO;
import br.com.douglas.agenda.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
    private final TelefoneDAO telefoneDAO;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
        telefoneDAO = AgendaDatabase.getInstance(context).getTelefoneDAO();
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int pos) {
        return alunos.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return alunos.get(pos).getId();
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Aluno alunoDevolvido = alunos.get(i);
        vincula(viewCriada, alunoDevolvido);
        return viewCriada;
    }

    private void vincula(View view, Aluno aluno) {
        TextView nome = view.findViewById(R.id.item_aluno_nome);
        nome.setText(aluno.getNomeCompleto()); //+ " " + aluno.dataFormatada()
        TextView campoTelefone = view.findViewById(R.id.item_aluno_telefone);

        new BuscaPrimeiroTelefoneDoAlunoTask(telefoneDAO, aluno.getId(), telefoneEncontrado ->
                campoTelefone.setText(telefoneEncontrado.getNumero())).execute();
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater.from(context)
                .inflate(R.layout.item_aluno, viewGroup, false);
    }

    public void atualiza(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        this.alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
