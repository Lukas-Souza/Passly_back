package dev.Client.Repository;

import dev.Client.Entity.TuristaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TuristaRepository {

    private final Map<Long, TuristaEntity> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public boolean existsByCpf(String cpf) {
        return store.values().stream().anyMatch(t -> cpf != null && cpf.equals(t.getCpf()));
    }

    public boolean existsByEmail(String email) {
        return store.values().stream().anyMatch(t -> email != null && email.equals(t.getEmail()));
    }

    public long count() {
        return store.size();
    }

    public TuristaEntity save(TuristaEntity turista) {
        if (turista.getId() == 0) {
            long id = seq.getAndIncrement();
            turista.setId(id);
        }
        store.put(turista.getId(), turista);
        return turista;
    }

    public Optional<TuristaEntity> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public boolean existsById(Long id) {
        return store.containsKey(id);
    }

   
}