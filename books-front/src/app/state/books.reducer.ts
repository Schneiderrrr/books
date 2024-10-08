import { createReducer, on } from '@ngrx/store';
import * as BooksActions from './books.actions';

import { Book } from "../book";

export const BOOKS_FEATURE_KEY = 'books';

export interface BooksState {
    books: Book[];
    status: string;
    error: any;
}

export const initialBooksState: BooksState = {
    books: [],
    status: 'init',
    error: null
}

export const booksReducer = createReducer(
  initialBooksState,
    on(BooksActions.initBooks, (state) => ({
        ...state,
        status: 'loading' as const
    })),
    on(BooksActions.partOfBooks, (state) => ({
        ...state,
        status: 'loading' as const
    })),
    on(BooksActions.sortedBooks, (state) => ({
      ...state,
      status: 'loading' as const
    })),
    on(BooksActions.deleteBook, (state) => ({
      ...state,
      status: 'deleting' as const
    })),
    on(BooksActions.addBook, (state) => ({
      ...state,
      status: 'adding' as const
    })),
    on(BooksActions.loadBooksSuccess, (state, { books }) => ({
        ...state,
        books: books,
        status: 'loaded' as const
    })),
    on(BooksActions.loadBooksFailure, (state, { error }) => ({
        ...state,
        status: 'error' as const,
        error
    }))
);
