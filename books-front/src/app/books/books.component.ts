import { Component, OnInit, signal } from '@angular/core';
import { Book } from '../book';
import { BookService } from '../book.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  public books = signal<Book[]>([]);

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

  public getBooks(): void{
    this.bookService.getBooks().subscribe(
      (response: Book[]) => {
        this.books.set(response);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        alert(error.message);
      }
    );
  }

  public getPartOfBooks(take: number, skip: number): void{
    this.bookService.getPartOfBooks(take, skip).subscribe(
      (response: Book[]) => {
        this.books.set(response);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        alert(error.message);
      }
    );
  }

  public getSortedBooks(sort: string): void{
    this.bookService.getSortedBooks(sort).subscribe(
      (response: Book[]) => {
        this.books.set(response);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        alert(error.message);
      }
    );
  }

  public deleteBook(id: number): void{
    this.bookService.deleteBook(id).subscribe(
      () => {
        this.books.update((books) => books.filter(b => b.id != id));
      });
  }

  public addBook(name: string, isbn: string, author: string, date: Date ): void{
    var book = {name: name, isbn: isbn, author: author, releaseDate: date} as Book;
    this.bookService.addBook(book)
      .subscribe(book => {
        this.books.update((books) => [book, ...books]);
      });
  }
}
