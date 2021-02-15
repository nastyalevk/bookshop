import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Book } from 'src/app/model/book/book';
import { BookReview } from 'src/app/model/review/book/book-review';
import { ShopReview } from 'src/app/model/review/shop/shop-review';
import { Shop } from 'src/app/model/shop/shop';
import { BookService } from 'src/app/_services/book/book.service';
import { ReviewService } from 'src/app/_services/review/review.service';
import { ShopService } from 'src/app/_services/shop/shop.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';
import { NgbdModalContentComponent } from '../../ngbd-modal-content/ngbd-modal-content.component';

@Component({
  selector: 'app-edit-comment',
  templateUrl: './edit-comment.component.html',
  styleUrls: ['./edit-comment.component.css']
})
export class EditCommentComponent implements OnInit {

  shop: Shop;
  book: Book;
  reviewId: number;
  shopId: number;
  bookId: number;
  bookReview: BookReview;
  shopReview: ShopReview;
  isBookReview = false;
  isShopReview = false;
  private roles: string[] = [];
  isClient = false;
  isAdmin = false;
  isLoggedIn = false;

  constructor(private bookService: BookService, private shopService: ShopService, protected router: Router,
    private route: ActivatedRoute, private reviewService: ReviewService, private tokenStorageService: TokenStorageService,
    private modalService: NgbModal) {
    this.reviewId = this.route.snapshot.params.reviewId;
    this.shopId = this.route.snapshot.params.shopId;
    this.bookId = this.route.snapshot.params.bookId;
    this.book = new Book();
    this.shop = new Shop();
    this.bookReview = new BookReview();
    this.shopReview = new ShopReview();
    this.roles = this.tokenStorageService.getUser().roles;
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      this.isClient = this.roles.includes('ROLE_CLIENT');
      this.isAdmin = this.roles.includes('ROLE_ADMIN');
    }
  }

  ngOnInit(): void {
    if (this.bookId) {
      this.bookService.getOne(this.bookId).subscribe(data => {
        console.log(data);
        this.book = data;
        this.reviewService.getOneBookReview(this.reviewId).subscribe(data => {
          this.bookReview = data;
          console.log(data);
          this.isBookReview = true;
          this.isShopReview = false;
        });
      });
    }
    else {
      if (this.shopId)
        this.shopService.getShop(this.shopId).subscribe(data => {
          this.shop = data;
          this.reviewService.getOneShopReview(this.reviewId).subscribe(data => {
            this.shopReview = data;
            this.isShopReview = true;
            this.isBookReview = false;
          });
        });
    }
  }
  saveBookReviewAdmin() {
    this.bookReview.approved = true;
    this.reviewService.saveBookReview(this.bookReview).subscribe();
    this.router.navigate(['admin/book/comments']);
  }
  saveShopReviewAdmin() {
    this.shopReview.approved = true;
    this.reviewService.saveShopReview(this.shopReview).subscribe();
    this.router.navigate(['admin/shop/comments']);
  }
  saveBookReviewClient() {
    this.bookReview.approved = false;
    this.reviewService.saveBookReview(this.bookReview).subscribe();
    this.router.navigate([`book/${this.bookId}`]);
  }
  saveShopReviewClient() {
    this.shopReview.approved = false;
    this.reviewService.saveShopReview(this.shopReview).subscribe();
    this.router.navigate([`shop/${this.shopId}`]);
  }

  deleteBookReview() {
    if(this.isAdmin){
    this.reviewService.deleteBookReview(this.bookReview).subscribe(() => { this.router.navigate(['admin/book/comments']); },
      err => {
        console.log(err.error.message);
        const modalRef = this.modalService.open(NgbdModalContentComponent);
        modalRef.componentInstance.message = err.error.message;
      });}
      if(this.isClient){
        this.reviewService.deleteBookReview(this.bookReview).subscribe(() => {this.router.navigate([`book/${this.bookId}`]);},
          err => {
            console.log(err.error.message);
            const modalRef = this.modalService.open(NgbdModalContentComponent);
            modalRef.componentInstance.message = err.error.message;
          });}
  }

  deleteShopReview() {
    if(this.isAdmin){
    this.reviewService.deleteShopReview(this.shopReview).subscribe(() => { this.router.navigate(['admin/shop/comments']); },
      err => {
        console.log(err.error.message);
        const modalRef = this.modalService.open(NgbdModalContentComponent);
        modalRef.componentInstance.message = err.error.message;
      });}
      if(this.isClient){
        this.reviewService.deleteShopReview(this.shopReview).subscribe(() => {this.router.navigate([`shop/${this.shopId}`]);
      },
          err => {
            console.log(err.error.message);
            const modalRef = this.modalService.open(NgbdModalContentComponent);
            modalRef.componentInstance.message = err.error.message;
          });}
  }
}
