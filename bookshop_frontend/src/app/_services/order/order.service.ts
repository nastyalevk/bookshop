import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { _ } from 'ag-grid-community';
import { Observable } from 'rxjs';
import { Order } from 'src/app/model/order/order';
import { OrderContent } from 'src/app/model/orderContent/order-content';

const Url = 'http://localhost:8087/order';

@Injectable({
  providedIn: 'root'
})

export class OrderService {

  constructor(private http: HttpClient) {
  }
  saveOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(Url + '/create', order);
  }

  saveOrderContent(orderContent: OrderContent): Observable<OrderContent> {
    return this.http.post<OrderContent>(Url + '/content/create/', orderContent);
  }

  updateOrderContent(orderContent: OrderContent): Observable<OrderContent> {
    return this.http.post<OrderContent>(Url + '/content/update/', orderContent);
  }

  deleteOrderContent(orderContent: OrderContent) {
    return this.http.post(Url + '/content/delete/', orderContent);
  }

  getOrderByShop(page: number, pageSize: number, shopId: number): Observable<any> {
    return this.http.get(Url + `/shop/?page=${page - 1}&size=${pageSize}&shopId=${shopId}`);
  }

  getOne(id: number): Observable<Order> {
    return this.http.get<Order>(Url + '/' + id);
  }


  getOrderContents(orderId: number): Observable<any> {
    return this.http.get<any>(Url + '/content/' + orderId);
  }

  getOrderContent(orderId: number, bookId: number): Observable<OrderContent> {
    return this.http.get<OrderContent>(Url + '/content/' + orderId + "/" + bookId);
  }

  getOrderByUser(page: number, pageSize: number): Observable<any> {
    return this.http.get<any>(Url + `/client/?page=${page - 1}&size=${pageSize}`);
  }
}
