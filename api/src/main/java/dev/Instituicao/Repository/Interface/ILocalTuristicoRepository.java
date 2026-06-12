package dev.Instituicao.Repository.Interface;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.Instituicao.Entity.LocalTuristicoEntity;
import dev.Instituicao.Entity.VisitacaoEntity;
import jakarta.transaction.Transactional;

public interface ILocalTuristicoRepository extends JpaRepository<LocalTuristicoEntity, Long> {
    @Query(value = """
    SELECT * FROM passly.visitacao
    WHERE id = :idLocal
    """, nativeQuery = true)
List<VisitacaoEntity> findVisitacoesByLocalId(@Param("idLocal") Long idLocal);
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO passly.visitacao
        (datavisita, notaavaliacao, id)
        VALUES (
            NOW(),
            :nota,
            :idLocal
        )
        """, nativeQuery = true)
    int registrarVisitacao(
            @Param("nota") Short nota,
            @Param("idLocal") Long idLocal);
}
