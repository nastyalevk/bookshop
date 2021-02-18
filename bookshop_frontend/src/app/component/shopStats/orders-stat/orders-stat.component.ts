import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ShopService } from 'src/app/_services/shop/shop.service';

@Component({
  selector: 'app-orders-stat',
  templateUrl: './orders-stat.component.html',
  styleUrls: ['./orders-stat.component.css']
})
export class OrdersStatComponent implements OnInit {

  shopId: number;
  info = Array<Object[]>();

  constructor(protected router: Router, private route: ActivatedRoute, private shopService: ShopService) {
    this.shopId = this.route.snapshot.params.shopId;
   }

  ngOnInit(): void {
    this.shopService.getOrdersStat(this.shopId).subscribe(data=>{
      this.info=data;
      console.log(this.info);
    });
  }

  getProceedStat(){
    this.router.navigate([`shop/${this.shopId}/stat/proceed`]);
  }
}
