package dev.CheckIn.Service;
import org.springframework.web.bind.annotation.RestController;

import dev.CheckIn.Models.CheckInModel;
import dev.Client.Repository.IVisitacoesRepositoryByTurist;
import dev.Client.Repository.TuristaRepository;

import dev.Instituicao.Repository.Interface.ILocalTuristicoRepository;
import dev.Instituicao.Repository.Interface.IVisitacoesRepository;

@RestController
public class CheckInService {
    
    private final ILocalTuristicoRepository ILocalTuristicoRepository;

    static IVisitacoesRepository iVisitacoesRepository;
    static IVisitacoesRepositoryByTurist iVisitacoesRepositoryByTurist;



    public CheckInService(
            ILocalTuristicoRepository localTuristicoRepository, TuristaRepository TuristaRepository) {
        this.ILocalTuristicoRepository = localTuristicoRepository;

    }

    public String CheckIn(
        Long IdLocal, 
        long IdUser,
        CheckInModel heckInModel
    ){
        try {
        
        int lineEfect = ILocalTuristicoRepository.registrarVisitacao((Short)heckInModel.getNota(), IdLocal);

        return "Check-in realizado com sucesso. Total de: "+ lineEfect+ " alteradas.. Notas"+heckInModel.getNota();
        } catch (Exception e) {
            return "Erro: "+e.getMessage();
        }
        
        
        

    }
    
}
