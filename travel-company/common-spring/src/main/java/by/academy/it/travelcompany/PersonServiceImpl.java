package by.academy.it.travelcompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private Person person;

    @Override
    public String getName() {
        return person.getName();
    }
}
