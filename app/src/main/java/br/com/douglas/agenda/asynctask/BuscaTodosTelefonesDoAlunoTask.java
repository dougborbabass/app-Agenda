package br.com.douglas.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.douglas.agenda.database.dao.TelefoneDAO;
import br.com.douglas.agenda.model.Aluno;
import br.com.douglas.agenda.model.Telefone;

public class BuscaTodosTelefonesDoAlunoTask extends AsyncTask <Void, Void, List<Telefone>> {

    private final TelefoneDAO dao;
    private final Aluno aluno;
    private final TelefonesDoAlunoEncontradosListener listener;

    public BuscaTodosTelefonesDoAlunoTask(TelefoneDAO dao, Aluno aluno, TelefonesDoAlunoEncontradosListener listener) {
        this.dao = dao;
        this.aluno = aluno;
        this.listener = listener;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return dao.buscaTodosTelefonesDoAluno(aluno.getId());
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.quandoEncontrados(telefones);
    }

    public interface TelefonesDoAlunoEncontradosListener {
        void quandoEncontrados(List<Telefone> telefones);
    }
}
