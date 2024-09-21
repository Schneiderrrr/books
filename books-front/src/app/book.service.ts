import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from '@angular/common/http';
import { Book } from "./book";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root'
})
export class BookService{
    private apiVer = "/api/v1";
    private apiServiceUrl = environment.apiBaseUrl + this.apiVer;

    constructor(private http: HttpClient){}

    public getBooks(): Observable<Book[]>{
        return this.http.get<Book[]>(`${this.apiServiceUrl}/books`);
    }
    
    public getPartOfBooks(take: number, skip: number): Observable<Book[]>{
        return this.http.get<Book[]>(`${this.apiServiceUrl}/books?take=${take}&skip=${skip}`);
    }

    public getSortedBooks(sort: string): Observable<Book[]>{
        return this.http.get<Book[]>(`${this.apiServiceUrl}/books?sort=${sort}`);
    }

    public getOneBook(id: number): Observable<Book>{
        return this.http.get<Book>(`${this.apiServiceUrl}/books/${id}`);
    }

    public addBook(book: Book): Observable<Book>{
        return this.http.post<Book>(`${this.apiServiceUrl}/books`, book);
    }

    public updateBook(id: number, book: Book): Observable<Book>{
        return this.http.put<Book>(`${this.apiServiceUrl}/books/${id}`, book);
    }

    public deleteBook(id: number): Observable<void>{
        return this.http.delete<void>(`${this.apiServiceUrl}/books/${id}`);
    }
}