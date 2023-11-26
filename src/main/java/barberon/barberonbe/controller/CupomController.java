package barberon.barberonbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import barberon.barberonbe.DTO.CupomDTO;
import barberon.barberonbe.model.Cupom;
import barberon.barberonbe.service.CupomService;

@RequestMapping("/api/cupons")
@CrossOrigin(origins = "http://localhost:4000")

public class CupomController {
	@Autowired
	private CupomService cupomService;

	@PostMapping
	public CupomDTO save(@RequestBody Cupom cupom) {
		return cupomService.save(cupom);
	}

	@GetMapping
	public List<Cupom> findAll() {
		return cupomService.findAll();
	}

	@GetMapping("/{id}")
	public Cupom getById(@PathVariable long id) {
		return cupomService.findById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CupomDTO> update(@PathVariable Long id, @RequestBody Cupom cupom) {
		CupomDTO updatedCupom = cupomService.update(id, cupom);
		return new ResponseEntity<>(updatedCupom, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id) {
		cupomService.deleteById(id);
	}

}
