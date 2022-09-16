import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { ContactService } from 'src/services/contact.service';
import { Contact } from '../models';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {

  @Output()
  onNewContact = new Subject<Contact>()

  contactForm!: FormGroup

  constructor(private fb: FormBuilder, private contactSvc: ContactService) { }

  ngOnInit(): void {
    this.contactForm = this.createContactForm()
  }

  private createContactForm(): FormGroup {
    return this.fb.group ({
      name: this.fb.control<string>('', [Validators.required]),
      email: this.fb.control<string>('', [Validators.email]),
      mobile: this.fb.control<string>('', [Validators.required, Validators.minLength(5)])
    })
  }

  processAddContact() {
    const contact = this.contactForm.value as Contact
    this.onNewContact.next(contact)
  }
}
