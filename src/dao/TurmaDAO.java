package dao;

import config.DataBase;
import entities.Turma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {

	private Connection connection;

	public TurmaDAO() {
		this.connection = new DataBase().conect();
	}

	public void adicionarBanco(Turma Turma) {
		String sql = "INSERT INTO turma(nome, idProfessor) VALUES(?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, Turma.getNome());
			stmt.setString(2, Turma.getIdProfessor());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean atualizarTurma(Turma Turma) {
		String sql = "Update turma set nome = ?, idProfessor = ? WHERE id = ?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, Turma.getNome());
			stmt.setString(2, Turma.getIdProfessor());
			stmt.setString(3, Turma.getId());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Turma> selectListaTurma() {
		String sql = "SELECT t.id, t.nome Matéria, p.nome as Professor FROM escola.turma t join professor p on t.idProfessor = p.id;";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return recuperaTurma(stmt, rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Turma> selectTurmaPorId(String idTurma) {
		String sql = "SELECT * FROM turma WHERE id = " + idTurma;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return recuperaTurma(stmt, rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean deletaTurmaPorId(String idTurma) {
		String sql = "Delete from turma WHERE id = " + idTurma;
		try {
			Statement stmt = connection.createStatement();
			if (stmt.execute(sql)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private List<Turma> recuperaTurma(Statement stmt, ResultSet rs) throws SQLException {
		List<Turma> lista = new ArrayList<Turma>();

		while (rs.next()) {
			Turma Turma = new Turma();

			Turma.setId(rs.getString("id"));
			Turma.setNome(rs.getString("Matéria"));
			Turma.setIdProfessor(rs.getString("Professor"));

			lista.add(Turma);
		}

		stmt.close();

		return lista;
	}
}
