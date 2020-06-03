package bindings.commons.automation.salesforce;

public enum SFQuery {
  GET_ACCOUNT_ID("SELECT Id FROM Account WHERE AccountNumber = '%s'"),
  GET_ACCOUNT_ID_WHERE_ACCOUNT_NUM_AND_DEBIT_CARD("SELECT Id FROM Account WHERE SSN__c = '%s' AND DebitCardNumber__c = '%s'"),
  GET_ACCOUNT_BY_SSN("SELECT Id, AccountNumber, Bank_Account__c, DateOfBirth__c, FirstName, Gender__c, IdentificationStatus__c, LastName, Marketing_permission__c, Phone, SSN__c, SecondLastName__c FROM Account WHERE SSN__c = '%s'"),
  GET_TASK_ID("SELECT Id FROM Task WHERE Account_Number__c = '%s' AND Subject = '%s' ORDER BY CreatedDate DESC"),
  GET_TASK_ID_WHERE_STATUS("SELECT Id FROM Task WHERE Account_Number__c = '%s' AND Subject = '%s' AND Status = '%s' ORDER BY CreatedDate DESC"),
  GET_CASE_ID("SELECT Id FROM Case WHERE Account_Number__c = '%s' AND Type = '%s' ORDER BY CreatedDate DESC"),
  GET_CASE_ID_BY_NAME("SELECT Id FROM Case WHERE Account_Ssn__c = '%s' AND RecordType.Name = '%s' ORDER BY CreatedDate DESC"),
  GET_CASE_STATUS("SELECT Status FROM Case WHERE Account_Number__c = '%s' ORDER BY CreatedDate DESC"),
  GET_CONTRACT_ID("SELECT Id FROM Contract WHERE Account_Number__c = '%s'"),
  GET_MCB_CREDIT_APP_ID("SELECT Id FROM MCBCreditApplication__c WHERE Account_Number__c = '%s'"),
  GET_MCB_PRODUCT_ID("SELECT Id FROM MCBProduct__c WHERE BackendId__c = '%s'"),
  GET_PRINCIPAL("SELECT Product_amount__c FROM MCBCreditApplication__c WHERE Account_Number__c = '%s'"),
  SF_TASK_STATUS_QUERY("SELECT Status, Id, Subject FROM Task WHERE Account_Number__c = '%s' and Status not in ('Accepted', 'Close failed') ORDER BY LastModifiedDate ASC LIMIT 5"),
  GET_ALL_OPENED_TASK("SELECT Status, Id, Subject FROM Task WHERE Account_Number__c = '%s' AND Status <> 'Accepted' AND Status <> 'Completed'"),
  SF_BANK_ARCHIVE_QUERY("SELECT Id, Bank_Archive_Number__c FROM Case WHERE Account_Number__c = '%s'"),
  SF_VERIFICATION_QUERY("SELECT Status, Id, Subject FROM Task WHERE Account_Number__c = '%s' and Status in ('Accepted') and Subject in ('Pay money', 'Check New Customer') LIMIT 5"),
  GET_ATTACHMENT_ID("Select Id FROM Attachment__c WHERE account__r.BackendId__c = '%s' AND Type__c = '%s'"),
  GET_MARKETING_CONSENTS("SELECT Name__c, Value__c, Version__c FROM CustomerConsent__c WHERE Account__c = '%s'"),
  SF_GET_CASE_STATUS_BY_SSN("SELECT Status FROM Case WHERE account_ssn__c='%s' AND RecordType.Name='%s'"),
  SF_GET_CASE_NUMBER("SELECT CaseNumber FROM Case WHERE account_ssn__c='%s' AND RecordType.Name='%s'"),
  SF_GET_MD5("SELECT MD5__c FROM Case WHERE account_ssn__c='%s' AND RecordType.Name='%s'"),
  SF_GET_MD5MATCHED__C("SELECT MD5Matched__c FROM Case WHERE account_ssn__c='%s'"),
  SF_GET_ACCOUNT_IDENTIFICATION_STATUS("SELECT IdentificationStatus__c FROM Account WHERE SSN__c='%s'"),
  SF_GET_CONTRACT_NUMBER("SELECT ContractNumber FROM Contract WHERE Account_SSN__c = '%s'"),
  SF_GET_CONTRACT_MMP_VALUE("SELECT MMP__c FROM Contract WHERE Account_SSN__c = '%s'"),
  SF_GET_TASK_STATUS_AND_SUBJECT("SELECT Status,Subject FROM Task WHERE Account.ssn__c= '%s' AND Subject='%s'"),
  SF_GET_INVOICE_DAYS_LATE("SELECT Days_late__c FROM Invoice__c WHERE Contract__r.account_ssn__c = '%s'"),
  SF_GET_CASE_OWNER("SELECT Name FROM User WHERE Id IN (SELECT OwnerId FROM Case WHERE account_ssn__c = '%s' AND RecordType.Name ='%s')"),
  SF_GET_BANK_ACCOUNT_NUMBER("SELECT Bank_Account__c FROM Account where AccountNumber = '%s'"),
  SF_GET_APPLICATION_PRODUCT_AMOUNT("SELECT Product_amount_taken__c FROM MCBCreditApplication__c WHERE Account_SSN__c='%s'"),
  GET_ATTACHMENT("SELECT Id, File_Name__c, Preview_Link__c, Type__c FROM Attachment__c WHERE Type__c =  '%s' AND Account__c IN (SELECT Id FROM Account WHERE BackendId__c = '%s' )"),
  GET_COLLECTION_ORDER_BY_INVOICE_ID("SELECT Id,BackendId__c,CollectionCompany__c,CollectionType__c,Invoice__c, IsDeleted,SendingState__c,State__c,Type__c FROM CollectionOrder__c WHERE Invoice__c = '%s'"),
  GET_COLLECTION_ORDER_BY_ID_AND_STATE("SELECT Id FROM CollectionOrder__c WHERE Id = '%s' AND SendingState__c = '%s'"),
  GET_INVOICE_UNALLOCATED_AMOUNT("SELECT Unallocated_amount__c FROM Invoice__c WHERE Contract__r.account_ssn__c = '%s'"),
  GET_INVOICE_BY_ACCOUNT_NUMBER_AND_DAYS_LATE("SELECT Id, BackendId__c,Days_late__c, Invoice_Date__c,Invoice_Order_Number__c,In_Collection__c,Status__c FROM Invoice__c WHERE Account_Number__c = '%s' AND Days_late__c = %s"),
  GET_PARENT_INVOICE_NAME("SELECT Name FROM Invoice__c WHERE Account_Number__c = '%s' order by BackendId__c  ASC limit 1"),
  GET_INVOICE_STATUS("SELECT Status__c FROM Invoice__c where Account_Number__c = '%s' order by BackendId__c  ASC limit 1"),
  GET_INVOICE_ID("SELECT BackendId__c FROM Invoice__c where Account_Number__c = '%s'"),
  GET_LATEST_INVOICE_NAME("SELECT Name FROM Invoice__c WHERE Account_Number__c = '%s' order by BackendId__c  DESC limit 1"),
  GET_LATEST_INVOICE_STATUS("SELECT Status__c FROM Invoice__c where Account_Number__c = '%s' order by BackendId__c  DESC limit 1"),
  GET_LATEST_INVOICE_ID("SELECT MAX(BackendId__c) FROM Invoice__c where Account_Number__c = '%s'"),
  GET_APPLICATION_NUMBER("SELECT Name FROM MCBCreditApplication__c WHERE Account_Number__c = '%s'"),
  GET_APPLICATION_ID("SELECT ID FROM MCBCreditApplication__c WHERE Account_Number__c = '%s'");

  public final String query;

  SFQuery(final String query) {
    this.query = query;
  }

  public String getQuery() {
    return query;
  }
}
