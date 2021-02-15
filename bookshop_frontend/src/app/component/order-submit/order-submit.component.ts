import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-order-submit',
  templateUrl: './order-submit.component.html',
  styleUrls: ['./order-submit.component.css']
})
export class OrderSubmitComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {}

  onSubmit() {
    this.router.navigate(['/home']);
  }
}
