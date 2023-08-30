package br.ifsp.edu.gabriel;

import br.ifsp.edu.gabriel.persistence.AlunoDAO;
import br.ifsp.edu.gabriel.util.JPAUtil;
import jakarta.persistence.EntityManager;

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
        }while(choice != 6);

    }
}