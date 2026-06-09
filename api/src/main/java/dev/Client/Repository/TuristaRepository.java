package dev.Client.Repository;

import dev.Client.Entity.TuristaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public interface TuristaRepository extends JpaRepository<TuristaEntity, Long>{


}