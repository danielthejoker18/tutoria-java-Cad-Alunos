package service;

import dao.AlunoDAO;
import entities.Aluno;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AlunoService {

	private static AlunoDAO alunoDAO = new AlunoDAO();

	public Aluno validaAluno(Aluno aluno) {
		if (aluno.getNome() != null
				&& !aluno.getNome().isEmpty()
				&& aluno.getNomeMae() != null
				&& !aluno.getNomeMae().isEmpty()
				&& aluno.getDataNascimento() != null
				&& !aluno.getDataNascimento().isEmpty()) {
			aluno.setDataCadastro(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
			alunoDAO.adicionarBanco(aluno);
		} else {
			System.out.println(
					"Verifique os dados cadastrados. O nome do aluno, nome da mãe e data de nascimento são obrigatórios!");
		}

		return null;
	}

	public static Aluno validaAlunoUpdate(Aluno aluno) {
		if (aluno.getNome() != null
				&& !aluno.getNome().isEmpty()
				&& aluno.getNomeMae() != null
				&& !aluno.getNomeMae().isEmpty()
				&& aluno.getDataNascimento() != null
				&& !aluno.getDataNascimento().isEmpty()
				&& aluno.getId() != null
				&& !aluno.getId().isEmpty()) {
			aluno.setDataCadastro(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
			alunoDAO.atualizarAluno(aluno);
		} else {
			System.out.println(
					"Verifique os dados cadastrados. O nome do aluno, nome da mãe e data de nascimento são obrigatórios!");
		}

		return null;
	}

	public static List<Aluno> listaAlunos() {
		return alunoDAO.selectListaAlunos();
	}

	public List<Aluno> listaAlunoPorId(String id) {
		return alunoDAO.selectAlunoPorId(id);
	}

	public boolean deletaAlunoPorId(String id) {
		if (id != "" && !id.isEmpty()) {
			return alunoDAO.deletaAlunoPorId(id);
		} else {
			return false;
		}
	}
}
