import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/_services/auth/auth.service';

@Component({
  selector: 'app-token-verification',
  templateUrl: './token-verification.component.html',
  styleUrls: ['./token-verification.component.css']
})
export class TokenVerificationComponent implements OnInit {
  isSuccessful = false;
  errorMessage = '';

  token: string;
  isVerified = false;
  constructor(private route: ActivatedRoute, private authService: AuthService) {
    this.token = this.route.snapshot.params.token;
  }

  ngOnInit(): void {
    this.authService.verifyToken(this.token).subscribe(() => {
      this.isSuccessful = true;
    },
    err => {
      this.errorMessage = err.error.message;
      this.isSuccessful = false;
    }
  );
  }


}
