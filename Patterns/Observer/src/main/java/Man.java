
public class Man implements SalesObserver {
    public void handleSales(String sale) {
        if (sale.equals("Beer")){
            System.out.println("Oh eeeee...");
        }
    }
}
