import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Shop } from 'src/app/model/shop/shop';
import { ShopService } from 'src/app/_services/shop/shop.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';

@Component({
  selector: 'app-board-owner',
  templateUrl: './board-owner.component.html',
  styleUrls: ['./board-owner.component.css']
})
export class BoardOwnerComponent implements OnInit {

  shops: Shop[] = [];
  private roles: string[] = [];
  isOwner = false;
  isLoggedIn = false;

  constructor(private shopService: ShopService, protected router: Router,
    private tokenStorageService: TokenStorageService) {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    console.log(this.isLoggedIn);
    if (this.isLoggedIn) {
      this.roles = this.tokenStorageService.getUser().roles;
      this.isOwner = this.roles.includes('ROLE_OWNER');
    }
  }

  ngOnInit(): void {
    if (this.isOwner) {
      this.shopService.getShopsByUsername().subscribe(data => {
        this.shops = data
      });
    }
  }

  createShop() {
    if (this.isOwner) {
      this.router.navigate([`new/shop`]);
    }
  }

  goToShopPage(id: number) {
    if (this.isOwner) {
      this.router.navigate([`orders/shop/${id}`]);
    }
  }
}
