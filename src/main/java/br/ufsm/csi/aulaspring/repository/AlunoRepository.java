package br.ufsm.csi.aulaspring.repository;

import br.ufsm.csi.aulaspring.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("SELECT a FROM Aluno a WHERE a.status = 'ATIVO' ")
    public List<Aluno> findByStatusAtivo();

    @Query("SELECT i FROM Aluno i WHERE i.status = 'INATIVO' ")
    public List<Aluno> findByStatusInativo();

    public List<Aluno> findByNomeContainingIgnoreCase(String nome);

}
