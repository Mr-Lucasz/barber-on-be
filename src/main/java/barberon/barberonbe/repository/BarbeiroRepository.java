package barberon.barberonbe.repository;

import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.model.Barbeiro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {


}