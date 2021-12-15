package br.com.douglas.agenda.asynctask;

import android.os.AsyncTask;

import br.com.douglas.agenda.model.Telefone;

abstract class BaseAlunoComTelefoneTask extends AsyncTask<Void, Void, Void> {

    private final FinalizadaListener listener;

    protected BaseAlunoComTelefoneTask(FinalizadaListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.quandoFinalizada();
    }

    protected void vinculaALunoComTelefone(int alunoId, Telefone... telefones) {
        for (Telefone telefone : telefones) {
            telefone.setAlunoId(alunoId);
        }
    }

    public interface FinalizadaListener {
        void quandoFinalizada();
    }
}
