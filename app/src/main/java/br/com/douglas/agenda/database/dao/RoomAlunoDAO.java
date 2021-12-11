package br.com.douglas.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.com.douglas.agenda.model.Aluno;

@Dao
public interface RoomAlunoDAO {
    @Insert
    void salva(Aluno aluno);

    @Query("SELECT * FROM Aluno")
    List<Aluno> todos();

    @Delete
    void remove(Aluno aluno);
}
