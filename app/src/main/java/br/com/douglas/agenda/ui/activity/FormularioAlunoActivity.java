package br.com.douglas.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import br.com.douglas.agenda.R;
import br.com.douglas.agenda.dao.AlunoDAO;
import br.com.douglas.agenda.model.Aluno;

import static br.com.douglas.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";

    EditText campoNome;
    EditText campoTelefone;
    EditText campoEmail;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        AlunoDAO alunoDAO = new AlunoDAO();
        bindCampos();
        configuraBtnSalvar(alunoDAO);
        carregaInfosAluno();
    }

    private void bindCampos() {
        campoNome = findViewById(R.id.activity_form_aluno_nome);
        campoTelefone = findViewById(R.id.activity_form_aluno_telefone);
        campoEmail = findViewById(R.id.activity_form_aluno_email);
    }

    private void configuraBtnSalvar(AlunoDAO alunoDAO) {
        Button btnSalvar = findViewById(R.id.activity_form_aluno_btn_salvar);
        btnSalvar.setOnClickListener(view -> {
            preencheAluno();
            finalizaFormulario(alunoDAO);
        });
    }

    private void carregaInfosAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }

    private void finalizaFormulario(AlunoDAO alunoDAO) {
        if (aluno.temIdValido()) {
            alunoDAO.edita(aluno);
        } else {
            alunoDAO.salva(aluno);
        }
        finish();
    }
}