package cn.hyperchain.contract;

import cn.hyperchain.annotations.StoreField;
import cn.hyperchain.core.HyperMap;

public class Bank extends BaseContract implements ISBank {

    @StoreField
    HyperMap<String, Integer> hyperMap = new HyperMap<String, Integer>();

    public Bank() {
    }

    public Boolean tran(String from, String to, int value) {
        Integer fromBalance = hyperMap.get(from);
        if (fromBalance == null) {
            fromBalance = 100;
            hyperMap.put(from, fromBalance);
        }
        Integer toBalance = hyperMap.get(to);
        if (toBalance == null) {
            toBalance = 100;
            hyperMap.put(to, toBalance);
        }
        fromBalance -= value;
        toBalance += value;
        hyperMap.put(from, fromBalance);
        hyperMap.put(to, toBalance);
        return true;
    }

    public int getBalance(String account) {
        if (account == null || hyperMap.get(account) == null) {
            return 0;
        }
        return hyperMap.get(account);
    }

    @Override
    public void onCreated() {
        System.out.println("onCreat");
    }

    @Override
    public void onPreCommit() {
        System.out.println("onPreCommit");
    }

    @Override
    public void onCommitted() {
        System.out.println("onCommitted");
    }
}
