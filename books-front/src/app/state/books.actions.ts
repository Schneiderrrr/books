import { createAction, props } from "@ngrx/store";
import { Book } from "../book";

export const initBooks = createAction('[Books Page] Init');

export const partOfBooks = createAction(
  '[Books Page] Part of books',
  props<{ take: number; skip: number }>(),
);

export const sortedBooks = createAction(
  '[Books Page] Sorted books',
  props<{ sort: string }>(),
);

export const deleteBook = createAction(
  '[Books Page] Delete books',
  props<{ id: number }>(),
);

export const addBook = createAction(
  '[Books Page] Add books',
  props<{ book: Book }>(),
);

export const loadBooksSuccess = createAction(
  '[Books/API] Load Books Success',
  props<{ books: Book[] }>(),
);

export const loadBooksFailure = createAction(
  '[Books/API] Load Books Failure',
  props<{ error: any }>(),
);

export const initBook = createAction(
  '[One book Page] Init book',
  props<{ id: number }>(),
);

export const updateBook = createAction(
  '[One book Page] Update book',
  props<{ book: Book }>(),
);

export const loadOneBookSuccess = createAction(
  '[Books/API] Load One Book Success',
  props<{ book: Book }>(),
);

export const loadOneBookFailure = createAction(
  '[Books/API] Load One Book Failure',
  props<{ error: any }>(),
);
