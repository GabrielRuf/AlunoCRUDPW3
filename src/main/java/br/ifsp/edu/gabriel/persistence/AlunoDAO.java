package br.ifsp.edu.gabriel.persistence;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import br.ifsp.edu.gabriel.model.Aluno;

import java.util.List;

public class AlunoDAO {
    private EntityManager Manager;
    public AlunoDAO(EntityManager Manager){
        this.Manager = Manager;
    }
    public void cadastrar(Aluno aluno) {
        Manager.getTransaction().begin();
        Manager.persist(aluno);
        Manager.getTransaction().commit();
        System.out.println("Cadastrado!");
    }
    public void deletar(Integer id){
        Manager.getTransaction().begin();
        Aluno aluno = buscarPorId(id);
        Manager.remove(aluno);
        Manager.getTransaction().commit();
        System.out.println("Deletado!");
    }
    public void editar(Aluno aluno){
        Manager.getTransaction().begin();
        Manager.merge(aluno);
        Manager.getTransaction().commit();
    }
    public Aluno buscarPorId(Integer id) {
        return Manager.find(Aluno.class, id);
    }
    public List<Aluno> buscarTodos() {
        String jpql = "SELECT a FROM Aluno a";
        return Manager.createQuery(jpql, Aluno.class).getResultList();
    }
    public Aluno buscarUnicoPorNome(String nome) throws NoResultException {
        String jpql = "SELECT a FROM Aluno a WHERE a.nome = :n";
        return Manager.createQuery(jpql, Aluno.class)
                .setParameter("n", nome)
                .getSingleResult();
    }

}
