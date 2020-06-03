package bindings.commons.automation.generator.observer;

import java.util.ArrayList;
import java.util.List;

public class GeneratorRegistrationEventPublisher {
  private final List<GeneratorObserver> observersList;

  public GeneratorRegistrationEventPublisher(
      final List<GeneratorObserver> observersList) {
    this.observersList = observersList;
  }

  public GeneratorRegistrationEventPublisher() {
    this(new ArrayList<>());
  }

  public void addObserver(final GeneratorObserver generatorObserver) {
    this.observersList.add(generatorObserver);
  }

  public void removeObserver(final GeneratorObserver generatorObserver) {
    this.observersList.remove(generatorObserver);
  }

  public void notifyObservers(final CustomerCreatedEvent customerCreatedEvent) {
    this.observersList.forEach(observer -> observer.onEvent(customerCreatedEvent));
  }
}
