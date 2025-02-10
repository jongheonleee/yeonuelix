package yeo.nuel.lix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yeo.nuel.lix.entity.SampleEntity;

public interface SampleJpaRepository extends JpaRepository<SampleEntity, String>, SampleCustomRepository {

}
