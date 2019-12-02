package cn.hyperchain.main;

import cn.hyperchain.contract.Bank;
import cn.hyperchain.sdk.account.Account;
import cn.hyperchain.sdk.account.Algo;
import cn.hyperchain.sdk.exception.RequestException;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.service.AccountService;
import cn.hyperchain.sdk.service.ContractService;
import cn.hyperchain.sdk.transaction.Transaction;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Deploy {

    //static String  path="/Users/huajishaonian/IdeaProjects/thefirst/target/classes/contract/thefirst-1.0-SNAPSHOT-testhvm.jar";

    public static String deploy(Path path) throws IOException, RequestException {
        ContractService contractService = HVMConfig.contractService();
        AccountService   accountService = HVMConfig.accountService();
        Account                 account = accountService.genAccount(Algo.ECRAW);
        Transaction tx = new Transaction.HVMBuilder(account.getAddress()).deploy(Files.newInputStream(path)).build();//部署合约；
        tx.sign(account);//
        ReceiptResponse response = contractService.deploy(tx).send().polling();
        String contractAddress = response.getContractAddress();
        System.out.println("contractAddress=" + contractAddress);
        return contractAddress;
    }

    public static void main(String[] args) throws IOException, RequestException {
        String path="/Users/huajishaonian/IdeaProjects/thefirst/target/classes/contract/thefirst-1.0-SNAPSHOT-testhvm.jar";
        deploy(Paths.get(path));
        //Logger logger = Logger.getLogger(Bank.class);
                //deploy(Paths.get("/Users/huajishaonian/IdeaProjects/thefirst/target/classes/contract/thefirst-1.0-SNAPSHOT-testhvm.jar"));
    }

}
