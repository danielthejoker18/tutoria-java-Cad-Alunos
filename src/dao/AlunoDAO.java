package dao;

import config.DataBase;
import entities.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

	private Connection connection;

	public AlunoDAO() {
		this.connection = new DataBase().conect();
	}

	public void adicionarBanco(Aluno aluno) {
		String sql = "INSERT INTO aluno(nome, nomeMae, nomePai, dataNascimento, dataCadastro) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, aluno.getNome());
			stmt.setString(2, aluno.getNomeMae());
			stmt.setString(3, aluno.getNomePai());
			stmt.setString(4, aluno.getDataNascimento());
			stmt.setString(5, aluno.getDataCadastro());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean atualizarAluno(Aluno aluno) {
		String sql = "Update aluno set nome = ?, nomeMae = ?,nomePai = ?,dataNascimento = ?,dataCadastro = ? WHERE id = ?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, aluno.getNome());
			stmt.setString(2, aluno.getNomeMae());
			stmt.setString(3, aluno.getNomePai());
			stmt.setString(4, aluno.getDataNascimento());
			stmt.setString(5, aluno.getDataCadastro());
			stmt.setString(6, aluno.getId());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Aluno> selectListaAlunos() {
		String sql = "SELECT * FROM aluno";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return recuperaAlunos(stmt, rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Aluno> selectAlunoPorId(String idAluno) {
		String sql = "SELECT * FROM aluno WHERE id = " + idAluno;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return recuperaAlunos(stmt, rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private List<Aluno> recuperaAlunos(Statement stmt, ResultSet rs) throws SQLException {
		List<Aluno> lista = new ArrayList<Aluno>();

		while (rs.next()) {
			Aluno aluno = new Aluno();

			aluno.setId(rs.getString("id"));
			aluno.setNome(rs.getString("nome"));
			aluno.setNomeMae(rs.getString("nomeMae"));
			aluno.setNomePai(rs.getString("nomePai"));
			aluno.setDataNascimento(rs.getString("dataNascimento"));
			aluno.setDataCadastro(rs.getString("dataCadastro"));

			lista.add(aluno);
		}

		stmt.close();

		return lista;
	}
}
