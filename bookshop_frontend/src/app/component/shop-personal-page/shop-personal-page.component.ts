import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/model/book/book';
import { Shop } from 'src/app/model/shop/shop';
import { BookService } from 'src/app/_services/book/book.service';
import { ShopService } from 'src/app/_services/shop/shop.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';

@Component({
  selector: 'app-shop-personal-page',
  templateUrl: './shop-personal-page.component.html',
  styleUrls: ['./shop-personal-page.component.css']
})
export class ShopPersonalPageComponent implements OnInit {

  shopId: number;
  books: Book[] = [];
  shop: Shop;
  private roles: string[] = [];
  isClient = false;
  isLoggedIn = false;

  currentBook?: Book;
  currentIndex = -1;
  page = 1;
  count = 0;
  pageSize = 5;
  pageSizes = [5, 10, 15];

  constructor(private route: ActivatedRoute, protected router: Router, private bookService: BookService,
    private shopService: ShopService, private tokenStorageService: TokenStorageService) {
    this.shopId = this.route.snapshot.params.shopId;
    this.shop = new Shop();
    this.roles = this.tokenStorageService.getUser().roles;
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      this.isClient = this.roles.includes('ROLE_CLIENT');
    }
  }

  ngOnInit(): void {
    this.shopService.getShop(this.shopId).subscribe(data => {
      console.log(this.shop);
      this.shop = data;
    });
    this.shopAssortment();
    console.log(this.isClient);
    console.log(this.shop);
  }

  shopAssortment() {
    this.bookService.getBooksByShop(this.page, this.pageSize, this.shopId).subscribe(
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

  setActiveBook(book: Book, index: number): void {
    this.currentBook = book;
    this.currentIndex = index;
  }

  handlePageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.page = 1;
    this.shopAssortment();
  }

  handlePageChange(event: number): void {
    this.page = event;
    this.shopAssortment();
  }

  goToBookPersonalPage(bookId: number) {
    this.router.navigate([`/book/${bookId}`]);
  }

  goToLogin() {
    this.router.navigate([`/login`]);
  }

  goToShopReview() {
    this.router.navigate([`/shop/${this.shopId}/review`]);
  }

}
