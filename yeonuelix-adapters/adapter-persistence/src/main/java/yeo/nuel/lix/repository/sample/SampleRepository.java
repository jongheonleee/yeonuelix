package yeo.nuel.lix.repository.sample;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yeo.nuel.lix.entity.smaple.SampleEntity;
import yeo.nuel.lix.sample.SamplePersistencePort;

@Repository
@RequiredArgsConstructor
public class SampleRepository implements SamplePersistencePort {

    private final SampleJpaRepository sampleJpaRepository;

    @Override
    @Transactional
    public String getSampleName(String id) {
        Optional<SampleEntity> byId = sampleJpaRepository.findById(id);
        return byId.map(SampleEntity::getSampleName).orElseThrow();
    }
}
