package yeo.nuel.lix.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yeo.nuel.lix.sample.response.SampleResponse;

@Service
@RequiredArgsConstructor
public class SampleService implements SearchSampleUseCase {

    private final SamplePort samplePort;
    private final SamplePersistencePort samplePersistencePort;

    @Override
    public SampleResponse getSample() {
        SamplePortResponse sample = samplePort.getSample();
        String sampleName = samplePersistencePort.getSampleName("1");
        return new SampleResponse(sample.getName());
    }
}
