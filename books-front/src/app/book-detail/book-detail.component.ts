import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Book } from '../book';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {
  book?: Book;

  constructor(
    private bookService: BookService, 
    private route: ActivatedRoute,
    private location: Location
  ){}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.getOneBook(id);
  }

  public getOneBook(id: number): void {
    this.bookService.getOneBook(id)
      .subscribe(hero => this.book = hero);
  }

  public goBack(): void {
    this.location.back();
  }

  public save(): void {
    if (this.book){
      this.bookService.updateBook(this.book.id, this.book)
      .subscribe(() => this.goBack());
    }
  }
}
