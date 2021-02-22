import { Component, OnInit } from '@angular/core';
import { Book } from '../../model/book/book';
import { BookService } from 'src/app/_services/book/book.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PaginationService } from 'src/app/_services/pagination/pagination.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  books: Book[] = [];
  currentBook?: Book;
  currentIndex = -1;
  bookName = '';
  sort: string;
  sorts: Map<String, String>;

  page: number;
  count = 0;
  pageSize: number;
  pageSizes = [8, 12, 15];

  constructor(private bookService: BookService, protected router: Router, private route: ActivatedRoute,
    private paginationService: PaginationService) {
    this.page = this.paginationService.getPage();
    this.pageSize = this.paginationService.getSize();
    this.sort = "";
    this.sorts = new Map([]);
  }

  ngOnInit(): void {
    this.retrieveBooks();
  }
  getPageSize(): number{
    return this.paginationService.getSize();
  }

  retrieveBooks(): void {
    if(this.bookName!=''){
      this.page=1;
      this.pageSize=this.paginationService.getSize();
    }
    this.bookService.getAll(this.bookName, this.page, this.pageSize, this.sort)
      .subscribe(
        response => {
          const { content, totalElements } = response.body;

          this.books = content;
          this.count = totalElements;
          console.log(response);
        },
        error => {
          console.log(error);
        });
  }

  handlePageChange(event: number): void {
    this.page = event;
    this.paginationService.setPage(this.page);
    this.retrieveBooks();
  }

  handlePageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.page = 1;
    this.paginationService.setPage(this.page);
    this.paginationService.setSize(this.pageSize);
    this.retrieveBooks();
  }

  setActiveBook(book: Book, index: number): void {
    this.currentBook = book;
    this.currentIndex = index;
  }

  LinktoBook(id: number) {
    this.router.navigate([`book/${id}`]);
  }

  sortTable(column: string) {
    if (this.sorts.has(column)) {
      this.sorts.delete(column);
      this.sorts.set(column, "asc");
    } else {
      this.sorts.set(column, "desc");
    }
    this.sort = '';
    for (let [key, value] of this.sorts) {
      this.sort += "," + key + "_" + value;
    }
    this.sort = this.sort.slice(1, this.sort.length);
    this.retrieveBooks();
  }
}
