package barberon.barberonbe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberon.barberonbe.model.Status;
import barberon.barberonbe.repository.StatusRepository;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    @Autowired
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
        Status existingStatus = statusRepository.findById(status.getStatusId()).orElse(null);
        existingStatus.setStatusId(status.getStatusId());
        existingStatus.setStatusNome(status.getStatusNome());
        return statusRepository.save(existingStatus);
    }
}
