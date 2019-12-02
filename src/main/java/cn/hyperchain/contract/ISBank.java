package cn.hyperchain.contract;

public interface ISBank extends BaseContractInterface
{
     Boolean tran(String from ,String to, int value);
     int getBalance(String account);
}
