import { Actions, createEffect, ofType } from "@ngrx/effects";
import { inject } from "@angular/core";
import { catchError, switchMap, map, of } from "rxjs";

import * as BooksActions from "./books.actions";
import { BookService } from "../book.service";


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
