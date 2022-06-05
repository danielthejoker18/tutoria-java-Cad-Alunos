package service;

import dao.TurmaDAO;
import entities.Turma;

import java.util.List;

public class TurmaService {

	private TurmaDAO TurmaDAO = new TurmaDAO();

	public Turma validaTurma(Turma Turma) {
		if (Turma.getNome() != null
				&& !Turma.getNome().isEmpty()
				&& Turma.getIdProfessor() != null
				&& !Turma.getIdProfessor().isEmpty()) {
			TurmaDAO.adicionarBanco(Turma);
		} else {
			System.out.println(
					"Verifique os dados cadastrados. O nome do Turma e id do Professor são obrigatórios!");
		}

		return null;
	}

	public Turma validaTurmaUpdate(Turma Turma) {
		if (Turma.getNome() != null
				&& !Turma.getNome().isEmpty()
				&& Turma.getIdProfessor() != null
				&& !Turma.getIdProfessor().isEmpty()) {
			TurmaDAO.atualizarTurma(Turma);
		} else {
			System.out.println(
					"Verifique os dados cadastrados. O nome do Turma e o id do Professor são obrigatórios!");
		}

		return null;
	}

	public List<Turma> listaTurma() {
		return TurmaDAO.selectListaTurma();
	}

	public List<Turma> listaTurmaPorId(String id) {
		return TurmaDAO.selectTurmaPorId(id);
	}

	public boolean deletaTurmaPorId(String id) {
		if (id != "" && !id.isEmpty()) {
			return TurmaDAO.deletaTurmaPorId(id);
		} else {
			return false;
		}
	}
}
