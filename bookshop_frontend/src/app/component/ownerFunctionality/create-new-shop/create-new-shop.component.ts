import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {Shop} from 'src/app/model/shop/shop';
import {User} from 'src/app/model/user/user';
import {ShopService} from 'src/app/_services/shop/shop.service';
import {TokenStorageService} from 'src/app/_services/token/token-storage.service';
import {UserService} from 'src/app/_services/user/user.service';
import { NgbdModalContentComponent } from '../../ngbd-modal-content/ngbd-modal-content.component';

@Component({
  selector: 'app-create-new-shop',
  templateUrl: './create-new-shop.component.html',
  styleUrls: ['./create-new-shop.component.css']
})
export class CreateNewShopComponent implements OnInit {

  shop: Shop;
  classifications = ["OPEN", "TERMINATED", "CLOSED"];

  constructor(protected router: Router, private shopService: ShopService,
              private userService: UserService, private tokenStorage: TokenStorageService,
              private modalService: NgbModal) {
    this.shop = new Shop();
  }

  ngOnInit(): void {
  }

  onSubmitShop() {
      this.shopService.saveShop(this.shop).subscribe(
        ()=>{
      this.router.navigate(['/owner']);}, 
      err => {
        const modalRef = this.modalService.open(NgbdModalContentComponent);
        modalRef.componentInstance.message = err.error.message;
      });
    
  }

}
