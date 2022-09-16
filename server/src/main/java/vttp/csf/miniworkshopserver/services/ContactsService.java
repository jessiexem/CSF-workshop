package vttp.csf.miniworkshopserver.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import vttp.csf.miniworkshopserver.repositories.ContactsRepository;
import vttp.csf.miniworkshopserver.models.Contact;

@Service
public class ContactsService {

    @Autowired
    ContactsRepository contactsRepo;

    public List<Contact> getAllContacts() {
        SqlRowSet rs = contactsRepo.getAllContacts();

        List<Contact> list = new ArrayList<>();

        if (!rs.isBeforeFirst()) {
            return null;
        }

        while (rs.next()) {
            list.add(Contact.convertSqlRowSetToContact(rs));
        }
        
        return list;
    }
    
    public boolean addContact(Contact contact) {
        return contactsRepo.addContact(contact);
    }

    public Optional<Contact> findContactByEmail(String email) {
        return contactsRepo.findContactByEmail(email);
    }

    public boolean deleteContact(String email) {
        return contactsRepo.deleteContact(email);
    }
}
