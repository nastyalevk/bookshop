import { Injectable } from '@angular/core';
import { Cart } from 'src/app/model/cart/cart';
import { OrderContent } from 'src/app/model/orderContent/order-content';
import { Shop } from 'src/app/model/shop/shop';
import { OrderService } from '../order/order.service';
import { ShopService } from '../shop/shop.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  items = new Map<string, Cart>();
  orderContent: OrderContent;

  constructor(shopService: ShopService) { }

  addToCart(item: Cart) {
    let key = "book_" + item.book.id.toString() + "_" + item.assortment.shopId;
    this.items.set(key, item);
  }

  getItems() {
    return this.items;
  }

  clearCart() {
    this.items = new Map<string, Cart>();
    return this.items;
  }

  getItem(key: string) {
    return this.items.get(key);
  }

  toArray(): Cart[] {
    return Array.from(this.items.values());
  }

  remove(item: Cart) {
    let key = "book_" + item.book.id.toString() + "_" + item.assortment.shopId;
    this.items.delete(key);
  }

  getShopList() {
    let shops = new Array<number>();
    for (let key of this.items.keys()) {
      shops.push(this.items.get(key).assortment.shopId)
    }
    return shops;
  }
}
