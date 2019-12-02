package cn.hyperchain.main;

import cn.hyperchain.invoke.InvokeGetFrombalance;
import cn.hyperchain.invoke.InvokeGetTobalance;
import cn.hyperchain.invoke.InvokeTransfer;
import cn.hyperchain.main.HVMConfig;
import cn.hyperchain.sdk.account.Account;
import cn.hyperchain.sdk.account.Algo;
import cn.hyperchain.sdk.common.utils.Decoder;
import cn.hyperchain.sdk.exception.RequestException;
import cn.hyperchain.sdk.request.Request;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.response.TxHashResponse;
import cn.hyperchain.sdk.service.ContractService;
import cn.hyperchain.sdk.transaction.Transaction;
import cn.hyperchain.sdk.transaction.VMType;

public class Task {
    public static void main(String[] args) throws RequestException {
        InvokeTransfer invokeTransfer = new InvokeTransfer("from", "to", 10);
        InvokeGetFrombalance invokeGetFrombalance = new InvokeGetFrombalance();
        InvokeGetTobalance invokeGetTobalance = new InvokeGetTobalance();

        Account account = HVMConfig.accountService().genAccount(Algo.ECRAW);
                //Account.fromAccountJson(HVMConfig.accountJsonString, "");

        Transaction transaction2 = new Transaction.HVMBuilder(account.getAddress())
                .invoke(HVMConfig.contractAddress,invokeGetFrombalance)
                .build();//调用合约
        Transaction transaction1 = new Transaction.HVMBuilder(account.getAddress())
                .invoke(HVMConfig.contractAddress, invokeTransfer)
                .build();
        transaction1.sign(account);
        transaction2.sign(account);//加数字签名
        ContractService contractService = HVMConfig.contractService();
        ReceiptResponse response1 = contractService.invoke(transaction1).send().polling();
        ReceiptResponse response2 = contractService.invoke(transaction2).send().polling();
        String ret1 = response1.getRet();
        String ret2 = response2.getRet();
        response1.getTxHash();
        Integer fromBalance = Decoder.decodeHVM(ret2,Integer.class);
        Boolean aBoolean    = Decoder.decodeHVM(ret1, Boolean.class);
        System.out.println(aBoolean);
        System.out.println(fromBalance);
    }
}
