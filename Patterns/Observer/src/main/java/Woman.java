
public class Woman implements SalesObserver {
    public void handleSales(String sale) {
        if (sale.equals("Clothes")) {
            System.out.println("I go shopping!");
        }
    }
}
