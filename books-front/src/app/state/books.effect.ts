import { Actions, createEffect, ofType } from "@ngrx/effects";
import { inject } from "@angular/core";
import { catchError, switchMap, map, of } from "rxjs";

import * as BooksActions from "./books.actions";
import { BookService } from "../book.service";
import { Book } from "../book";


export const loadAllBooksEffect = createEffect(
  () => {
    const actions$ = inject(Actions);
    const apiService$ = inject(BookService);

    return actions$.pipe(
      ofType(BooksActions.initBooks),
      switchMap(
        () => apiService$.getBooks().pipe(
          map(
            (books) => BooksActions.loadBooksSuccess({ books: books })
          ),
          catchError((error) => {
            console.error('Error', error);
            return of(BooksActions.loadBooksFailure({ error }));
          })
        )
      ),
    )
  }, { functional: true }
);

export const loadPartOfBooksEffect = createEffect(
  () => {
    const actions$ = inject(Actions);
    const apiService$ = inject(BookService);

    return actions$.pipe(
      ofType(BooksActions.partOfBooks),
      switchMap(
        (action: { take: number, skip: number }) =>
          apiService$.getPartOfBooks(action.take, action.skip)
            .pipe(
              map(
                (books) => BooksActions.loadBooksSuccess({ books: books })
              ),
              catchError((error) => {
                console.error('Error', error);
                return of(BooksActions.loadBooksFailure({ error }));
              })
            )
      ),
    )
  }, { functional: true }
);

export const loadSortedBooksEffect = createEffect(
  () => {
    const actions$ = inject(Actions);
    const apiService$ = inject(BookService);

    return actions$.pipe(
      ofType(BooksActions.sortedBooks),
      switchMap(
        (action: { sort: string }) =>
          apiService$.getSortedBooks(action.sort)
            .pipe(
              map(
                (books) => BooksActions.loadBooksSuccess({ books: books })
              ),
              catchError((error) => {
                console.error('Error', error);
                return of(BooksActions.loadBooksFailure({ error }));
              })
            )
      ),
    )
  }, { functional: true }
);

export const loadBooksWithoutDeletedEffect = createEffect(
  () => {
    const actions$ = inject(Actions);
    const apiService$ = inject(BookService);

    return actions$.pipe(
      ofType(BooksActions.deleteBook),
      switchMap(
        (action: { id: number }) =>
          apiService$.deleteBook(action.id)
            .pipe(
              map(
                () => BooksActions.initBooks()
              ),
              catchError((error) => {
                console.error('Error', error);
                return of(BooksActions.loadBooksFailure({ error }));
              })
            )
      ),
    )
  }, { functional: true }
);

export const loadBooksWithAddedEffect = createEffect(
  () => {
    const actions$ = inject(Actions);
    const apiService$ = inject(BookService);

    return actions$.pipe(
      ofType(BooksActions.addBook),
      switchMap(
        (action: { book: Book }) =>
          apiService$.addBook(action.book)
            .pipe(
              map(
                () => BooksActions.initBooks()
              ),
              catchError((error) => {
                console.error('Error', error);
                return of(BooksActions.loadBooksFailure({ error }));
              })
            )
      ),
    )
  }, { functional: true }
);

export const loadBookEffect = createEffect(
  () => {
    const actions$ = inject(Actions);
    const apiService$ = inject(BookService);

    return actions$.pipe(
      ofType(BooksActions.initBook),
      switchMap(
        (action: { id: number }) =>
          apiService$.getOneBook(action.id)
            .pipe(
              map(
                (book) => BooksActions.loadOneBookSuccess({ book: book })
              ),
              catchError((error) => {
                console.error('Error', error);
                return of(BooksActions.loadOneBookFailure({ error }));
              })
            )
      ),
    )
  }, { functional: true }
);

export const updateBookEffect = createEffect(
  () => {
    const actions$ = inject(Actions);
    const apiService$ = inject(BookService);

    return actions$.pipe(
      ofType(BooksActions.updateBook),
      switchMap(
        (action: { book: Book }) =>
          apiService$.updateBook(action.book.id, action.book)
            .pipe(
              map(
                (book) => BooksActions.loadOneBookSuccess({ book: book })
              ),
              catchError((error) => {
                console.error('Error', error);
                return of(BooksActions.loadOneBookFailure({ error }));
              })
            )
      ),
    )
  }, { functional: true }
);
