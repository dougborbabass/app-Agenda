package br.com.douglas.agenda.ui.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.douglas.agenda.R;
import br.com.douglas.agenda.dao.AlunoDAO;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlunoDAO alunoDAO = new AlunoDAO();

        setTitle("Lista de alunos");



        ListView listaDeAlunos = findViewById(R.id.listView_alunos);
        listaDeAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunoDAO.todos()));
    }
}
