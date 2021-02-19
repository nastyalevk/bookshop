import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Assortment } from 'src/app/model/assortment/assortment';
import { Cart } from 'src/app/model/cart/cart';
import { AssortmentService } from 'src/app/_services/assortment/assortment.service';
import { CartService } from 'src/app/_services/cart/cart.service';
import { ShopService } from 'src/app/_services/shop/shop.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  items: Cart[];
  fullPrice = 0;
  assortment: Assortment;
  isAvaliable: Map<string, boolean>;
  shopNames:  Map<number, string>;

  constructor(private router: Router, private cartService: CartService, private assortmentService: AssortmentService,
    private shopService: ShopService) {
    this.items = this.cartService.toArray();
    this.assortment = new Assortment();
    this.isAvaliable = new Map<string, boolean>();
    this.shopNames = new Map<number, string>();
  }

  ngOnInit(): void {
    this.items = this.cartService.toArray();
    this.fullPrice = 0;
    for (let i of this.items) {
      this.fullPrice += i.quantity * i.assortment.price;
    }
    this.isAvaliable.clear();
    for (let i of this.items) {
      this.isAvaliable.set(i.book.id.toString() + i.assortment.shopId.toString(), true);
    }
    for (let i of this.items) {
      this.shopService.getShop(i.assortment.shopId).subscribe(data => {
        this.shopNames.set(i.assortment.shopId, data.shopName);
      });
      this.assortmentService.getOne(i.book.id, i.assortment.shopId).subscribe(data => {
        this.assortment = data;
        if (this.assortment.quantity < i.quantity) {
          this.isAvaliable.set(i.book.id.toString() + i.assortment.shopId.toString(), false);
        } else {
          this.isAvaliable.set(i.book.id.toString() + i.assortment.shopId.toString(), true);

        }
      });
    }
  }

  remove(item: Cart) {
    this.cartService.remove(item);
    this.ngOnInit();
  }

  isOrderButton(): boolean {
    for (let [key, value] of this.isAvaliable) {
      if (!this.isAvaliable.get(key)) {
        return false
      }
    }
    return true
  }

  isBookAvaliable(bookId: number, shopId: number) {
    return this.isAvaliable.get(bookId.toString() + shopId.toString());
  }

  order() {
    this.router.navigate(['/order']);
  }

  handleMinus(item: Cart) {
    if (item.assortment.shopId = item.assortment.shopId) {
      if (item.quantity > 1) {

        item.quantity--;
        this.cartService.addToCart(item);
        this.fullPrice = 0;

        this.assortmentService.getOne(item.book.id, item.assortment.shopId).subscribe(data => {
          this.assortment = data;
          if (this.assortment.quantity < item.quantity) {
            this.isAvaliable.set(item.book.id.toString() + item.assortment.shopId.toString(), false);
          } else {
            this.isAvaliable.set(item.book.id.toString() + item.assortment.shopId.toString(), true);

          }
        });
        for (let i of this.items) {
          this.fullPrice += i.quantity * i.assortment.price;
        }
      }
    }
    console.log(this.items);

  }

  handlePlus(item: Cart) {
    if (item.assortment.shopId = item.assortment.shopId) {
      item.quantity++;
      this.cartService.addToCart(item);
      this.fullPrice = 0;

      this.assortmentService.getOne(item.book.id, item.assortment.shopId).subscribe(data => {
        this.assortment = data;
        if (this.assortment.quantity < item.quantity) {
          this.isAvaliable.set(item.book.id.toString() + item.assortment.shopId.toString(), false);
        } else {
          this.isAvaliable.set(item.book.id.toString() + item.assortment.shopId.toString(), true);

        }
      });
      for (let i of this.items) {
        this.fullPrice += i.quantity * i.assortment.price;
      }
    }
    console.log(this.items);
  }
}
