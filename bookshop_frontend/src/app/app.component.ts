import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './_services/token/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title(title: any) {
    throw new Error('Method not implemented.');
  }
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showOwnerBoard = false;
  showClientBoard = false;
  username?: string | undefined;

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showOwnerBoard = this.roles.includes('ROLE_OWNER');
      this.showClientBoard = this.roles.includes('ROLE_CLIENT');

      this.username = user.username;
    }
  }
  isAdmin() {
    return this.showAdminBoard;
  }
  isOwner() {
    return this.showOwnerBoard;
  }
  isClient() {
    return this.showClientBoard;
  }
  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}