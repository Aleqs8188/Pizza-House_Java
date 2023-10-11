package oleksii.pl;

import java.util.ArrayList;

public class OrderList {
    public ArrayList<Pizza> orderList = new ArrayList<>();
    private double totalSumToPay = 0d;

    public double getTotalSumToPay() {
        return totalSumToPay;
    }

    public void setTotalSumToPay(double totalSumToPay) {
        this.totalSumToPay = totalSumToPay;
    }
}
