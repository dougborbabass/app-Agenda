package br.com.douglas.agenda.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import br.com.douglas.agenda.database.dao.RoomAlunoDAO;
import br.com.douglas.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 1, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {
    public abstract RoomAlunoDAO getRoomAlunoDAO();
}
