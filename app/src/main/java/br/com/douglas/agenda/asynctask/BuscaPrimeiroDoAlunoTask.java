package br.com.douglas.agenda.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import br.com.douglas.agenda.database.dao.TelefoneDAO;
import br.com.douglas.agenda.model.Telefone;

public class BuscaPrimeiroDoAlunoTask extends AsyncTask<Void, Void, Telefone> {

    private final TelefoneDAO dao;
    private final TextView campoTelefone;
    private final int alunoId;

    public BuscaPrimeiroDoAlunoTask(TelefoneDAO dao, TextView telefone, int alunoId) {
        this.dao = dao;
        this.campoTelefone = telefone;
        this.alunoId = alunoId;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return dao.buscaPrimeiroTelefoneDoAluno(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        campoTelefone.setText(primeiroTelefone.getNumero());
    }
}
