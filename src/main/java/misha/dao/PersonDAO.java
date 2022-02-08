package misha.dao;

import misha.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int PIOPLE_COUNT;
    private List<Person> piple;
    {
        piple = new ArrayList<>();

        piple.add(new Person(++PIOPLE_COUNT,"Misha"));
        piple.add(new Person(++PIOPLE_COUNT,"Dima"));
        piple.add(new Person(++PIOPLE_COUNT,"Sasha"));
        piple.add(new Person(++PIOPLE_COUNT,"Katya"));



    }
    public List<Person> index(){
        return piple;
    }

    public Person shpw(int id){
        return piple.stream().filter(person -> person.getId()==id).findAny()
        .orElse(null);
    }
    public void save (Person person){
        person.setId(++PIOPLE_COUNT);
        piple.add(person);
    }
    public void update(int id,Person updatedPerson){
        Person personToBeUpdated= shpw(id);

        personToBeUpdated.setName(updatedPerson.getName());
    }
}
