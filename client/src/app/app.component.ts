import { Component } from '@angular/core';
import { ContactService } from 'src/services/contact.service';
import { Contact } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public isLoadAddForm = true
  public isLoadListContacts = false


  constructor(private contactSvc: ContactService) {}

  loadAddForm() {
    this.isLoadAddForm = true
    this.isLoadListContacts = false
  }

  loadListContact() {
    
    console.info("in loadListContact()")
    //initalise the component first --> goes to OnInit
    this.isLoadListContacts = true;
    this.isLoadAddForm = false;

    this.contactSvc.getAllContacts()
    .then(
      result => {
        console.info("in loadListContact",result)
        this.contactSvc.onNewList.next(result);
      }
    )
    .catch(
      error => {
        console.error('>>>> performGetList error: ', error)
        alert(`>>> performGetList error: ${JSON.stringify(error)}`)
      }
    )

  }

  saveContact(newContact : Contact) {

    console.info("saveContact:",newContact)

    //save to db
    this.contactSvc.addContact(newContact)
      .then(result =>{
        console.info('>>>> result:', result)
      
        // this.isAddContactSuccess = true
        // this.isLoadAddForm = false
        this.loadListContact()
      })
      .catch(error => {
        console.error("---error:", error)
        //this.isAddContactSuccess = false
        this.isLoadAddForm = true;

        alert(`Cannot add contact: ${newContact.name}. Please check input particulars again.`)
      })
  }

  deleteContact(email : String) {
    console.info("=====in deleteContact email:",email)

    this.contactSvc.deleteContact(email)
    .then(result => {
      console.info('>>>> deleteContact result:', result)
      
      this.loadListContact()
    })
    .catch(error => {
      console.error("--- deleteContact error:", error)

      this.isLoadListContacts = true
      this.isLoadAddForm = false

      alert(`Error encountered in deleting contact: ${email}.`)
    })
  }

  // performGetList() {
  //   this.contactSvc.getAllContacts()
  //   .then(
  //     result => {
  //       this.contactSvc.onNewList.next(result)

  //       this.isLoadListContacts = true
  //       this.isLoadAddForm = false
  //     }
  //   )
  //   .catch(
  //     error => {
  //       console.error('>>>> performGetList error: ', error)
  //       alert(`>>> performGetList error: ${JSON.stringify(error)}`)
  //     }
  //   )
  // }
}
