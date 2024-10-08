import { createReducer, on } from '@ngrx/store';
import * as BooksActions from './books.actions';

import { Book } from "../book";

export const BOOKS_FEATURE_KEY = 'books';

export interface BooksState {
    books: Book[];
    book: Book | null;
    status: string;
    error: any;
}

export const initialBooksState: BooksState = {
    books: [],
    book: null,
    status: 'init',
    error: null
}

const statuses = {
  loading: 'loading',
  loaded: 'loaded',
  error: 'error',

  deleting: 'deleting',
  adding: 'adding',
  updating: 'updating',
}

export const booksReducer = createReducer(
  initialBooksState,
    on(BooksActions.initBooks, (state) => ({
        ...state,
        status: statuses.loading
    })),
    on(BooksActions.partOfBooks, (state) => ({
        ...state,
        status: statuses.loading
    })),
    on(BooksActions.sortedBooks, (state) => ({
      ...state,
      status: statuses.loading
    })),
    on(BooksActions.deleteBook, (state) => ({
      ...state,
      status: statuses.deleting
    })),
    on(BooksActions.addBook, (state) => ({
      ...state,
      status: statuses.adding
    })),
    on(BooksActions.loadBooksSuccess, (state, { books }) => ({
      ...state,
      books: books,
      status: statuses.loaded
    })),
    on(BooksActions.loadBooksFailure, (state, { error }) => ({
      ...state,
      status: statuses.error,
      error
    })),

    on(BooksActions.initBook, (state) => ({
      ...state,
      status: statuses.loading
    })),
    on(BooksActions.updateBook, (state) => ({
      ...state,
      status: statuses.updating
    })),
    on(BooksActions.loadOneBookSuccess, (state, { book }) => ({
      ...state,
      book: book,
      status: statuses.loaded
    })),
    on(BooksActions.loadOneBookFailure, (state, { error }) => ({
      ...state,
      status: statuses.error,
      error
    }))
);
