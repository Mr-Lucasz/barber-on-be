package barberon.barberonbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import barberon.barberonbe.model.Cupom;


@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {


}
