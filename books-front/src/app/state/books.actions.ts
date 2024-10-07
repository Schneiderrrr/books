import { createAction, props } from "@ngrx/store";
import { Book } from "../book";


export const initBooks = createAction('[Books Page] Init');

export const partOfBooks = createAction(
  '[Books Page] Part of books',
  props<{ take: number; skip: number }>(),
);

export const loadBooksSuccess = createAction(
  '[Books/API] Load Books Success',
  props<{ books: Book[] }>(),
);

export const loadBooksFailure = createAction(
  '[Books/API] Load Books Failure',
  props<{ error: any }>(),
);
