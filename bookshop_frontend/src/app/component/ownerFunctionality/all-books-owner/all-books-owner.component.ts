import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Assortment } from 'src/app/model/assortment/assortment';
import { Book } from 'src/app/model/book/book';
import { AssortmentService } from 'src/app/_services/assortment/assortment.service';
import { BookService } from 'src/app/_services/book/book.service';

@Component({
  selector: 'all-books-owner',
  templateUrl: './all-books-owner.component.html',
  styleUrls: ['./all-books-owner.component.css']
})
export class AllBooksOwnerComponent implements OnInit {

  books: Book[] = [];
  currentBook?: Book;
  currentIndex = -1;
  bookName = '';
  sort: string;
  sorts: Map<String, String>;
  buttons: Map<String, boolean>;

  page = 1;
  count = 0;
  pageSize = 9;
  pageSizes = [9, 12, 15];
  id: number;
  isInShop: boolean;
  assortment: Assortment
  value: boolean;
  
  constructor(private route: ActivatedRoute, protected router: Router, private bookService: BookService,
    private assortmentService: AssortmentService) {
    this.id = this.route.snapshot.params.id;
    this.sort = "";
    this.sorts = new Map([]);
    this.isInShop = true;
    this.buttons = new Map([]);
    this.value = true;
    this.assortment = new Assortment();
  }

  ngOnInit(): void {
    this.retrieveBooks();
  }

  getAllOrders() {
    this.router.navigate([`/orders/shop/${this.id}`]);
  }

  editShop() {
    this.router.navigate([`/shop/edit/${this.id}`]);
  }

  addNewBook() {
    this.router.navigate([`/shop/newBook/${this.id}`]);
  }

  addBook() { }

  shopAssortment() {
    this.router.navigate([`/shop/${this.id}`]);
  }
  retrieveBooks(): void {
    this.bookService.getAll(this.bookName, this.page, this.pageSize, this.sort)
      .subscribe(
        response => {
          const { content, totalElements } = response.body;

          this.books = content;
          this.booksInShop(this.books)
          this.count = totalElements;
          console.log(response);
        },
        error => {
          console.log(error);
        });
  }

  handlePageChange(event: number): void {
    this.page = event;
    this.retrieveBooks();
  }

  handlePageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.page = 1;
    this.retrieveBooks();
  }

  setActiveBook(book: Book, index: number): void {
    this.currentBook = book;
    this.currentIndex = index;
  }

  sortTable(column: string) {
    if (this.sorts.has(column)) {
      this.sorts.delete(column);
      this.sorts.set(column, "asc");
    }
    else {
      this.sorts.set(column, "desc");
    }
    this.sort = '';
    for (let [key, value] of this.sorts) {
      this.sort += "," + key + "_" + value;
    }
    this.sort = this.sort.slice(1, this.sort.length);
    this.retrieveBooks();
  }

  addToShop(id: string) {
    this.router.navigate([`shop/addBook/${this.id}/${id}`]);
  }
  deleteFromShop(bookId: number) {
    this.assortmentService.deleteAssortment(bookId, this.id).subscribe();
    this.retrieveBooks();
  }

  isBookInShop(id: string) {
    return this.buttons.get(id);
  }

  booksInShop(books: Book[]) {
    this.buttons.clear();
    for (let book of books) {
      this.assortmentService.existsByBook(book.id, this.id).subscribe(data => {
        this.buttons.set(book.id, data);
      });
    }
  }
}
