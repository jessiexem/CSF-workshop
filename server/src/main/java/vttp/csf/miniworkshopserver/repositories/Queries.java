package vttp.csf.miniworkshopserver.repositories;

public interface Queries {
    
    public static final String SQL_INSERT_CONTACT = 
        "insert into contact (name, mobile, email) values (?,?,?);";

    public static final String SQL_SELECT_ALL_CONTACTS = 
        "select * from contact;";

    public static final String SQL_SELECT_USER_BY_EMAIL = 
        "select * from contact where email=?; ";

    public static final String SQL_DELETE_CONTACT_BY_EMAIL = 
        "delete from contact where email=?; ";
}
