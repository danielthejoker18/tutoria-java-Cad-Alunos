package dao;

import config.DataBase;
import entities.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

	private Connection connection;

	public ProfessorDAO() {
		this.connection = new DataBase().conect();
	}

	public void adicionarBanco(Professor Professor) {
		String sql = "INSERT INTO professor(nome, dataNascimento, dataCadastro) VALUES(?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, Professor.getNome());
			stmt.setString(2, Professor.getDataNascimento());
			stmt.setString(3, Professor.getDataCadastro());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean atualizarProfessor(Professor Professor) {
		String sql = "Update professor set nome = ?,dataNascimento = ?,dataCadastro = ? WHERE id = ?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, Professor.getNome());
			stmt.setString(2, Professor.getDataNascimento());
			stmt.setString(3, Professor.getDataCadastro());
			stmt.setString(4, Professor.getId());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Professor> selectListaProfessor() {
		String sql = "SELECT * FROM professor";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return recuperaProfessor(stmt, rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Professor> selectProfessorPorId(String idProfessor) {
		String sql = "SELECT * FROM Professor WHERE id = " + idProfessor;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return recuperaProfessor(stmt, rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean deletaProfessorPorId(String idProfessor) {
		String sql = "Delete from professor WHERE id = " + idProfessor;
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

	private List<Professor> recuperaProfessor(Statement stmt, ResultSet rs) throws SQLException {
		List<Professor> lista = new ArrayList<Professor>();

		while (rs.next()) {
			Professor Professor = new Professor();

			Professor.setId(rs.getString("id"));
			Professor.setNome(rs.getString("nome"));
			Professor.setDataNascimento(rs.getString("dataNascimento"));
			Professor.setDataCadastro(rs.getString("dataCadastro"));

			lista.add(Professor);
		}

		stmt.close();

		return lista;
	}
}
