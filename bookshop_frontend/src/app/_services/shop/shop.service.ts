import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Shop } from 'src/app/model/shop/shop';

const Url = 'http://localhost:8087/shop';
const StatUrl = "http://localhost:8087/stat/";

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  constructor(private http: HttpClient) { }

  getShopsByUsername(): Observable<any> {
    return this.http.get(Url + "/username");
  }

  getShop(shopId: number | undefined): Observable<Shop>{
    return this.http.get<Shop>(Url + "/" + shopId);
  }

  getShopByBook(shopId: number): Observable<any>{
    return this.http.get(Url + "/book/" + shopId);
  }

  saveShop(shop: Shop):Observable<Shop> {
    return this.http.post<Shop>(Url + '/create/', shop);
  }
   getShopStat():Observable<any>{
     return this.http.get(StatUrl + 'shops');
   }
   getProceedStat(shopId: number, fromDate: string, toDate: string):Observable<any>{
    return this.http.get(StatUrl + `proceed/?shopId=${shopId}&beginning=${fromDate}&ending=${toDate}`);
  }
  getOrdersStat(shopId: number):Observable<any>{
    return this.http.get(StatUrl + `shop/order/${shopId}`);
  }
}
