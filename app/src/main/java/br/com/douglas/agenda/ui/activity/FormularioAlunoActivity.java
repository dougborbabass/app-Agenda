package br.com.douglas.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.douglas.agenda.R;
import br.com.douglas.agenda.dao.AlunoDAO;
import br.com.douglas.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        AlunoDAO alunoDAO = new AlunoDAO();

        final EditText campoNome = findViewById(R.id.activity_form_aluno_nome);
        final EditText campoTelefone = findViewById(R.id.activity_form_aluno_telefone);
        final EditText campoEmail = findViewById(R.id.activity_form_aluno_email);

        Button btnSalvar = findViewById(R.id.activity_form_aluno_btn_salvar);
        btnSalvar.setOnClickListener(view -> {
            String nome = campoNome.getText().toString();
            String telefone = campoTelefone.getText().toString();
            String email = campoEmail.getText().toString();

            Aluno alunoCriado = new Aluno(nome, telefone, email);
            alunoDAO.salva(alunoCriado);

            finish();
        });
    }
}