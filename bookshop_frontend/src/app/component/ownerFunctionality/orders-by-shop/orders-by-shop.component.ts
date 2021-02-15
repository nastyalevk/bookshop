import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from 'src/app/model/order/order';
import { BookService } from 'src/app/_services/book/book.service';
import { OrderService } from 'src/app/_services/order/order.service';
import { ShopService } from 'src/app/_services/shop/shop.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';

@Component({
  selector: 'app-orders-by-shop',
  templateUrl: './orders-by-shop.component.html',
  styleUrls: ['./orders-by-shop.component.css']
})
export class OrdersByShopComponent implements OnInit {

  id: number;
  orders: Order[] = [];
  currentOrder?: Order;
  currentIndex = -1;
  username: string;
  isOwner = false;
  isLoggedIn = false;

  page = 1;
  count = 0;
  pageSize = 9;
  pageSizes = [9, 12, 15];
  constructor(private route: ActivatedRoute, protected router: Router, private orderService: OrderService,
    private tokenStorageService: TokenStorageService) {
    this.id = this.route.snapshot.params.id;
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    this.username = this.tokenStorageService.getUser().username;

  }

  ngOnInit(): void {
    this.getAllOrders();
    console.log(this.orders);
  }


  getAllOrders() {
    this.orderService.getOrderByShop(this.page, this.pageSize, this.id).subscribe(response => {
      const { content, totalElements } = response.body;

      this.orders = content;
      this.count = totalElements;
      console.log(response);
    },
      error => {
        console.log(error);
        this.router.navigate([`/error`]);
      });

  }
  editShop() {
    this.router.navigate([`/shop/edit/${this.id}`]);
  }
  addNewBook() {
    this.router.navigate([`/shop/newBook/${this.id}`]);
  }

  addBook() {
    this.router.navigate([`/shop/addBooks/${this.id}`]);
  }

  shopAssortment() {
    this.router.navigate([`/shop/${this.id}`]);
  }

  goToOrder(orderId: number) {
    this.router.navigate([`order/edit/${orderId}`]);
  }

  handlePageChange(event: number): void {
    this.page = event;
    this.getAllOrders();
  }

  handlePageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.page = 1;
    this.getAllOrders();
  }

  setActiveBook(order: Order, index: number): void {
    this.currentOrder = order;
    this.currentIndex = index;
  }
}
