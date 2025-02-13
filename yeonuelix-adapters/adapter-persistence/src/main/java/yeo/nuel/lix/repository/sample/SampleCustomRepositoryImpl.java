package yeo.nuel.lix.repository.sample;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yeo.nuel.lix.entity.QSampleEntity;
import yeo.nuel.lix.entity.smaple.SampleEntity;

@Repository
@RequiredArgsConstructor
public class SampleCustomRepositoryImpl implements SampleCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<SampleEntity> findAllByAbc() {
        return jpaQueryFactory.selectFrom(QSampleEntity.sampleEntity)
                              .fetch();
    }
}
