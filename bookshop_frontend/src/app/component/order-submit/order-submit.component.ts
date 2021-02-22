import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrderService } from 'src/app/_services/order/order.service';
import { ShopService } from 'src/app/_services/shop/shop.service';

@Component({
  selector: 'app-order-submit',
  templateUrl: './order-submit.component.html',
  styleUrls: ['./order-submit.component.css']
})
export class OrderSubmitComponent implements OnInit {

  message: Map<number, number>;
  parsedMessage: Map<string, number>;

  constructor(private router: Router, private orderService: OrderService, private shopService: ShopService) {
    this.message = this.orderService.orderNumber;
    this.parsedMessage=new Map<string, number>();
  }

  ngOnInit(): void {
    for (let [key, value] of this.message) {
      this.shopService.getShop(key).subscribe(data=>{
        this.parsedMessage.set(data.shopName, value);
      });
    }
  }

  onSubmit() {
    this.router.navigate(['/home']);
  }
}
