package yeo.nuel.lix.repository.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import yeo.nuel.lix.entity.smaple.SampleEntity;

public interface SampleJpaRepository extends JpaRepository<SampleEntity, String>, SampleCustomRepository {

}
