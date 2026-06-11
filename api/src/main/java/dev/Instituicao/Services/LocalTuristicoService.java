package dev.Instituicao.Services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import dev.Instituicao.DTO.Response.VisitacaoResponse;
import dev.Instituicao.Entity.LocalTuristicoEntity;
import dev.Instituicao.Entity.VisitacaoEntity;
import dev.Instituicao.Repository.Interface.ILocalTuristicoRepository;


@Service
public class LocalTuristicoService {

    private final ILocalTuristicoRepository _localTurrisRepository;
    public LocalTuristicoService( ILocalTuristicoRepository ILocalTuristicoRepository){
        this._localTurrisRepository = ILocalTuristicoRepository;
    }



     public String criar( LocalTuristicoEntity localTuristicoEntity) {
        try {
        _localTurrisRepository.save(localTuristicoEntity);
            return "Sucess";
        } catch (Exception e) {
            return "Ocorreu algum erro ao adicionar o elemento";
        }

     }

     public LocalTuristicoEntity editar(long id, LocalTuristicoEntity localTuristicoEntity ) {

        LocalTuristicoEntity local = 
                _localTurrisRepository.findById(id).orElse(null);

        if (local == null) {
            return null;
         }

        localTuristicoEntity.setId(local.getId());
        return _localTurrisRepository.save(localTuristicoEntity);
        
}
public void deletar(long id) {
    _localTurrisRepository.deleteById(id);
}

public LocalTuristicoEntity buscarPorId(long id) {
    return _localTurrisRepository.findById(id).orElse(null);
}

public List<VisitacaoResponse> listarVisistas(Long id) {
    List<VisitacaoResponse> visitacaoDto = new ArrayList<VisitacaoResponse>();
    List<VisitacaoEntity> visitacaoEntities = _localTurrisRepository.findVisitacoesByLocalId(id);
    for (VisitacaoEntity visitacaoEntity : visitacaoEntities) {
        VisitacaoResponse response = new VisitacaoResponse(visitacaoEntity.getDataVisita().toString(), visitacaoEntity.getNotaVisitacao(), visitacaoEntity.getId().toString());
        visitacaoDto.add(response);
    }
    return visitacaoDto;
}
}