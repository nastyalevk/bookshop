import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user/user';
import { UserService } from '../../_services/user/user.service';
import 'ag-grid-community/dist/styles/ag-grid.css';
import 'ag-grid-community/dist/styles/ag-theme-alpine-dark.css';
import { TokenStorageService } from 'src/app/_services/token/token-storage.service';

@Component({
  // selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {

  users = new Array();
  row: any[];
  private roles: string[] = [];
  isAdmin = false;
  isLoggedIn = false;

  page = 1;
  count = 0;
  pageSize = 8;
  pageSizes = [8, 12, 15];
  currentUser?: User;
  currentIndex = -1;

  constructor(private userService: UserService, protected router: Router,
    private tokenStorageService: TokenStorageService) {
    this.row = [];
    this.users = new Array<User>();
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    console.log(this.isLoggedIn);
    if (this.isLoggedIn) {
      this.roles = this.tokenStorageService.getUser().roles;
      console.log(this.roles);
      this.isAdmin = this.roles.includes('ROLE_ADMIN');
    }
  }

  ngOnInit() {
    // if (this.isAdmin) {
      this.userService.findAll(this.page, this.pageSize).subscribe(
        response => {
          const {content, totalElements} = response.body;

          this.users = content;
          this.count = totalElements;
          console.log(content);
        },
        error => {
          console.log(error);
        }
      );
    // }
  }

  getSelectedRow(id: number) {
    this.router.navigate([`edit-user/${id}`]);
  }

  addNew() {
    if (this.isAdmin) {
      this.router.navigate(['new']);
    }
  }

  editUsers() {
    this.router.navigate(['admin']);
  }

  BookApproveComment() {
    this.router.navigate(['admin/book/comments']);
  }

  ShopApproveComment() {
    this.router.navigate(['admin/shop/comments']);
  }

  getRoles(params: any) {
    var result = '';
    for (let i of params) {
      result += i.name.substr(5, i.name.lenght) + " ";
    }
    return result;
  }

  handlePageChange(event: number): void {
    this.page = event;
    this.ngOnInit();
  }

  handlePageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.page = 1;
    this.ngOnInit();
  }

  setActiveUser(user: User, index: number): void {
    this.currentUser = user;
    this.currentIndex = index;
  }

  getFullname(firstName: string, lastName: string){ 
    let result='';
    if(firstName){
      result=firstName + " ";
    }
    if(lastName){
      result=lastName;
    }
    return result;
  }
}

