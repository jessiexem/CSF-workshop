package vttp.csf.miniworkshopserver.models;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Contact {
    
    private String name;
    private String mobile;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Contact create(String json) {
        final Contact contact = new Contact();

        InputStream is = new ByteArrayInputStream(json.getBytes());
        JsonReader reader = Json.createReader(is);
        JsonObject obj = reader.readObject();

        contact.setName(obj.getString("name"));
        contact.setEmail(obj.getString("email"));
        contact.setMobile(obj.getString("mobile"));

        return contact;
    }

    public static Contact convertSqlRowSetToContact(SqlRowSet rs) {
        Contact contact = new Contact();
        contact.setName(rs.getString("name"));
        contact.setEmail(rs.getString("email"));
        contact.setMobile(rs.getString("mobile"));
        return contact;
    }

}
