package br.com.douglas.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.douglas.agenda.database.dao.AlunoDAO;
import br.com.douglas.agenda.database.dao.TelefoneDAO;
import br.com.douglas.agenda.model.Aluno;
import br.com.douglas.agenda.model.Telefone;
import br.com.douglas.agenda.model.TipoTelefone;

public class EditaAlunoTask extends AsyncTask<Void, Void, Void> {

    private final AlunoDAO alunoDAO;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final TelefoneDAO telefoneDAO;
    private List<Telefone> telefonesDoAluno;
    private final AlunoEditadoListener listener;

    public EditaAlunoTask(AlunoDAO alunoDAO, Aluno aluno,
                          Telefone telefoneFixo, Telefone telefoneCelular,
                          TelefoneDAO telefoneDAO, List<Telefone> telefonesDoAluno, AlunoEditadoListener listener) {
        this.alunoDAO = alunoDAO;
        this.aluno = aluno;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefoneDAO = telefoneDAO;
        this.telefonesDoAluno = telefonesDoAluno;
        this.listener = listener;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.edita(aluno);
        vinculaALunoComTelefone(aluno.getId(), telefoneFixo, telefoneCelular);
        atualizaIdsDosTelefones(telefoneFixo, telefoneCelular);
        telefoneDAO.atualiza(telefoneFixo, telefoneCelular);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.quandoEditado();
    }

    private void atualizaIdsDosTelefones(Telefone telefoneFixo, Telefone telefoneCelular) {
        for (Telefone telefone : telefonesDoAluno) {
            if (telefone.getTipo() == TipoTelefone.FIXO) {
                telefoneFixo.setId(telefone.getId());
            } else {
                telefoneCelular.setId(telefone.getId());
            }
        }
    }

    private void vinculaALunoComTelefone(int alunoId, Telefone... telefones) {
        for (Telefone telefone : telefones) {
            telefone.setAlunoId(alunoId);
        }
    }

    public interface AlunoEditadoListener {
        void quandoEditado();
    }
}
