package service;

import dao.ProfessorDAO;
import entities.Professor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProfessorService {

	private ProfessorDAO ProfessorDAO = new ProfessorDAO();

	public Professor validaProfessor(Professor Professor) {
		if (Professor.getNome() != null
				&& !Professor.getNome().isEmpty()
				&& Professor.getDataNascimento() != null
				&& !Professor.getDataNascimento().isEmpty()) {
			Professor.setDataCadastro(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
			ProfessorDAO.adicionarBanco(Professor);
		} else {
			System.out.println(
					"Verifique os dados cadastrados. O nome do Professor e data de nascimento são obrigatórios!");
		}

		return null;
	}

	public Professor validaProfessorUpdate(Professor Professor) {
		if (Professor.getNome() != null
				&& !Professor.getNome().isEmpty()
				&& Professor.getDataNascimento() != null
				&& !Professor.getDataNascimento().isEmpty()
				&& Professor.getId() != null
				&& !Professor.getId().isEmpty()) {
			Professor.setDataCadastro(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
			ProfessorDAO.atualizarProfessor(Professor);
		} else {
			System.out.println(
					"Verifique os dados cadastrados. O nome do Professor e a data de nascimento são obrigatórios!");
		}

		return null;
	}

	public List<Professor> listaProfessor() {
		return ProfessorDAO.selectListaProfessor();
	}

	public List<Professor> listaProfessorPorId(String id) {
		return ProfessorDAO.selectProfessorPorId(id);
	}

	public boolean deletaProfessorPorId(String id) {
		if (id != "" && !id.isEmpty()) {
			return ProfessorDAO.deletaProfessorPorId(id);
		} else {
			return false;
		}
	}
}
