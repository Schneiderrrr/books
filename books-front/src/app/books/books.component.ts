import { Component, OnInit } from '@angular/core';
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
  public books: Book[] = [];
  public take?: number;
  public skip?: number;

  constructor(
    private bookService: BookService, 
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.take = Number(this.route.snapshot.paramMap.get('take'));
    this.skip = Number(this.route.snapshot.paramMap.get('skip'));

    console.log("take = " + this.take.toString() + " skip = " + this.skip.toString());
    
    if (this.take && this.skip){
      this.getPartOfBooks(this.take, this.skip);
    } 
    else {
      this.getBooks();
    }
  }

  public getBooks(): void{
    this.bookService.getBooks().subscribe(
      (response: Book[]) => {
        this.books = response;
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
        this.books = response;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        alert(error.message);
      }
    );
  }

  public deleteBook(id: number): void{
    this.books = this.books.filter(b => b.id != id);
    this.bookService.deleteBook(id).subscribe();
  }

  public addBook(name: string, isbn: string, author: string, date: Date ): void{
    var book = {name: name, isbn: isbn, author: author, releaseDate: date} as Book;
    this.bookService.addBook(book)
      .subscribe(book => {
        this.books.push(book)
      });
  }
}
