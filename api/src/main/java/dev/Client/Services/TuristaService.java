package dev.Client.Services;

import dev.Client.Dto.TuristaDto;
import dev.Client.Entity.TuristaEntity;
import dev.Client.Repository.TuristaRepository;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TuristaService {

    private final TuristaRepository turistaRepository;
    private final BasicPasswordEncoder passwordEncoder;

    public TuristaService(TuristaRepository turistaRepository) {
        this.turistaRepository = turistaRepository;
        this.passwordEncoder = new BasicPasswordEncoder();
    }

    public long contarTotal() {
        return turistaRepository.count();
    }

    public TuristaDto.Response cadastrar(TuristaDto.Request dto) {
    
        TuristaEntity turista = new TuristaEntity();
        turista.setNome(dto.getNome());
        turista.setCpf(dto.getCpf());
        turista.setEmail(dto.getEmail());
        turista.setTelefone(dto.getTelefone());
        turista.setLogin(dto.getLogin());
        turista.setDataNascimento(dto.getDataNascimento());
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
        TuristaEntity turista = turistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turista não encontrado com id: " + id));
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

// Simple fallback password encoder using SHA-256.
class BasicPasswordEncoder {
    public String encode(String raw) {
        if (raw == null) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(raw.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String h = Integer.toHexString(0xff & b);
                if (h.length() == 1) hex.append('0');
                hex.append(h);
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to encode password", e);
        }
    }
}
