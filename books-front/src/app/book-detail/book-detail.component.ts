import {Component, inject, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Book } from '../book';
import { Store } from "@ngrx/store";
import { Actions, ofType } from "@ngrx/effects";
import * as BooksActions from "../state/books.actions";
import * as BooksSelectors from "../state/books.selectors";
import { take } from "rxjs";

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {
  private readonly store = inject(Store);
  private readonly actions = inject(Actions);

  public book$ = this.store.select(BooksSelectors.selectOneBook);

  private readonly bookId = Number(this.route.snapshot.paramMap.get('id'));
  public bookName: string | undefined;
  public bookAuthor: string | undefined;
  public bookISBN: string | undefined;
  public bookReleaseDate: Date | undefined;

  constructor(
    private route: ActivatedRoute,
    private location: Location
  ){ }

  ngOnInit(): void {
    this.getOneBook(this.bookId);

    this.book$.subscribe((book) => {
      this.bookName = book?.name;
      this.bookAuthor = book?.author;
      this.bookISBN = book?.isbn;
      this.bookReleaseDate = book?.releaseDate;
    });
  }

  public getOneBook(id: number): void {
    this.store.dispatch(BooksActions.initBook({ id }));
  }

  public goBack(): void {
    this.location.back();
  }

  public save(): void {
    const book = {
      id: this.bookId,
      name: this.bookName,
      author: this.bookAuthor,
      isbn: this.bookISBN,
      releaseDate: this.bookReleaseDate
    } as Book;
    this.store.dispatch(BooksActions.updateBook({ book }));

    // .dispatch() is asynchronous. So, if we want to do something AFTER dispatching
    // some action, we should subscribe to it.
    // P.S. Maybe it is better to create another one action "backAfterSuccess" and subscribe to it...
    this.actions.pipe(take(1), ofType(BooksActions.loadOneBookSuccess))
      .subscribe(() => this.goBack());
  }
}
