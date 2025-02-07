package yeo.nuel.lix.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yeo.nuel.lix.sample.response.SampleResponse;

@Service
@RequiredArgsConstructor
public class SampleService implements SearchSampleUseCase {

    private final SamplePort samplePort;

    @Override
    public SampleResponse getSample() {
        SamplePortResponse sample = samplePort.getSample();
        return new SampleResponse(sample.getName());
    }
}
