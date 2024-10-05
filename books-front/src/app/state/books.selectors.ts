import { createFeatureSelector, createSelector } from "@ngrx/store";
import {BOOKS_FEATURE_KEY, BooksState} from "./books.reducer";

export const selectBooksState = createFeatureSelector<BooksState>(BOOKS_FEATURE_KEY);

export const selectBooksStatus = createSelector(
  selectBooksState,
  (state: BooksState) => state.status
);

export const selectBooksError = createSelector(
  selectBooksState,
  (state: BooksState) => state.error
);

export const selectAllBooks = createSelector(
  selectBooksState,
  (state: BooksState) => state.books
);
