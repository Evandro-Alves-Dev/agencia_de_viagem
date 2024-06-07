package br.com.velg.agencia_de_viagem.controller;

import br.com.velg.agencia_de_viagem.dtos.DestinationDTO;

import br.com.velg.agencia_de_viagem.service.DestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/destination ")
public class DestinationController {

    private DestinationService destinationService;

    DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PostMapping("/create")
    public ResponseEntity<DestinationDTO> create(@RequestBody DestinationDTO destinationDTO) {
        var destination = destinationService.insert(destinationDTO);
        return new ResponseEntity<>(destination, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DestinationDTO>> findAll() {
        var allDestination = destinationService.findAll();
        return ResponseEntity.ok().body(allDestination);
    }

    @GetMapping("/search")
    public List<DestinationDTO> search(@RequestParam(required = false) String nameDestination, @RequestParam(required = false) String location) throws Exception {
            var allSearch = destinationService.searchByNameOrLocation(nameDestination, location);
            return allSearch;
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Destino> detalhar(@PathVariable Long id) {
//        return destinoService.buscarPorId(id)
//                .map(destino -> new ResponseEntity<>(destino, HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @PutMapping("/{id}/avaliar")
//    public ResponseEntity<Destino> avaliar(@PathVariable Long id, @RequestParam int nota) {
//        try {
//            Destino destinoAvaliacao = destinoService.avaliar(id, nota);
//            return new ResponseEntity<>(destinoAvaliacao, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> excluir(@PathVariable Long id) {
//        destinoService.excluir(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
