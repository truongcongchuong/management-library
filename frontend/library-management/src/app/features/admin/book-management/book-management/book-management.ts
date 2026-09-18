import { Component } from '@angular/core';
import { ViewCard } from '../view-card/view-card';
import { ViewTable } from '../view-table/view-table';

@Component({
  imports: [ViewCard, ViewTable],
  selector: 'app-book-management',
  styleUrl: './book-management.scss',
  templateUrl: './book-management.html',
})
export class BookManagement {

  isCardBook = true;

  viewToggle(isCard: boolean) {
    this.isCardBook = isCard;
  } 
}
