import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal, NgbDateStruct, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Cart } from 'src/app/model/cart/cart';
import { Order } from 'src/app/model/order/order';
import { OrderContent } from 'src/app/model/orderContent/order-content';
import { Shop } from 'src/app/model/shop/shop';
import { CartService } from 'src/app/_services/cart/cart.service';
import { OrderService } from 'src/app/_services/order/order.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';
import { NgbdModalContentComponent } from '../ngbd-modal-content/ngbd-modal-content.component';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  items: Cart[];
  orderContent: OrderContent;
  order = new Order();
  itemsInOrder: Cart[] = [];

  deliveryAddress: string | undefined;
  description: string | undefined;
  model: NgbDateStruct;
  today = new Date();
  dd = String(this.today.getDate()).padStart(2, '0');
  ddnew = String(this.today.getDate() + 1).padStart(2, '0');
  mm = String(this.today.getMonth() + 1).padStart(2, '0');
  yyyy = String(this.today.getFullYear());
  hh = String(this.today.getHours());
  MM = String(this.today.getMinutes());
  ss = String(this.today.getSeconds());

  constructor(private router: Router, private cartService: CartService,
    private tokenStorage: TokenStorageService, private orderService: OrderService, private modalService: NgbModal) {
    this.items = this.cartService.toArray();
    this.order.cost = 0;
    this.orderContent = new OrderContent();
  }

  ngOnInit(): void { }

  onSubmit() {
    let shops = this.cartService.getShopList();
    let orderAmound = shops.length;
    this.deliveryAddress = this.order.deliveryAddress;
    this.description = this.order.description;
    for (let i = 0; i < orderAmound; i++) {
      this.itemsInOrder = new Array<Cart>();
      this.setOrder(shops[i]);
      this.setItemsInOrder(shops[i]);
      console.log(this.order);
      this.saveOrder();
    }
  }

  setItemsInOrder(shop: number) {
    for (let item of this.items) {
      if (item.assortment.shopId == shop) {
        this.itemsInOrder.push(item);
        this.order.cost += item.quantity * item.assortment.price;
      }
    }
  }

  saveOrder() {
    this.orderService.saveOrder(this.order).subscribe(data => {
      this.order = data;
      for (let item of this.itemsInOrder) {
        this.orderContent = new OrderContent();
        this.orderContent.bookId = item.book.id;
        this.orderContent.orderId = data.orderId;
        this.orderContent.price = item.assortment.price;
        this.orderContent.quantity = item.quantity
        this.orderService.saveOrderContent(this.orderContent).subscribe(() => {
          this.ngOnInit()
        },
          err => {
            const modalRef = this.modalService.open(NgbdModalContentComponent);
            modalRef.componentInstance.message = err.error.message;
          });
      }

      this.cartService.clearCart();
      this.router.navigate(['/order-info']);
    },
      err => {
        const modalRef = this.modalService.open(NgbdModalContentComponent);
        modalRef.componentInstance.message = err.error.message;
      });
  }

  setOrder(shopId: number) {
    this.order = new Order();
    this.order.deliveryAddress = this.deliveryAddress;
    this.order.description = this.description;
    this.order.cost = 0;
    this.order.classification = 'OPEN';
    this.order.orderSubmitDate = this.yyyy + "-" + this.mm + "-" + this.dd + " " + this.hh + ":" + this.MM + ":" + this.ss;
    this.order.shopId = shopId;
    if (this.model) {
      this.dd = String(this.model.day);
      this.mm = String(this.model.month);
      this.yyyy = String(this.model.year);
      this.hh = "00";
      this.MM = "00";
      this.ss = "00";
      this.order.orderCompleteDate = this.yyyy + "-" + this.mm + "-" + this.dd + " " + this.hh + ":" + this.MM + ":" + this.ss;
    } else {
      this.order.orderCompleteDate = this.yyyy + "-" + this.mm + "-" + this.ddnew + " " + this.hh + ":" + this.MM + ":" + this.ss;
    }
    this.order.username = this.tokenStorage.getUser().username;
    this.order.orderNumber = Math.random() * 10000;
  }

}
