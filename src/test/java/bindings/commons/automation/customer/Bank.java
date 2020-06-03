package bindings.commons.automation.customer;

public enum Bank {

  BANCO_NACIONAL_DE_MEXICO("002", "Banco Nacional de México, S.A."),
  BBVA_BANCOMER("012", "BBVA Bancomer, S.A."),
  BANCO_SANTANDER("014", "Banco Santander (Mexico), S.A."),
  BANCO_NACIONAL_DEL_EJERCITO("019", "Banco Nacional del Ejército, Fuerza Aérea y Armada, S.N.C."),
  HSBC("021", "HSBC México, S.A."),
  BANCO_DEL_BAJIO("030", "Banco del Bajío, S.A."),
  IXE_BANCO("032", "IXE Banco, S.A."),
  BANCO_INBURSA("036", "Banco Inbursa, S.A."),
  BANCO_INTERACCIONES("037", "Banco Interacciones, S.A."),
  BANCA_MIFEL("042", "Banca Mifel, S.A."),
  SCOTIABANK_INVERLAT("044", "Scotiabank Inverlat, S.A."),
  BANCO_REGIONAL_DE_MONTERREY("058", "Banco Regional de Monterrey, S.A."),
  BANCO_INVEX("059", "Banco Invex, S.A."),
  BANSI("060", "Bansi, S.A."),
  BANCA_AFIRME("062", "Banca Afirme, S.A."),
  BANCO_MERCANTIL_DEL_NORTE("072", "Banco Mercantil del Norte, S.A."),
  BANCO_MONEX("112", "Banco Monex, S.A."),
  BANCO_VE_POR_MAS("113", "Banco Ve por Mas, S.A."),
  BANCO_AZTECA("127", "Banco Azteca, S.A."),
  BANCO_AUTOFIN("128", "Banco Autofin México, S.A."),
  BANCO_AHORRO_FAMSA("131", "Banco Ahorro Famsa, S.A."),
  BANCO_MULTIVA("132", "Banco Multiva, S.A."),
  ACTINVER_BANCO("133", "Actinver Banco, S.A."),
  BANCO_WAL_MART_DE_MEXICO_ADELANTE("134", "Banco Wal Mart de México Adelante, S.A."),
  BANCOPPEL("137", "BanCoppel, S.A."),
  BANCO_AMIGO("138", "Banco Amigo, S.A."),
  BANCO_FACIL("140", "Banco Fácil, S.A."),
  CONSULTORIA_INTERNATIONAL_BANCO("143", "Consultoría Internacional Banco, S.A."),
  BANCO_BASE("145", "Banco BASE, S.A. de I.B.M."),
  BANCO_DEL_AHORRO_NACIONAL("166", "Banco del Ahorro Nacional y Servicios Financieros, S.N.C.");

  public final String bankCode;
  public final String bankName;

  Bank(final String bankCode, final String bankName) {
    this.bankCode = bankCode;
    this.bankName = bankName;
  }

}
