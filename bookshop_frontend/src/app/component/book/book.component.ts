import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { stat } from 'fs';
import { AppComponent } from 'src/app/app.component';
import { Assortment } from 'src/app/model/assortment/assortment';
import { Book } from 'src/app/model/book/book';
import { Cart } from 'src/app/model/cart/cart';
import { BookReview } from 'src/app/model/review/book/book-review';
import { Shop } from 'src/app/model/shop/shop';
import { AssortmentService } from 'src/app/_services/assortment/assortment.service';
import { BookService } from 'src/app/_services/book/book.service';
import { CartService } from 'src/app/_services/cart/cart.service';
import { ReviewService } from 'src/app/_services/review/review.service';
import { ShopService } from 'src/app/_services/shop/shop.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})

export class BookComponent implements OnInit {
  id: number;
  book: Book;
  minPrices: number[] = [];
  minPrice = 0;
  isCart: Map<string, boolean>;
  reviews: BookReview[] = [];
  review: BookReview;
  isAvaliable = false;
  assortments: Assortment[] = [];
  shopAssortment: Map<Assortment, Shop>;
  private roles: string[] = [];
  isClient = false;
  isLoggedIn = false;
  currentUser = "";
  status = ["out of stock", "low", "medium", "high", "unknown"];
  today = new Date();
  dd = String(this.today.getDate()).padStart(2, '0');
  mm = String(this.today.getMonth() + 1).padStart(2, '0');
  yyyy = String(this.today.getFullYear());
  hh = String(this.today.getHours());
  MM = String(this.today.getMinutes());
  ss = String(this.today.getSeconds());

  constructor(private route: ActivatedRoute, protected router: Router,
    private bookService: BookService, private appComponent: AppComponent,
    private cartService: CartService, private assortmentService: AssortmentService, private shopService: ShopService,
    private reviewService: ReviewService, private tokenStorageService: TokenStorageService, private config: NgbRatingConfig) {
    this.id = this.route.snapshot.params.id;
    this.book = new Book();
    this.isCart = new Map<string, boolean>();
    this.review = new BookReview();
    this.review.rating = 0;
    this.roles = this.tokenStorageService.getUser().roles;
    this.shopAssortment = new Map<Assortment, Shop>();
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    console.log(this.isLoggedIn);
    if (this.isLoggedIn) {
      this.isClient = this.roles.includes('ROLE_CLIENT');
      this.currentUser = this.tokenStorageService.getUser().username;
    }
    config.max = 10;
  }

  ngOnInit() {
    this.bookService.getOne(this.id).subscribe(
      data => {
        this.book = data;
        this.assortmentService.getByBook(data.id).subscribe(data => {
          this.assortments = data;
          console.log(this.assortments);
          for (let i of this.assortments) {
            this.shopService.getShop(i.shopId).subscribe(data => {
              this.shopAssortment.set(i, data);
              if (this.shopAssortment.keys()) {
                this.isAvaliable = true;
                for (let j of this.shopAssortment.keys()) {
                  this.minPrices.push(j.price);
                }
                this.minPrice = this.minPrices.sort((n1, n2) => n1 - n2)[0];
              } else {
                this.isAvaliable = false;
              }
            });
          }
        });
      },
      () => {
        this.router.navigate([`/error`]);
      });
    this.reviewService.getBookReview(this.id).subscribe(data => {
      this.reviews = data;
    });
  }

  onSubmit(assortment: Assortment) {
    if (this.appComponent.isClient()) {
      let key = "book_" + this.id.toString() + "_" + assortment.shopId;
      this.cartService.addToCart(new Cart(this.book, 1, assortment));
      this.isCart.set(key, true);
    }
    else {
      this.router.navigate(['/login']);
    }
  }

  inCart(shopId: number) {
    let key = "book_" + this.id.toString() + "_" + shopId;
    if (this.cartService.getItem(key)) {
      return true;
    }
    return false;
  }

  goToShop(shopId: number) {
    this.router.navigate([`/shop/page/${shopId}`]);
  }

  saveComment() {
    this.review.bookId = this.id;
    this.review.datetime = this.yyyy + "-" + this.mm + "-" + this.dd + " " + this.hh + ":" + this.MM + ":" + this.ss;
    this.reviewService.saveBookReview(this.review).subscribe(() => { this.ngOnInit(); });
  }

  isOpen(classification: string): boolean {
    if (classification == "OPEN") {
      return true;
    }
    if (classification == "ACTIVE") {
      return true;
    }
    return false;
  }
  editComment(reviewId: number) {
    this.router.navigate([`review/${reviewId}/book/${this.book.id}`]);
  }

  getStatus(quantity: number) {
    if (quantity <= 0) {
      return this.status[0];
    } else if (quantity <= 5) {
      return this.status[1];
    } else if (quantity > 5 && quantity <= 10) {
      return this.status[2];
    } else if (quantity > 10) {
      return this.status[3];
    }
    return this.status[4];
  }
}
