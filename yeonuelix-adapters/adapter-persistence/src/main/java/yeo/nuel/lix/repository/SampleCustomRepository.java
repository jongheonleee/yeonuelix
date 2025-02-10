package yeo.nuel.lix.repository;

import java.util.List;
import yeo.nuel.lix.entity.SampleEntity;

public interface SampleCustomRepository {
    List<SampleEntity> findAllByAbc();
}
