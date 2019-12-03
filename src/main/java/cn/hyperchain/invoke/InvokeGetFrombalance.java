package cn.hyperchain.invoke;

import cn.hyperchain.contract.BaseContractInterface;
import cn.hyperchain.contract.BaseInvoke;
import cn.hyperchain.contract.ISBank;

public class InvokeGetFrombalance implements BaseInvoke<Integer,ISBank> {
    public String from;
    public String to;
    public int value;

    public InvokeGetFrombalance() {
    }

    public InvokeGetFrombalance(String from, String to, int value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }
    @Override
    public Integer invoke(ISBank baseContractInterface)
    {
        return baseContractInterface.getBalance("from");
    }

    public static void test() {
        System.out.println("输出数据进行测试。第si次测试");
        System.out.println("输出数据进行测试。第si次测试,在dev上做了测试。");
        System.out.println("输出数据进行测试。第si次测试,在bug1上做了测试。");
        System.out.println("输出数据进行测试。第si次测试,在dev上做了测试,并未提交。");
        System.out.println("输出数据进行测试。在ev上做了测试,暂存测试。");
        System.out.println("输出数据进行测试。在ev上做了测试2,暂存测试。");
        System.out.println("输出数据进行测试。在bug上做了测试2,暂存测试。");
    }
}
