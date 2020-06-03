package bindings.commons.automation.aio.rest.v2.creditapplication;

import feign.RequestLine;

public interface EECreditApplicationClient extends GenericCreditApplicationClient {
  @RequestLine("GET /credit-application-ee/auto-approval-status")
  AutoApprovalStatusDTO autoApprovalStatus();
}
