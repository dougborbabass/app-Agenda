package br.com.douglas.agenda;

import android.app.Application;

import br.com.douglas.agenda.dao.AlunoDAO;
import br.com.douglas.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.salva(new Aluno("Fran", "123456", "gmail@gmail"));
        alunoDAO.salva(new Aluno("Marta", "9879874", "gmail@gmail"));
    }
}
