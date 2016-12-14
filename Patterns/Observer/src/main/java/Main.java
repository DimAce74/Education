
public class Main {
    public static void main(String[] args) {
        SalesObservable salesObservable = new Tokenizer();
        SalesObserver man = new Man();
        SalesObserver woman = new Woman();

        salesObservable.addObserver(man);
        salesObservable.addObserver(woman);

        SalesObserver childObserver = new SalesObserver() {
            public void handleSales(String sale) {
                if (sale.equals("Game")) {
                    System.out.println("Mom, buy for me that!");
                }
            }
        };

        salesObservable.addObserver(childObserver);

        Tokenizer tokenizer = (Tokenizer) salesObservable;
        tokenizer.tokenize("Beer Clothes Game");

    }
}
