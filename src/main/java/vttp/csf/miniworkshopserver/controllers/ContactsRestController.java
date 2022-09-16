package vttp.csf.miniworkshopserver.controllers;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.csf.miniworkshopserver.models.Contact;

import vttp.csf.miniworkshopserver.services.ContactsService;

@RestController
@RequestMapping (path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactsRestController {

    @Autowired
    ContactsService contactsSvc;

    private Logger logger = Logger.getLogger(ContactsRestController.class.getName());
    
    @GetMapping(path="/contacts")
    public ResponseEntity<List<Contact>> getContacts() {
        logger.info("in getContacts controller");

        List<Contact> contactList = contactsSvc.getAllContacts();

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(contactList);
    }

    @PostMapping (path="/addContact")
    public ResponseEntity<String> addContact(@RequestBody Contact contact) {
        logger.info("in addContact controller");

        Optional<Contact> optContact = contactsSvc.findContactByEmail(contact.getEmail());

        ////should not add contact if email already exists
        if (optContact.isPresent()) {
            logger.severe("Contact with email already exists. Cannot create user.");
            JsonObject resp = Json.createObjectBuilder()
            .add("message",String.format("Contact %s with email already exists. Cannot add contact.", contact.getName()))
            .build();

            return ResponseEntity.status(400)
            .body(resp.toString());
        }   

        boolean b = contactsSvc.addContact(contact);
        //HttpHeaders responseHeaders = new HttpHeaders();
        //responseHeaders.set("Access-Control-Allow-Origin","*");

        if (!b) {
            JsonObject resp = Json.createObjectBuilder()
            .add("message",String.format("%s was not added to contact list successfully", contact.getName()))
            .build();

            return ResponseEntity.status(400)
            .body(resp.toString());
        }
        else {
            JsonObject resp = Json.createObjectBuilder()
            .add("message", String.format("%s was sucessfully added to contact list", contact.getName()))
            .build();

            return ResponseEntity.status(HttpStatus.CREATED)
            .body(resp.toString());
        }
    }

    @PostMapping (path="/deleteContact")
    public ResponseEntity<String> deleteContact(@RequestBody String email) {
        logger.info("in deleteContact controller");

        boolean b = contactsSvc.deleteContact(email);

        if (!b) {
            JsonObject resp = Json.createObjectBuilder()
            .add("message",String.format("%s was not deleted successfully", email))
            .build();

            return ResponseEntity.status(400)
            .body(resp.toString());
        }
        else {
            JsonObject resp = Json.createObjectBuilder()
            .add("message", String.format("%s was sucessfully deleted from contact list", email))
            .build();

            return ResponseEntity.status(HttpStatus.OK)
            .body(resp.toString());
        }

    }

}
