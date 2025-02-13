package yeo.nuel.lix.repository.sample;

import java.util.List;
import yeo.nuel.lix.entity.smaple.SampleEntity;

public interface SampleCustomRepository {
    List<SampleEntity> findAllByAbc();
}
