package cn.hyperchain.myselfmain;

import cn.hyperchain.invoke.InvokeTransfer;
import cn.hyperchain.main.HVMConfig;
import cn.hyperchain.sdk.account.Account;
import cn.hyperchain.sdk.account.Algo;
import cn.hyperchain.sdk.common.utils.Decoder;
import cn.hyperchain.sdk.exception.RequestException;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.service.AccountService;
import cn.hyperchain.sdk.service.ContractService;
import cn.hyperchain.sdk.transaction.Transaction;

import java.io.IOException;
import java.nio.file.Files;

public class Deploy {
    public static void main(String[] args) throws IOException, RequestException {
        ContractService contractService = HVMConfig.contractService();
        AccountService accountService = HVMConfig.accountService();
        Account account = accountService.genAccount(Algo.ECRAW);
        Transaction transaction = new Transaction.HVMBuilder(account.getAddress())
                .deploy(Files.newInputStream(HVMInfo.path)).build();
        transaction.sign(account);
        ReceiptResponse receiptResponse = contractService.deploy(transaction).send().polling();
        String ContractAddress = receiptResponse.getContractAddress();
        System.out.println(ContractAddress);
        InvokeTransfer invokeTransfer = new InvokeTransfer("from", "to", 10);
        Transaction transaction1 = new Transaction.HVMBuilder(ContractAddress)
                .invoke(ContractAddress,invokeTransfer)
                .build();
        transaction1.sign(account);
        ReceiptResponse receiptResponse1 = contractService.invoke(transaction1).send().polling();

        Boolean judge = Decoder.decodeHVM(receiptResponse1.getRet(),Boolean.class);
        System.out.println(judge);



    }
}
