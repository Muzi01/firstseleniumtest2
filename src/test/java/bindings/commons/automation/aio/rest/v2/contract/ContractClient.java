package bindings.commons.automation.aio.rest.v2.contract;

import com.ipfdigital.automation.aio.rest.dto.ChangeCreditLineDTO;
import com.ipfdigital.automation.aio.rest.dto.ChangeCreditLinePredictionExtDTO;
import com.ipfdigital.automation.aio.rest.dto.DrawDTO;
import com.ipfdigital.automation.aio.rest.dto.DrawSelectionsDTO;
import com.ipfdigital.automation.aio.rest.dto.DueDateSelectionDTO;
import com.ipfdigital.automation.aio.rest.dto.PreparePaymentRequestDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductSelectionDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductSelectionWithFirstDrawtDTO;
import feign.Param;
import feign.RequestLine;

import java.util.List;
import java.util.Map;

public interface ContractClient {
  @RequestLine("PUT /contracts/{contractId}/draw")
  void draw(DrawDTO draw, @Param("contractId") long contractId);

  @RequestLine("PUT /contracts/{contractId}/extra-services/subscriptions/duedate")
  void changeDueDate(DueDateSelectionDTO dueDateSelection, @Param("contractId") long contractId);

  @RequestLine("PUT /contracts/{contractId}/extra-services/subscriptions/paymentHoliday")
  void paymentHoliday(@Param("contractId") long contractId);

  @RequestLine("PUT /contracts/{contractId}/extra-services/subscriptions/CALMA")
  void activateCalma(@Param("contractId") long contractId);

  @RequestLine("GET /contracts/{contractId}/upgrade/availablePrincipal")
  void getUpgradeAvailablePrincipal(@Param("contractId") long contractId);

  @RequestLine("GET /contracts/{contractId}/upsell/availablePrincipal")
  void getUpsellAvailablePrincipal(@Param("contractId") long contractId);

  @RequestLine("GET /contracts/{contractId}/upgrade/application")
  void getUpgradeApplication(@Param("contractId") long contractId);

  @RequestLine("GET /contracts/{contractId}/upsell/application")
  void getUpsellApplication(@Param("contractId") long contractId);

  @RequestLine("GET /contracts/{contractId}/extra-services/predicted/changeCreditLine?upgrade=true")
  List<ChangeCreditLinePredictionExtDTO> getPredictedProduct(@Param("contractId") long contractId);

  @RequestLine("GET /contracts/{contractId}/upgrade/products")
  List<ProductDTO> getUpdateProducts(@Param("contractId") long contractId);

  @RequestLine("GET /contracts/{contractId}/upsell/products")
  List<ProductDTO> getUpsellProducts(@Param("contractId") long contractId);

  @RequestLine("GET /contracts/{contractId}/upgrade/draw-selections?maturity={maturity}&productId={productId}")
  Map<String, List<DrawSelectionsDTO>> getDrawSelection(@Param("contractId") long contractId,
      @Param("maturity") int maturityPeriods, @Param("productId") long productId);

  @RequestLine("PUT /contracts/{contractId}/extra-services/subscriptions/changeCreditLine")
  void changeCreditLine(ChangeCreditLineDTO changeCreditLineDTO,
      @Param("contractId") long contractId);

  @RequestLine("PUT /contracts/{contractId}/upgrade/product")
  void updateProduct(ProductSelectionWithFirstDrawtDTO productSelectionWithFirstDrawtDTO,
      @Param("contractId") long contractId);

  @RequestLine("PUT /contracts/{contractId}/upsell/product")
  void upsellProduct(ProductSelectionDTO productSelectionDTO, @Param("contractId") long contractId);

  @RequestLine("POST /contracts/{contractId}/upgrade/upgrade")
  void upgradeUpgrade(@Param("contractId") long contractId);

  @RequestLine("POST /contracts/{contractId}/upsell/upsell")
  void upsellUpsell(@Param("contractId") long contractId);

  @RequestLine("POST /contracts/{contractId}/recurrentPaymentMethod/activate")
  void activateRecurrentPayment(@Param("contractId") Long contractId);

  @RequestLine("POST /contracts/{contractId}/invoices/{invoiceId}/prepare-payment/liberto")
  void preparePayment(PreparePaymentRequestDTO preparePaymentRequest,
      @Param("contractId") long contractId, @Param("invoiceId") long invoiceId);
}
