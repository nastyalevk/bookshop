import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { DateFormatterService } from 'src/app/_services/formatter/date-formatter.service';
import { ShopService } from 'src/app/_services/shop/shop.service';

@Component({
  selector: 'app-proceed-stat',
  templateUrl: './proceed-stat.component.html',
  styleUrls: ['./proceed-stat.component.css']
})
export class ProceedStatComponent implements OnInit {

  fromDate: NgbDateStruct;
  toDate: NgbDateStruct;
  isThereStat=false;
  shopId: number;
  result: number;
  constructor(private shopService: ShopService, private route: ActivatedRoute, 
    private dateFormatter: DateFormatterService, protected router: Router) { 
    this.shopId = this.route.snapshot.params.shopId;
  }

  ngOnInit(): void {
  }

  getStat() {
    this.isThereStat=true;
    this.shopService.getProceedStat(this.shopId, this.dateFormatter.formatDate(this.fromDate), this.dateFormatter.formatDate(this.toDate)).subscribe(data=>{
      this.result=data.body;
    });
  }
  getOrderStat(){
    this.router.navigate([`shop/${this.shopId}/stat/orders`]);
  }

}
