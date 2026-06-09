package dev.Client.Services;

import dev.Client.Dto.TuristaDto;
import dev.Client.Entity.TuristaEntity;
import dev.Client.Repository.TuristaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TuristaService {

    private final TuristaRepository turistaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public TuristaService(TuristaRepository turistaRepository) {
        this.turistaRepository = turistaRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public long contarTotal() {
        return turistaRepository.count();
    }

    public TuristaDto.Response cadastrar(TuristaDto.Request dto) {
        if (turistaRepository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("Já existe um turista com esse CPF.");
        }
        if (turistaRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Já existe um turista com esse e-mail.");
        }

        TuristaEntity turista = new TuristaEntity(
                dto.getNome(),
                dto.getCpf(),
                dto.getEmail(),
                dto.getTelefone(),
                dto.getPassaporte(),
                dto.getDataNascimento()
        );
        turista.setSenha(passwordEncoder.encode(dto.getSenha()));

        return new TuristaDto.Response(turistaRepository.save(turista));
    }

    public TuristaDto.Response atualizar(Long id, TuristaDto.Request dto) {
        TuristaEntity turista = turistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turista não encontrado com id: " + id));

        turista.setNome(dto.getNome());
        turista.setEmail(dto.getEmail());
        turista.setTelefone(dto.getTelefone());
        turista.setDataNascimento(dto.getDataNascimento());

        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            turista.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        return new TuristaDto.Response(turistaRepository.save(turista));
    }

    public void deletar(Long id) {
        if (!turistaRepository.existsById(id)) {
            throw new RuntimeException("Turista não encontrado com id: " + id);
        }
        turistaRepository.deleteById(id);
    }

    public TuristaDto.Response buscarPorId(Long id) {
        TuristaEntity turista = turistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turista não encontrado com id: " + id));
        return new TuristaDto.Response(turista);
    }

    public List<TuristaDto.HistoricoResponse> listarLugaresVisitados(Long id) {
        TuristaEntity turista = turistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turista não encontrado com id: " + id));
        throw new RuntimeException("Histórico não disponível para Turista. Verifique a implementação de TuristaEntity.");
    }
}
