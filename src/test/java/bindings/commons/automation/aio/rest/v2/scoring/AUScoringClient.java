package bindings.commons.automation.aio.rest.v2.scoring;

import com.ipfdigital.automation.aio.rest.dto.au.ScoringAUDTO;
import feign.RequestLine;

public interface AUScoringClient {
  @RequestLine("POST /application-scoring-au/init")
  void scoringInit(ScoringAUDTO data);
}
