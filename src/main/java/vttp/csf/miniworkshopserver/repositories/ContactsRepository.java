package vttp.csf.miniworkshopserver.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.csf.miniworkshopserver.models.Contact;

import static vttp.csf.miniworkshopserver.repositories.Queries.*;

import java.util.Optional;
import java.util.logging.Logger;

@Repository
public class ContactsRepository {

    @Autowired
    private JdbcTemplate template;

    private final Logger logger = Logger.getLogger(ContactsRepository.class.getName());

    public boolean addContact(Contact contact) {
        int added = template.update(SQL_INSERT_CONTACT,
             contact.getName(),
             contact.getMobile(),
             contact.getEmail());
        
        return added>0;
    }

    public SqlRowSet getAllContacts() {
        return template.queryForRowSet(SQL_SELECT_ALL_CONTACTS);
    }

    public Optional<Contact> findContactByEmail(String email) {
        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_USER_BY_EMAIL,email);
        if (!rs.first()) {
            logger.warning(">>>> ContactRepository: findUserByEmail: no data found");
            return Optional.empty();
        }
        else return Optional.of(Contact.convertSqlRowSetToContact(rs));
    }

    public boolean deleteContact(String email) {
        int deleted = template.update(SQL_DELETE_CONTACT_BY_EMAIL, email);

        return deleted>0;
    }
}
