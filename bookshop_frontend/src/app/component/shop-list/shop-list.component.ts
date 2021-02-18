import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ShopService } from 'src/app/_services/shop/shop.service';

@Component({
  selector: 'app-shop-list',
  templateUrl: './shop-list.component.html',
  styleUrls: ['./shop-list.component.css']
})
export class ShopListComponent implements OnInit {

  constructor(private shopService: ShopService, protected router: Router) { }

  info = Array<Object[]>();

  ngOnInit(): void {
    this.shopService.getShopStat().subscribe(data=>{
      this.info=data;
      console.log(this.info);
    });
  }

  gotoShop(shopId: number){
    this.router.navigate([`/shop/page/${shopId}`]);
  }

}
