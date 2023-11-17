package barberon.barberonbe.service;
import java.util.List;
import org.springframework.stereotype.Service;
import barberon.barberonbe.model.Status;
import barberon.barberonbe.repository.StatusRepository;

@Service
public class StatusService {
    private final StatusRepository statusRepository;


    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

   
    //edite status
    public Status updateStatus(Status status) {
        Status existingStatus = statusRepository.findById(status.getId()).orElse(null);
        existingStatus.setId(status.getId());
        existingStatus.setStatusNome(status.getStatusNome());
        return statusRepository.save(existingStatus);
    }

    public Status getStatusById(Long id) {
        return statusRepository.findById(id).orElse(null);
    }
}
