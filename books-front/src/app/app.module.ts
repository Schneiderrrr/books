import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BookService } from './book.service';
import { HttpClientModule } from '@angular/common/http';
import { BooksComponent } from './books/books.component';
import { AppRoutingModule } from './app-routing.module';
import { BookDetailComponent } from './book-detail/book-detail.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    BooksComponent,
    BookDetailComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [BookService],
  bootstrap: [AppComponent]
})
export class AppModule { }
