package jpopulator;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;

import java.util.List;

/**
 *
 * @author Hu Jianbo
 * @date: 2018/5/2
 */
public class TestJpopulator {

    public static void main(String[] args) {
        Populator populator = new PopulatorBuilder().build();

        Person person = (Person) populator.populateBean(Person.class);
        System.out.println(person);

        List<Person> persons = populator.populateBeans(Person.class, 2);
        System.out.println(persons);
    }

}
