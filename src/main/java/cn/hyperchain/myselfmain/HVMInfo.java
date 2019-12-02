package cn.hyperchain.myselfmain;

import cn.hyperchain.sdk.provider.DefaultHttpProvider;
import cn.hyperchain.sdk.provider.HttpProvider;
import cn.hyperchain.sdk.provider.ProviderManager;
import cn.hyperchain.sdk.service.AccountService;
import cn.hyperchain.sdk.service.ContractService;
import cn.hyperchain.sdk.service.ServiceManager;
import cn.hyperchain.sdk.service.TxService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

public class HVMInfo {
    private final static String jsonRpcUrl = "42.159.94.163:8081,42.159.94.163:8082,42.159.94.163:8083,42.159.94.163:8084";
    private final static String separator = ",";

    public final static  String jarPath = "/Users/huajishaonian/IdeaProjects/thefirst/target/classes/contract/thefirst-1.0-SNAPSHOT-testhvm.jar";
    public final static  Path   path = Paths.get(jarPath);
    public static HttpProvider[] getHttpProvider(){
        return Arrays.stream(jsonRpcUrl.split(separator))
                .map(url->new DefaultHttpProvider.Builder().setUrl(url).build())
                .toArray(HttpProvider[]::new);
    }

     public static ProviderManager getProviderManager(){
        return ProviderManager.createManager(getHttpProvider());
     }

     public static AccountService  getAccountService(){
        return ServiceManager.getAccountService(getProviderManager());
     }

     public static TxService getTxService(){
        return ServiceManager.getTxService(getProviderManager());
     }

     public static ContractService getContractService(){
        return ServiceManager.getContractService(getProviderManager());
     }

     public static String ContractAddress = null;


}
