import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, Subject } from "rxjs";
import { Contact } from "src/app/models";

// const URL_ADD_CONTACT = "http://localhost:8090/api/addContact"
// const URL_GET_CONTACTS = "http://localhost:8090/api/contacts"
// const URL_DELETE_CONTACT = "http://localhost:8090/api/deleteContact"

const URL_ADD_CONTACT = "https://jgcontactbook.herokuapp.com/api/addContact"
const URL_GET_CONTACTS = "https://jgcontactbook.herokuapp.com/api/contacts"
const URL_DELETE_CONTACT = "https://jgcontactbook.herokuapp.com/api/deleteContact"

@Injectable()
export class ContactService {

    onNewList = new Subject<Contact[]>();

    constructor (private http: HttpClient) {}

    addContact(contactDetails: Contact): Promise<String> {

        const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('Accept','application/json')

        return firstValueFrom (
            this.http.post<any>(URL_ADD_CONTACT,contactDetails, {headers})
        )
    }

    getAllContacts(): Promise<Contact[]> {
        const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('Accept','application/json')

        return firstValueFrom (
            this.http.get<Contact[]>(URL_GET_CONTACTS)
        )
        // .then (
        //     x => { return Promise.resolve(x) }
        // )
    }

    deleteContact(email: String): Promise<String> {
        const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('Accept','application/json')

        return firstValueFrom (
            this.http.post<any>(URL_DELETE_CONTACT, email, {headers})
        )
    }
}