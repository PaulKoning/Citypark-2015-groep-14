package bankclient;

public class BankProxy implements bankclient.Bank {
  private String _endpoint = null;
  private bankclient.Bank bank = null;
  
  public BankProxy() {
    _initBankProxy();
  }
  
  public BankProxy(String endpoint) {
    _endpoint = endpoint;
    _initBankProxy();
  }
  
  private void _initBankProxy() {
    try {
      bank = (new bankclient.BankServiceLocator()).getBank();
      if (bank != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)bank)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)bank)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (bank != null)
      ((javax.xml.rpc.Stub)bank)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public bankclient.Bank getBank() {
    if (bank == null)
      _initBankProxy();
    return bank;
  }
  
  public boolean transfer(int accountfrom, int accountto, double amount) throws java.rmi.RemoteException{
    if (bank == null)
      _initBankProxy();
    return bank.transfer(accountfrom, accountto, amount);
  }
  
  public boolean transferMetPin(int accountfrom, int accountto, double amount, int pin) throws java.rmi.RemoteException{
    if (bank == null)
      _initBankProxy();
    return bank.transferMetPin(accountfrom, accountto, amount, pin);
  }
  
  public boolean doCreditCheck(int accountnr, double amount) throws java.rmi.RemoteException{
    if (bank == null)
      _initBankProxy();
    return bank.doCreditCheck(accountnr, amount);
  }
  
  
}