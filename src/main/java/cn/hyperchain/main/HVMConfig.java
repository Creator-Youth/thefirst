package cn.hyperchain.main;
import cn.hyperchain.sdk.provider.DefaultHttpProvider;
import cn.hyperchain.sdk.provider.HttpProvider;
import cn.hyperchain.sdk.provider.ProviderManager;
import cn.hyperchain.sdk.service.AccountService;
import cn.hyperchain.sdk.service.ContractService;
import cn.hyperchain.sdk.service.ServiceManager;
import cn.hyperchain.sdk.service.TxService;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Loger
 */
public class HVMConfig {

    //各个节点的URL
    private final static String jsonRpcUrl = "42.159.94.163:8081,42.159.94.163:8082,42.159.94.163:8083,42.159.94.163:8084";

    //分隔符
    private static final String separator = ",";

    /*public static final String accountJsonString = "{\"address\":\"b5e525b4827786303b2eb7882287deee20e1a5be\"," +
            "\"publicKey\":\"0467adaf3f7c941bac36f95eb04526e5ed8e7e0e64b874eebed776e2ebbc30a674370e34bb7be59d957fbd5e80c6f73b63b90806edb299b565af0b203a51fefb8c\"," +
            "\"privateKey\":\"897fa10eede231d68b0291af542f41c8a99ba6defaca7274a9bc43f4aed0952370ae3cf2bd5a4c7752983242325361cb1645470041fa92a8369cf2bd4691a8a5\"," +
            "\"version\":\"4.0\"," +
            "\"algo\":\"0x11\"}";*/

    public static final String contractAddress = "0xde8fc00d561b01b615e8554bb3f8872b2436dc0a";

    private static HttpProvider[ ] getByJsonRpcUrl(String jsonRpcUrl) {
        return Arrays.stream(jsonRpcUrl.split(separator))
        .map(url -> new DefaultHttpProvider.Builder().setUrl(url).build())
        .toArray(HttpProvider[]::new);
        /*String[] str = jsonRpcUrl.split(separator);
        HttpProvider []httpProviders = new HttpProvider[str.length];
        for(int i = 0; i < str.length; i++) {
            httpProviders[i] = new DefaultHttpProvider.Builder().setUrl(str[i]).build();
        }
        return  httpProviders;*/
    }

    public static ProviderManager providerManager() {
        HttpProvider[] httpProviders = getByJsonRpcUrl(jsonRpcUrl);
        return ProviderManager.createManager(httpProviders);
    }//ProviderManager负责管理和集成HttpProvider

    /**
     * 合约调用
     */
    public static ContractService contractService() //service提供服务，ServiceManager负责管理service
    {
        return ServiceManager
                .getContractService(providerManager());
    }
    /*public  static NodeService    nodeService() throws RequestException {
        NodeRequest nodeRequest = (NodeRequest) ServiceManager.getNodeService(providerManager()).getNodes();
        NodeResponse nodeResponse = (NodeResponse) nodeRequest.send();
        return ServiceManager.getNodeService(providerManager());
    }*/
    /**
     * 普通的转账交易，不涉及虚拟机
     */
    public static TxService txService() {
        return ServiceManager.getTxService(providerManager());
    }

    /**
     * Hyperchain  账户相关操作
     */
    public static AccountService accountService() {
        return ServiceManager.getAccountService(providerManager());
    }

    HashMap <String,String > hashMap = new HashMap<>();



}
