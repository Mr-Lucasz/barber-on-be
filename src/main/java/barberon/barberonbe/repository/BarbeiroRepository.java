package barberon.barberonbe.repository;

import barberon.barberonbe.model.Barbeiro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> { 
    Barbeiro findByEmail(String email);
    // id   
    Optional<Barbeiro> findById(Long id);


}