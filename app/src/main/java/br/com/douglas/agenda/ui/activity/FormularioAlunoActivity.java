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

    public static final String TITULO_APPBAR = "Novo aluno";
    EditText campoNome;
    EditText campoTelefone;
    EditText campoEmail;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        setTitle(TITULO_APPBAR);

        AlunoDAO alunoDAO = new AlunoDAO();
        bindCampos();
        configuraBtnSalvar(alunoDAO);

        Intent dados = getIntent();

        if (dados.hasExtra("aluno")) {
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        } else {
            aluno = new Aluno();
        }
    }

    private void bindCampos() {
        campoNome = findViewById(R.id.activity_form_aluno_nome);
        campoTelefone = findViewById(R.id.activity_form_aluno_telefone);
        campoEmail = findViewById(R.id.activity_form_aluno_email);
    }

    private void configuraBtnSalvar(AlunoDAO alunoDAO) {
        Button btnSalvar = findViewById(R.id.activity_form_aluno_btn_salvar);
        btnSalvar.setOnClickListener(view -> {
//            Aluno alunoCriado = criaAluno(campoNome, campoTelefone, campoEmail);
//            salva(alunoDAO, alunoCriado);
            preencheAluno();

            if (aluno.temIdValido()) {
                alunoDAO.edita(aluno);
            } else {
                alunoDAO.salva(aluno);
            }
            finish();
        });
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }
}