public interface DisplaySubject {
    void attach(DisplayObserver observer);
    void detach(DisplayObserver observer);

    void notifyObservers();
}
