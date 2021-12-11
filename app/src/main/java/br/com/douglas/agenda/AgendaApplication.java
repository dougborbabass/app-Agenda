package br.com.douglas.agenda;

import android.app.Application;

import androidx.room.Room;

import br.com.douglas.agenda.dao.AlunoDAO;
import br.com.douglas.agenda.database.AgendaDatabase;
import br.com.douglas.agenda.database.dao.RoomAlunoDAO;
import br.com.douglas.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
        AgendaDatabase database = Room
                .databaseBuilder(this, AgendaDatabase.class, "agenda.db")
                .allowMainThreadQueries()
                .build();
        RoomAlunoDAO dao = database.getRoomAlunoDAO();
        dao.salva(new Aluno("Fran", "123456", "gmail@gmail"));
        dao.salva(new Aluno("Marta", "9879874", "gmail@gmail"));
    }
}
