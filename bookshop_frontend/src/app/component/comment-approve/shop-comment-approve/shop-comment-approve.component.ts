import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ShopReview } from 'src/app/model/review/shop/shop-review';
import { ReviewService } from 'src/app/_services/review/review.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';

@Component({
  selector: 'app-shop-comment-approve',
  templateUrl: './shop-comment-approve.component.html',
  styleUrls: ['./shop-comment-approve.component.css']
})
export class ShopCommentApproveComponent implements OnInit {

  reviews: ReviewService[] = [];
  private roles: string[] = [];
  isAdmin = false;
  isLoggedIn = false;
  constructor(protected router: Router, private reviewService: ReviewService, private tokenStorageService: TokenStorageService) {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    console.log(this.isLoggedIn);
    if (this.isLoggedIn) {
      this.roles = this.tokenStorageService.getUser().roles;
      console.log(this.roles);
      this.isAdmin = this.roles.includes('ROLE_ADMIN');
    }}

  ngOnInit(): void {
    this.reviewService.getShopReviewAdmin().subscribe(data=>{
      this.reviews = data;
    });
  }

  editUsers() {
    this.router.navigate(['admin']);
  }

  BookApproveComment() {
    this.router.navigate(['admin/book/comments']);
  }
  ShopApproveComment() {
    this.router.navigate(['admin/shop/comments']);
  }

  approveShop(review: ShopReview){
    review.approved = true;
    console.log(review);
    this.reviewService.saveShopReview(review).subscribe(()=>{
      this.ngOnInit()
    });
    
  }
  editComment(reviewId: number, shopId: number){
    this.router.navigate([`review/${reviewId}/shop/${shopId}`]);
  }
}
