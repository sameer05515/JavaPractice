package model;

import java.io.*;
import java.util.*;

/**
 * Created by everduin on 12/12/2016.
 */
public class Database {

    /*changing to Linked List for optimized adding and removing in the middle
    * ArrayList is only optimized for adding and removing at the end*/
    private List<Person> people;

    public Database() {
        people = new LinkedList<Person>();
    }

    /*At this point we still need to tell
    * the table view that the model has
    * changed so that the view updates
    * (30)*/
    public void removePerson(int index) {
        people.remove(index);
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    /*Other objects could potentially modify
    * this list and we don't want that so we
    * change it to
    * Collections.unmodifiableList*/
    public List<Person> getPeople() {
        return Collections.unmodifiableList(people);
    }

    /*Methods for saving and loading*/
    public void saveToFile(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        //Returns type list of persons - (An array is an object)
        Person[] persons = people.toArray(new Person[people.size()]);

        oos.writeObject(persons);

        oos.close();
    }

    public void loadFromFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        //Erasia? I can't understand what word he's saying
        try {
            Person[] persons = (Person[]) ois.readObject();

            people.clear();
            people.addAll(Arrays.asList(persons));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ois.close();
    }
}

