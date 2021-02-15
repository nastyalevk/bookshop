import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BookReview } from 'src/app/model/review/book/book-review';
import { ShopReview } from 'src/app/model/review/shop/shop-review';

const Url = 'http://localhost:8087/review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient) { }

  getShopReview(shopId: number): Observable<any>{
    return this.http.get(Url + "/shop/" + shopId);
  }

  getBookReview(bookId: number): Observable<any>{
    return this.http.get(Url + "/book/" + bookId);
  }

  getOneShopReview(reviewId: number): Observable<ShopReview>{
    return this.http.get<ShopReview>(Url + "/one/shop/" + reviewId);
  }

  getOneBookReview(reviewId: number): Observable<BookReview>{
    return this.http.get<BookReview>(Url + "/one/book/" + reviewId);
  }

  getShopReviewAdmin(): Observable<any>{
    return this.http.get(Url + "/approve/shop/");
  }

  getBookReviewAdmin(): Observable<any>{
    return this.http.get(Url + "/approve/book/");
  }

  saveBookReview(review: BookReview): Observable<BookReview>{
    return this.http.post<BookReview>(Url+ "/book/create/", review);
  }

  saveShopReview(review: ShopReview): Observable<ShopReview>{
    return this.http.post<ShopReview>(Url+ "/shop/create/", review);
  }

  deleteBookReview(review: BookReview): Observable<any>{
    return this.http.post(Url+ "/delete/book/", review);
  }

  deleteShopReview(review: ShopReview): Observable<any>{
    return this.http.post(Url+ "/delete/shop/", review);
  }
}
