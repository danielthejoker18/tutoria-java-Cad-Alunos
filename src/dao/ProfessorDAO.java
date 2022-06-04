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
		String sql = "INSERT INTO Professor(nome, nomeMae, nomePai, dataNascimento, dataCadastro, idTurma) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, Professor.getNome());
			stmt.setString(2, Professor.getNomeMae());
			stmt.setString(3, Professor.getNomePai());
			stmt.setString(4, Professor.getDataNascimento());
			stmt.setString(5, Professor.getDataCadastro());
			stmt.setString(6, Professor.getIdTurma());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean atualizarProfessor(Professor Professor) {
		String sql = "Update Professor set nome = ?, nomeMae = ?,nomePai = ?,dataNascimento = ?,dataCadastro = ? WHERE id = ?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, Professor.getNome());
			stmt.setString(2, Professor.getNomeMae());
			stmt.setString(3, Professor.getNomePai());
			stmt.setString(4, Professor.getDataNascimento());
			stmt.setString(5, Professor.getDataCadastro());
			stmt.setString(6, Professor.getId());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Professor> selectListaProfessor() {
		String sql = "SELECT * FROM Professor";
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
		String sql = "Delete from Professor WHERE id = " + idProfessor;
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
			Professor.setNomeMae(rs.getString("nomeMae"));
			Professor.setNomePai(rs.getString("nomePai"));
			Professor.setDataNascimento(rs.getString("dataNascimento"));
			Professor.setDataCadastro(rs.getString("dataCadastro"));

			lista.add(Professor);
		}

		stmt.close();

		return lista;
	}
}
