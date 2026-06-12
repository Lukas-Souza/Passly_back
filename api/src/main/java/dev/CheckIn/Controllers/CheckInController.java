package dev.CheckIn.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import dev.CheckIn.Models.CheckInModel;
import dev.CheckIn.Service.CheckInService;
import dev.Err.Response.ErroResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
class CheckInController {
    private final CheckInService checkInService;

    public CheckInController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @PostMapping("/check-in/{idLocal}/{idUser}")
    public ResponseEntity<String> checkInByIdLocalTuristico(@PathVariable("idLocal") long IdLocal,@PathVariable long idUser, @RequestBody CheckInModel checkInModel) {
        try {
            
            return ResponseEntity.status(HttpStatus.CREATED).body(checkInService.CheckIn(IdLocal, idUser, checkInModel));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErroResponse(500, e.getMessage()).toString());
        }
    }
}
