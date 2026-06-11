package dev.Instituicao.Controllers;

import dev.Client.Services.TuristaService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.Instituicao.DTO.Response.VisitacaoResponse;
import dev.Instituicao.Entity.LocalTuristicoEntity;
import dev.Instituicao.Entity.VisitacaoEntity;
import dev.Instituicao.Services.LocalTuristicoService;

@RestController
@RequestMapping("/localTuristico")
public class LocalTuristicoController {

    private final LocalTuristicoService service;


    public LocalTuristicoController(LocalTuristicoService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<String> criar(
        @RequestBody LocalTuristicoEntity localTuristicoEntity) {
         try {
            service.criar( localTuristicoEntity);
            return new ResponseEntity<>("Sucess", HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editar(
        @PathVariable long id,
        @RequestBody LocalTuristicoEntity localTuristicoMod) {
        try {
            service.editar(id, localTuristicoMod);
            return new ResponseEntity<>("Sucess", HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(
        @PathVariable long id) {
        try {
        service.deletar(id);
        return new ResponseEntity<>("Sucesso", HttpStatus.NO_CONTENT);
            
        } catch (Exception e) {

        return new ResponseEntity<>("OCORREU UM ERRO: "+e, HttpStatus.BAD_REQUEST);

        }    
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalTuristicoEntity> buscarPorId(
        @PathVariable long id) {
        return new ResponseEntity<>(service.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/visitas")
    public List<VisitacaoResponse> listarVisitas(
        @PathVariable long id) {
            
        return service.listarVisistas(id);
    }
}