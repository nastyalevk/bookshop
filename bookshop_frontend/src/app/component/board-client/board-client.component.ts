import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from 'src/app/model/order/order';
import { OrderService } from 'src/app/_services/order/order.service';
import { ShopService } from 'src/app/_services/shop/shop.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';

@Component({
  selector: 'app-board-client',
  templateUrl: './board-client.component.html',
  styleUrls: ['./board-client.component.css']
})
export class BoardClientComponent implements OnInit {
  orders: Order[] = [];
  currentOrder?: Order;
  currentIndex = -1;
  shops: Map<number, string>;
  page = 1;
  count = 0;
  pageSize = 9;
  pageSizes = [9, 12, 15];
  private roles: string[] = [];
  isClient = false;
  isLoggedIn = false;
  constructor(private orderService: OrderService, private router: Router,
    private shopService: ShopService, private tokenStorageService: TokenStorageService) {
      this.shops = new Map<number, string>();
      this.isLoggedIn = !!this.tokenStorageService.getToken();
      console.log(this.isLoggedIn);
      if (this.isLoggedIn) {
        this.roles = this.tokenStorageService.getUser().roles;
        console.log(this.roles);
        this.isClient = this.roles.includes('ROLE_CLIENT');
      }
     }

  ngOnInit(): void {
    this.getAllOrders();
    console.log(this.orders);
  }

  getAllOrders() {
    this.orderService.getOrderByUser(this.page, this.pageSize).subscribe(response => {
      const { content, totalElements } = response.body;

      this.orders = content;
      this.count = totalElements;
      this.shops.clear()
      for (let i of this.orders) {
        this.shopService.getShop(i.shopId).subscribe(data=>{
          this.shops.set(i.shopId, data.shopName);
        });
      }
      console.log(response);
    },
      error => {
        console.log(error);
      });
  }
  goToOrder(orderId: number) {
    this.router.navigate([`/order/edit/${orderId}`]);
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

  setActiveOrder(order: Order, index: number): void {
    this.currentOrder = order;
    this.currentIndex = index;
  }

}
