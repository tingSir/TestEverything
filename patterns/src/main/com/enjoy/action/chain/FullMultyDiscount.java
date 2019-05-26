package com.enjoy.action.chain;

/**
 * 满减
 */
public class FullMultyDiscount extends com.enjoy.action.chain.MultyDiscount {
    public FullMultyDiscount(com.enjoy.action.chain.MultyDiscount nextMultyDiscount) {
        super(nextMultyDiscount);
    }

    @Override
    public int calculate(int money) {
        if (money > 200){
            System.out.println("优惠满减20元");
            money = money - 20;
        }

        return super.calculate(money);
    }
}
