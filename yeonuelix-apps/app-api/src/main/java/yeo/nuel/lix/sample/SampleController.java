package yeo.nuel.lix.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yeo.nuel.lix.sample.response.SampleResponse;

@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SearchSampleUseCase searchSampleUseCase;

    @GetMapping("/api/v1/sample")
    public SampleResponse getSample() {
//        return null;
        return searchSampleUseCase.getSample();
    }
}
