import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BooksComponent } from './books/books.component';
import { BookDetailComponent } from './book-detail/book-detail.component';

const routes: Routes = [
  { path: '', redirectTo: '/api/v1/books', pathMatch: 'full' },
  { path: 'api/v1/books;take;skip', component: BooksComponent },
  { path: 'api/v1/books;sort', component: BooksComponent },
  { path: 'api/v1/books', component: BooksComponent },
  { path: 'api/v1/books/:id', component: BookDetailComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }