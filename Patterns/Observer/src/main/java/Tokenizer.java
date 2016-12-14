/**
 * Created by dimac on 14.12.2016.
 */
public class Tokenizer implements SalesObservable {
    private static final int MAX_OBSERVERS = 5;

    private SalesObserver[] observers;
    private int countOfObservers;

    public Tokenizer() {
        this.observers = new SalesObserver[MAX_OBSERVERS];
        this.countOfObservers = 0;
    }

    public void tokenize (String sales) {
        String[] salesAsArray = sales.split("\\s+");
        for (int i=0; i<salesAsArray.length; i++) {
            notifyObserver(salesAsArray[i]);
        }
    }

    public void addObserver(SalesObserver observer) {
        if (countOfObservers<MAX_OBSERVERS){
            this.observers[countOfObservers] = observer;
            countOfObservers++;
        } else {
            System.out.println("Observers is full");
        }
    }

    public void notifyObserver(String sale) {
        for (int i=0; i<countOfObservers; i++){
            observers[i].handleSales(sale);
        }
    }
}
