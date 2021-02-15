import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Shop } from 'src/app/model/shop/shop';

const Url = 'http://localhost:8087/shop';

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  constructor(private http: HttpClient) { }

  getShopsByUsername(): Observable<any> {
    return this.http.get(Url + "/username");
  }

  getShop(shopId: number): Observable<Shop>{
    return this.http.get<Shop>(Url + "/" + shopId);
  }

  getShopByBook(shopId: number): Observable<any>{
    return this.http.get(Url + "/book/" + shopId);
  }

  saveShop(shop: Shop):Observable<Shop> {
    return this.http.post<Shop>(Url + '/create/', shop);
  }
}
