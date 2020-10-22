package br.com.erudio.converter.custom;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVO;
import org.springframework.stereotype.Service;

@Service
public class PersonConverter {

    public PersonVO convertEntityToVO(Person person) {
        PersonVO vo = new PersonVO();
        vo.setKey(person.getId());
        vo.setAddress(person.getAddress());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        return vo;
    }

    public Person convertVOToEntity(PersonVO person) {
        Person entity = new Person();
        entity.setId(person.getKey());
        entity.setAddress(person.getAddress());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        return entity;
    }
}
