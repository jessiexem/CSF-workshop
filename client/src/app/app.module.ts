import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule} from '@angular/common/http'

import { AppComponent } from './app.component';
import { ContactsComponent } from './components/contacts.component';
import { ListComponent } from './components/list.component';
import { ContactService } from 'src/services/contact.service';

@NgModule({
  declarations: [
    AppComponent,
    ContactsComponent,
    ListComponent
  ],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule, HttpClientModule
  ],
  providers: [ContactService],
  bootstrap: [AppComponent]
})
export class AppModule { }
