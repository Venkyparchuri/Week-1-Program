public MyModel() {
// just for testing we hard-code the persons here:
persons.add(new Person("Lars", "Vogel"));
persons.add(new Person("Jim", "Knopf"));
}

private void notifyListeners(Object object, String property, String oldValue, String newValue) {
for (PropertyChangeListener name : listener) {
name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
}
}

public void addChangeListener(PropertyChangeListener newListener) {
listener.add(newListener);
}

}
package com.vogella.java.designpattern.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MyObserver implements PropertyChangeListener {
public MyObserver(MyModel model) {
model.addChangeListener(this);
}

@Override
public void propertyChange(PropertyChangeEvent event) {
System.out.println("Changed property: " + event.getPropertyName() + " [old -> "
+ event.getOldValue() + "] | [new -> " + event.getNewValue() +"]");
}
}
package com.vogella.java.designpattern.observer;

import com.vogella.java.designpattern.observer.MyModel.Person;

public class Main {

public static void main(String[] args) {
MyModel model = new MyModel();
MyObserver observer = new MyObserver(model);
// we change the last name of the person, observer will get notified
for (Person person : model.getPersons()) {
person.setLastName(person.getLastName() + "1");
}
// we change the name of the person, observer will get notified
for (Person person : model.getPersons()) {
person.setFirstName(person.getFirstName() + "1");
}
}
}
