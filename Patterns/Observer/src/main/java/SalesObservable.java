/**
 * Created by dimac on 14.12.2016.
 */
public interface SalesObservable {
    void addObserver (SalesObserver observer);
    void notifyObserver (String sale);
}
