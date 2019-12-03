package cn.hyperchain.invoke;

import cn.hyperchain.contract.BaseContractInterface;
import cn.hyperchain.contract.BaseInvoke;
import cn.hyperchain.contract.ISBank;

public class InvokeGetTobalance implements BaseInvoke<Integer,ISBank>
{
    @Override
    public Integer invoke(ISBank baseContractInterface) {
        System.out.println("bug1 cnanged1");
        return baseContractInterface.getBalance("to");
    }
}
