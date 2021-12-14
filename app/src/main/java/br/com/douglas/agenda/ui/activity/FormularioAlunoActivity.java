package br.com.douglas.agenda.ui.activity;

import static br.com.douglas.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.douglas.agenda.R;
import br.com.douglas.agenda.database.AgendaDatabase;
import br.com.douglas.agenda.database.dao.AlunoDAO;
import br.com.douglas.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";

    EditText campoNome;
    EditText campoSobreNome;
    EditText campoTelefoneFixo;
    EditText campoTelefoneCelular;
    EditText campoEmail;
    private Aluno aluno;
    private AlunoDAO alunoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        AgendaDatabase database = AgendaDatabase.getInstance(this);

        alunoDAO = database.getRoomAlunoDAO();

        bindCampos();
        carregaInfosAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
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

    private void bindCampos() {
        campoNome = findViewById(R.id.activity_form_aluno_nome);
        campoSobreNome = findViewById(R.id.activity_form_aluno_sobrenome);
        campoTelefoneFixo = findViewById(R.id.activity_form_aluno_telefone_fixo);
        campoTelefoneCelular = findViewById(R.id.activity_form_aluno_telefone_celular);
        campoEmail = findViewById(R.id.activity_form_aluno_email);
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoSobreNome.setText(aluno.getSobrenome());
//        campoTelefoneFixo.setText(aluno.getTelefoneFixo());
//        campoTelefoneCelular.setText(aluno.getTelefoneCelular());
        campoEmail.setText(aluno.getEmail());
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String sobreNome = campoSobreNome.getText().toString();
        String telefoneFixo = campoTelefoneFixo.getText().toString();
        String telefoneCelular = campoTelefoneCelular.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setSobrenome(sobreNome);
//        aluno.setTelefoneFixo(telefoneFixo);
//        aluno.setTelefoneCelular(telefoneCelular);
        aluno.setEmail(email);
    }

    private void finalizaFormulario() {
        preencheAluno();
        if (aluno.temIdValido()) {
            alunoDAO.edita(aluno);
        } else {
            alunoDAO.salva(aluno);
        }
        finish();
    }
}