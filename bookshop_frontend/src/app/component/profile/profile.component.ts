import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../../_services/token/token-storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: any;

  constructor(private token: TokenStorageService) { }

  ngOnInit(): void {
    this.user = this.token.getUser();
  }
}