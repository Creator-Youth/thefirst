package cn.hyperchain.invoke;

import cn.hyperchain.contract.BaseInvoke;
import cn.hyperchain.contract.ISBank;

public class InvokeTransfer implements BaseInvoke<Boolean, ISBank>   //合约调用类
{
    public String from;
    public String to;
    public int value;

    public InvokeTransfer() {
    }

    public InvokeTransfer(String from, String to, int value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getValue() {
        return value;
    }

   // @Override
   public Boolean invoke(ISBank isBank) {
        if (isBank == null)
        {
            return false;
        }
        Boolean judge = isBank.tran(from, to, value);
        return judge;
    }

}
