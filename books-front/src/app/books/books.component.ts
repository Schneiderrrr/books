import { Component, inject, OnInit } from '@angular/core';
import { Book } from '../book';
import { BookService } from '../book.service';
import { ActivatedRoute } from '@angular/router';
import { Store} from "@ngrx/store";
import { map, Observable } from "rxjs";
import * as BooksActions from "../state/books.actions";
import * as BooksSelectors from "../state/books.selectors";

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  private readonly store = inject(Store);

  public statuses$ = this.store.select(BooksSelectors.selectBooksStatus);
  public errors$ = this.store.select(BooksSelectors.selectBooksError);
  public books$ = this.store.select(BooksSelectors.selectAllBooks);

  public take?: number;
  public skip?: number;
  public sort?: string;

  constructor(
    private bookService: BookService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.take = Number(this.route.snapshot.paramMap.get('take'));
    this.skip = Number(this.route.snapshot.paramMap.get('skip'));
    this.sort = this.route.snapshot.paramMap.get('sort')?.toString();

    if (this.take && this.skip){
      this.getPartOfBooks(this.take, this.skip);
    }
    else if (this.sort){
      this.getSortedBooks(this.sort);
    }
    else {
      this.getBooks();
    }
  }

  public getBooksArr$(): Observable<Book[]> {
    return this.books$.pipe(map((book) => Object.values(book)));
  }

  public getBooks(): void{
    this.store.dispatch(BooksActions.initBooks());
  }

  public getPartOfBooks(take: number, skip: number): void{
    this.store.dispatch(BooksActions.partOfBooks({ take, skip }));
  }

  public getSortedBooks(sort: string): void{
    this.store.dispatch(BooksActions.sortedBooks({ sort }));
  }

  public deleteBook(id: number): void{
    this.store.dispatch(BooksActions.deleteBook({ id }));
  }

  public addBook(name: string, isbn: string, author: string, date: Date): void{
    const book = {name: name, isbn: isbn, author: author, releaseDate: date} as Book;
    this.store.dispatch(BooksActions.addBook({ book }));
  }
}
