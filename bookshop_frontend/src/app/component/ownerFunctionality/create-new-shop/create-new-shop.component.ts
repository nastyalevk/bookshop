import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Shop } from 'src/app/model/shop/shop';
import { User } from 'src/app/model/user/user';
import { ShopService } from 'src/app/_services/shop/shop.service';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';
import { UserService } from 'src/app/_services/user/user.service';

@Component({
  selector: 'app-create-new-shop',
  templateUrl: './create-new-shop.component.html',
  styleUrls: ['./create-new-shop.component.css']
})
export class CreateNewShopComponent implements OnInit {

  shop: Shop;
  user: User;
  classifications=["QOPEN", "TERMINATED", "CLOSED"];

  constructor(protected router: Router, private shopService: ShopService,
    private userService: UserService, private tokenStorage: TokenStorageService) {
    this.shop = new Shop();
  }

  ngOnInit(): void {
  }

  onSubmitShop() {
    this.user = this.tokenStorage.getUser();
    this.userService.getUserByUsername(this.user.username).
      subscribe(data => {
        this.shop.userId = data.id;
        this.shop.classificationId = 1;
        this.shopService.saveShop(this.shop).subscribe();
        this.router.navigate(['/owner']);
      });
  }

}
