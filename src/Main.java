
import service.AlunoService;
import service.ProfessorService;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import entities.Aluno;
import entities.Professor;

public class Main {

	// scanner para entrada de dados
	private static Scanner entrada = new Scanner(System.in);

	// classe responsavel por manipular alunos
	private static AlunoService alunoService = new AlunoService();

	// classe responsavel por manipular Professores
	private static ProfessorService ProfessorService = new ProfessorService();

	public static void main(String[] args) throws SQLException {

		// flag que controla execucao do programa
		boolean continuar = true;

		while (continuar) {
			exibeMenu();

			switch (entrada.nextLine()) {
				case "1":
					limpaTela();
					adicionarAluno();
					pressioneQualquerTecla();
					break;
				case "2":
					limpaTela();
					listaAlunos();
					pressioneQualquerTecla();
					break;
				case "3":
					limpaTela();
					buscarPorId();
					pressioneQualquerTecla();
					break;
				case "4":
					limpaTela();
					atualizaAluno();
					pressioneQualquerTecla();
					break;
				case "5":
					limpaTela();
					deletaPorId();
					pressioneQualquerTecla();
					break;
				case "6":
					limpaTela();
					adicionarProfessor();
					pressioneQualquerTecla();
					break;
				case "7":
					limpaTela();
					System.out.println("Tchau... :)");
					pressioneQualquerTecla();
					continuar = false;
					break;
				default:
					limpaTela();
					System.out.println("Por favor, selecione uma opção válida.");
					pressioneQualquerTecla();
					break;
			}

			limpaTela();
		}

		entrada.close();
	}

	private static void exibeMenu() {
		System.out.println("Bem vindo, por favor digite uma opção válida: ");
		System.out.println("1 - Cadastrar aluno");
		System.out.println("2 - Listar alunos");
		System.out.println("3 - Buscar aluno por id");
		System.out.println("4 - Atualizar Aluno");
		System.out.println("5 - Deletar Aluno");
		System.out.println("6 - Cadastrar Professor");
		System.out.println("7 - Sair");
	}

	private static void adicionarAluno() {
		Aluno aluno = new Aluno();

		System.out.println("Informe o nome do aluno:");
		String resposta = entrada.nextLine();
		aluno.setNome(resposta);

		System.out.println("Informe o nome da Mae do Aluno:");
		resposta = entrada.nextLine();
		aluno.setNomeMae(resposta);

		System.out.println("Informe o nome do Pai do Aluno:");
		resposta = entrada.nextLine();
		aluno.setNomePai(resposta);

		System.out.println("Informe a data de nascimento do aluno (Ex.: 22/02/2000):");
		resposta = entrada.nextLine();
		aluno.setDataNascimento(resposta);

		alunoService.validaAluno(aluno);
	}

	private static void adicionarProfessor() {
		Professor Professor = new Professor();

		System.out.println("Informe o nome do Professor:");
		String resposta = entrada.nextLine();
		Professor.setNome(resposta);

		System.out.println("Informe o nome da Mae do Professor:");
		resposta = entrada.nextLine();
		Professor.setNomeMae(resposta);

		System.out.println("Informe o nome do Pai do Professor:");
		resposta = entrada.nextLine();
		Professor.setNomePai(resposta);

		System.out.println("Informe a data de nascimento do Professor (Ex.: 22/02/2000):");
		resposta = entrada.nextLine();
		Professor.setDataNascimento(resposta);

		System.out.println("Informe o id da turma em que o Professor está cadastrado (Ex.: 22/02/2000):");
		resposta = entrada.nextLine();
		Professor.setIdTurma(resposta);

		ProfessorService.validaProfessor(Professor);
	}

	private static void atualizaAluno() {
		Aluno Aluno = new Aluno();

		System.out.println("Informe o id do Aluno:");
		String resposta = entrada.nextLine();
		Aluno.setId(resposta);

		System.out.println("Informe o nome do Aluno:");
		resposta = entrada.nextLine();
		Aluno.setNome(resposta);

		System.out.println("Informe o nome da Mae do Aluno:");
		resposta = entrada.nextLine();
		Aluno.setNomeMae(resposta);

		System.out.println("Informe o nome do Pai do Aluno:");
		resposta = entrada.nextLine();
		Aluno.setNomePai(resposta);

		System.out.println("Informe a data de nascimento do Aluno (Ex.: 22/02/2000):");
		resposta = entrada.nextLine();
		Aluno.setDataNascimento(resposta);

		AlunoService.validaAlunoUpdate(Aluno);
	}

