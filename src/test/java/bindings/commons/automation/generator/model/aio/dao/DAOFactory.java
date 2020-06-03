package bindings.commons.automation.generator.model.aio.dao;

public class DAOFactory {

  public ContractDAO getContractDAO() {

    return new JDBCContractDAO();
  }

  public CustomerDAO getCustomerDAO() {
    return new JDBCCustomerDAO();
  }

  public CreditApplicationDAO getCreditApplicationDAO() {
    return new JDBCCreditApplicationDAO();
  }

  public InvoiceDAO getInvoiceDAO() {
    return new JDBCInvoiceDAO();
  }

  public InvoicingParamsDAO getInvoicingParamsDAO() {
    return new JDBCInvoicingParamsDAO();
  }

  public AllocationDAO getAllocationDAO() {
    return new JDBCAllocationDAO();
  }

  public AbstractCaseDAO getAbstractCaseDAO() {
    return new JDBCAbstractCaseDAO();
  }

  public CreditApplicationCaseDAO getCreditApplicationCaseDAO() {
    return new JDBCCreditApplicationCaseDAO();
  }

  public NewDrawdownCaseDAO getNewDrawdownCaseDAO() {
    return new JDBCNewDrawdownCaseDAO();
  }

  public BankPaymentBatchDAO getBankPaymentBatchDAO() {
    return new JDBCBankPaymentBatchDAO();
  }

  public PaymentDAO getPaymentDAO() {
    return new JDBCPaymentDAO();
  }

  public CollectionOrderDAO getCollectionOrderDAO() {
    return new JDBCCollectionOrderDAO();
  }

  public ExtraServiceSubscriptionDAO getExtraServiceSubscriptionDAO() {
    return new JDBCExtraServiceSubscriptionDAO();
  }

  public CashFlowDAO getCashFlowDAO() {
    return new JDBCCashFlowDAO();
  }

  public UserRegistrationRequestDAO getUserRegistrationRequestDAO() {
    return new JDBCUserRegistrationRequestDAO();
  }

  public ContractTerminationNoticeDAO getContractTerminationNoticeDAO() {
    return new JDBCContractTerminationNoticeDAO();
  }

  public ProductDAO getProductDAO() {
    return new JDBCProductDAO();
  }

  public PaymentPlanDAO getPaymentPlanDAO() {
    return new JDBCPaymentPlanDAO();
  }

  public PaymentPlanRowDAO getPaymentPlanRowDAO() {
    return new JDBCPaymentPlanRowDAO();
  }

  public DocumentDeliveryDAO getDocumentDeliveryDAO() {
    return new JDBCDocumentDeliveryDAO();
  }

  public ProvidentDataDAO getProvidentDataDAO() {
    return new JDBCProvidentDataDAO();
  }

  public CustomerMetadataDAO getCustomerMetadataDAO() {
    return new JDBCCustomerMetadataDAO();
  }

  public CollectionOrderUpdateDAO getCollectionOrderUpdateDAO() {
    return new JDBCCollectionOrderUpdateDAO();
  }

  public ContractDrawCampaignDAO getContractDrawCampaignDAO() {
    return new JDBCContractDrawCampaignDAO();
  }

  public ExtResponseDAO getExtResponseDAO() {
    return new JDBCExtResponseDAO();
  }

  public ScoringResultContainerDAO getScoringResultContainerDAO() {
    return new JDBCScoringResultContainerDAO();
  }
}
