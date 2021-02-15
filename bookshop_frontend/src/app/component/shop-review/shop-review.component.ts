import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { ShopReview } from 'src/app/model/review/shop/shop-review';
import { Shop } from 'src/app/model/shop/shop';
import { ReviewService } from 'src/app/_services/review/review.service';
import { ShopService } from 'src/app/_services/shop/shop.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';

@Component({
  selector: 'app-shop-review',
  templateUrl: './shop-review.component.html',
  styleUrls: ['./shop-review.component.css']
})
export class ShopReviewComponent implements OnInit {
  shopId: number;
  shop: Shop;
  isClient = false;
  private roles: string[] = [];
  isLoggedIn = false;
  currentUser= "";
  reviews: ShopReview[] = [];
  review: ShopReview;

  today = new Date();
  dd = String(this.today.getDate()).padStart(2, '0');
  mm = String(this.today.getMonth() + 1).padStart(2, '0');
  yyyy = String(this.today.getFullYear());
  hh = String(this.today.getHours());
  MM = String(this.today.getMinutes());
  ss = String(this.today.getSeconds());

  constructor(private route: ActivatedRoute, protected router: Router, private reviewService: ReviewService,
    private shopService: ShopService, private tokenStorageService: TokenStorageService, private config: NgbRatingConfig) {
    this.shopId = this.route.snapshot.params.shopId;
    this.shop = new Shop();
    this.roles = this.tokenStorageService.getUser().roles;
    this.review = new ShopReview();
    this.review.rating = 0;
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      this.isClient = this.roles.includes('ROLE_CLIENT');
      this.currentUser = this.tokenStorageService.getUser().username;
    }
    config.max = 10;
  }

  ngOnInit(): void {
    this.shopService.getShop(this.shopId).subscribe(data => {
      console.log(this.shop);
      this.shop = data;
    });
    this.reviewService.getShopReview(this.shopId).subscribe(data => {
      this.reviews = data;
    });
  }
  goToLogin() {
    this.router.navigate([`/login`]);
  }

  goToShopAssortment() {
    this.router.navigate([`/shop/page/${this.shopId}/`]);
  }
  saveComment() {
    this.review.shopId = this.shopId;
    this.review.datetime = this.yyyy + "-" + this.mm + "-" + this.dd + " " + this.hh + ":" + this.MM + ":" + this.ss;
    console.log(this.review);
    this.reviewService.saveShopReview(this.review).subscribe(()=>{this.ngOnInit()});
  }

  editComment(reviewId: number){
    this.router.navigate([`review/${reviewId}/shop/${this.shop.id}`]);
  }
}
