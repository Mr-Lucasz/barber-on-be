package barberon.barberonbe.repository;

import barberon.barberonbe.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}