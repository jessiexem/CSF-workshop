import { Component, OnChanges, OnDestroy, OnInit, Output, SimpleChanges } from '@angular/core';
import { Subject, Subscription } from 'rxjs';
import { ContactService } from 'src/services/contact.service';
import { Contact } from '../models';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit, OnDestroy{

  contactList : Contact[] = []
  sub$!: Subscription

  @Output()
  onDelete = new Subject<String>()

  constructor(private contactSvc: ContactService) {}

  ngOnInit(): void {
    console.info("in ngOnInit ListComponent")
    this.sub$ = this.contactSvc.onNewList.subscribe(
      list => {
        this.contactList = list
        console.info("list",list)
      }
    )

  }

  ngOnDestroy(): void {
    this.sub$.unsubscribe()
  }

  deleteContact(email: String) {
    console.log("deleteContact email:",email)

    this.onDelete.next(email)
  }

}