	private static void atualizaProfessor() {
		Professor Professor = new Professor();

		System.out.println("Informe o id do Professor:");
		String resposta = entrada.nextLine();
		Professor.setId(resposta);

		System.out.println("Informe o nome do Professor:");
		resposta = entrada.nextLine();
		Professor.setNome(resposta);

		System.out.println("Informe o nome da Mae do Professor:");
		resposta = entrada.nextLine();
		Professor.setNomeMae(resposta);

		System.out.println("Informe o nome do Pai do Professor:");
		resposta = entrada.nextLine();
		Professor.setNomePai(resposta);

		System.out.println("Informe a data de nascimento do Professor (Ex.: 22/02/2000):");
		resposta = entrada.nextLine();
		Professor.setDataNascimento(resposta);

		ProfessorService.validaProfessorUpdate(Professor);
	}

	private static void listaAlunos() {
		List<Aluno> listaAlunos = AlunoService.listaAlunos();
		printaAlunos(listaAlunos);
	}

	private static void listarProfessor() {
		List<Professor> listaProfessor = ProfessorService.listaProfessor();
		printaProfessor(listaProfessor);
	}

	private static void buscarPorId() {
		System.out.print("Informe o id do aluno: ");
		String idBusca = entrada.nextLine();
		List<Aluno> listaAlunos = alunoService.listaAlunoPorId(idBusca);
		printaAlunos(listaAlunos);
	}

	private static void deletaPorId() {
		System.out.print("Informe o id do aluno que deseja excluir: ");
		String idBusca = entrada.nextLine();
		try {
			alunoService.deletaAlunoPorId(idBusca);
			System.out.println("\n\n Registro deletado com sucesso!");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void printaAlunos(List<Aluno> listaAlunos) {
		Iterator<Aluno> it = listaAlunos.iterator();
		int i = 0;
		while (it.hasNext()) {
			Aluno aluno = it.next();
			if (i == 0) {
				System.out.printf("%-4s\t", "id");
			}
			if (i == 0) {
				System.out.printf("%-10s\t", "Nome");
			}
			if (i == 0) {
				System.out.printf("%-10s\t", "Data de Nascimento");
			}
			if (i == 0) {
				System.out.println();
			}
			if (i == 0) {
				for (int x = 0; x < 50; x++) {
					System.out.print("=");
				}
			}
			if (i == 0) {
				System.out.println();
			}
			System.out.printf("%-4s\t", aluno.getId() + "|");
			System.out.printf("%-10s\t", aluno.getNome());
			System.out.printf("%-10s\t", aluno.getDataNascimento());
			System.out.println();
			i++;
		}
		for (int x = 0; x < 50; x++) {
			System.out.print("=");
		}
		System.out.println();
		System.out.printf("%-4s\t", "id");
		System.out.printf("%-10s\t", "Nome");
		System.out.printf("%-10s\t", "Data de Nascimento");

	}

	private static void printaProfessor(List<Professor> listaProfessor) {
		Iterator<Professor> it = listaProfessor.iterator();
		int i = 0;
		while (it.hasNext()) {
			Professor Professor = it.next();
			if (i == 0) {
				System.out.printf("%-4s\t", "id");
			}
			if (i == 0) {
				System.out.printf("%-10s\t", "Nome");
			}
			if (i == 0) {
				System.out.printf("%-10s\t", "Data de Nascimento");
			}
			if (i == 0) {
				System.out.println();
			}
			if (i == 0) {
				for (int x = 0; x < 50; x++) {
					System.out.print("=");
				}
			}
			if (i == 0) {
				System.out.println();
			}
			System.out.printf("%-4s\t", Professor.getId() + "|");
			System.out.printf("%-10s\t", Professor.getNome());
			System.out.printf("%-10s\t", Professor.getDataNascimento());
			System.out.println();
			i++;
		}
		for (int x = 0; x < 50; x++) {
			System.out.print("=");
		}
		System.out.println();
		System.out.printf("%-4s\t", "id");
		System.out.printf("%-10s\t", "Nome");
		System.out.printf("%-10s\t", "Data de Nascimento");

	}

	private static void limpaTela() {
		for (int i = 0; i < 100; i++) {
			System.out.println("\r\n");
		}
	}

	private static void pressioneQualquerTecla() {
		System.out.println("\n\nPressione qualquer tecla para continuar...");
		entrada.nextLine();
	}
}
