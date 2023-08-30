package br.ifsp.edu.gabriel;

import br.ifsp.edu.gabriel.model.Aluno;
import br.ifsp.edu.gabriel.persistence.AlunoDAO;
import br.ifsp.edu.gabriel.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManager Manager = JPAUtil.getEntityManager();
        AlunoDAO DAO = new AlunoDAO(Manager);
        int choice = 7;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\n** CADASTRO DE ALUNOS **");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Excluir aluno");
            System.out.println("3 - Alterar aluno");
            System.out.println("4 - Buscar aluno pelo nome");
            System.out.println("5 - Listar alunos (com status aprovação)");
            System.out.println("6 - FIM\n");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    System.out.println("Digite nome:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite ra:");
                    String ra = scanner.nextLine();
                    System.out.println("Digite email:");
                    String email = scanner.nextLine();
                    System.out.println("Digite nota1:");
                    Double nota1 = scanner.nextDouble();
                    System.out.println("Digite nota2:");
                    Double nota2 = scanner.nextDouble();
                    System.out.println("Digite nota3:");
                    Double nota3 = scanner.nextDouble();
                    Aluno aluno = new Aluno(nome,ra,email,nota1,nota2,nota3);
                    DAO.cadastrar(aluno);
                    break;
                case 2:
                    System.out.println("Digite o id do aluno:");
                    int id = scanner.nextInt();
                    try{
                        DAO.deletar(id);
                    }catch (Exception exception){
                        System.out.println("ID incorreto!");
                    }

                    break;
                case 3:
                    System.out.println("Digite o id do aluno:");
                    int idAlterar = scanner.nextInt();
                    Aluno alunoAlterar = DAO.buscarPorId(idAlterar);
                    scanner.nextLine();
                    System.out.println("Digite nome:");
                    alunoAlterar.setNome(scanner.nextLine());
                    System.out.println("Digite ra:");
                    alunoAlterar.setRa(scanner.nextLine());
                    System.out.println("Digite email:");
                    alunoAlterar.setEmail(scanner.nextLine());
                    System.out.println("Digite nota1:");
                    alunoAlterar.setNota1(scanner.nextDouble());
                    System.out.println("Digite nota2:");
                    alunoAlterar.setNota2(scanner.nextDouble());
                    System.out.println("Digite nota3:");
                    alunoAlterar.setNota3(scanner.nextDouble());
                    DAO.editar(alunoAlterar);
                    break;
                case 4:
                    System.out.println("Digite nome:");
                    String nomeBuscar = scanner.nextLine();
                    try{
                        Aluno alunoBuscar = DAO.buscarUnicoPorNome(nomeBuscar);
                    }catch (Exception NoResultException){
                        System.out.println("Aluno não encontrado");
                    }
                    break;
                case 5:
                    List<Aluno> alunos = DAO.buscarTodos();
                    for (Aluno a:alunos) {
                        System.out.println(a);
                    }
                    break;
                default:
                    break;
            }
        }while(choice != 6);

    }
}