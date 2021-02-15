import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-not-exist',
  templateUrl: './not-exist.component.html',
  styleUrls: ['./not-exist.component.css']
})
export class NotExistComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  onSubmit() {
    this.router.navigate(['/home']);
  }

}
