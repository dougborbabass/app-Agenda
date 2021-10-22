package br.com.douglas.agenda.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.douglas.agenda.R;
import br.com.douglas.agenda.dao.AlunoDAO;
import br.com.douglas.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";
    EditText campoNome;
    EditText campoTelefone;
    EditText campoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        setTitle(TITULO_APPBAR);

        AlunoDAO alunoDAO = new AlunoDAO();

        bindCampos();

        configuraBtnSalvar(alunoDAO);
    }

    private void bindCampos() {
        campoNome = findViewById(R.id.activity_form_aluno_nome);
        campoTelefone = findViewById(R.id.activity_form_aluno_telefone);
        campoEmail = findViewById(R.id.activity_form_aluno_email);
    }

    private void configuraBtnSalvar(AlunoDAO alunoDAO) {
        Button btnSalvar = findViewById(R.id.activity_form_aluno_btn_salvar);
        btnSalvar.setOnClickListener(view -> {
            Aluno alunoCriado = criaAluno(campoNome, campoTelefone, campoEmail);
            salva(alunoDAO, alunoCriado);
        });
    }

    private Aluno criaAluno(EditText campoNome, EditText campoTelefone, EditText campoEmail) {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();
        return new Aluno(nome, telefone, email);
    }

    private void salva(AlunoDAO alunoDAO, Aluno alunoCriado) {
        alunoDAO.salva(alunoCriado);
        finish();
    }
}