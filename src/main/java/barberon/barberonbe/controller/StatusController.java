package barberon.barberonbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import barberon.barberonbe.model.Status;
import barberon.barberonbe.service.StatusService;

@RestController
@RequestMapping("/api/status")
@CrossOrigin(origins = "http://localhost:4000")
public class StatusController {

    @Autowired
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping
    public Status createStatus(@RequestBody Status status) {
        return statusService.saveStatus(status);
    }

    @GetMapping
    public List<Status> getAllStatus() {
        return statusService.getAllStatus();
    }

}
