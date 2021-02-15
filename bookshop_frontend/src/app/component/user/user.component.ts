import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/model/user/user';
import { UserService } from 'src/app/_services/user/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user: User;
  roles: string[] = [];
  rolesChange: string[] = [];
  activatedFlag = 0;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService) {
    this.user = new User();
  }

  onSubmit() {
    if (this.rolesChange[0]) {
      let params: any = {};
      params[`roles`] = this.rolesChange;
      params[`id`] = this.user.id;
      params[`message`] = this.user.message?.replace(" ", "_");
      this.userService.updateRoles(params).subscribe();
    }
    this.userService.update(this.user).subscribe(result => this.gotoUserList());
  }

  gotoUserList() {
    this.router.navigate(['/admin']);
  }

  ngOnInit() {
    this.userService.getOne(this.route.snapshot.params.id).subscribe(data => {
      this.user = data;
      for (let i of this.user.roles) {
        this.roles.push(i.name);
        window.localStorage.setItem("roles", i.name);
      }
    })
  }

  getRoleStatus(role: string) {
    let result = false;
    for (let i of this.roles) {
      if (i == role) {
        result = true;
      }
    }
    return result;
  }

  activatedChange(state: boolean) {
    this.user.activated = state;
    this.activatedFlag++;
  }

  changeRoleStatus(roleName: string, state: boolean) {
    if (state) {
      this.roles.push(roleName);
      this.rolesChange.push(roleName + "-create");
    }
    else {
      this.roles = this.roles.filter(role => role != roleName);
      this.rolesChange.push(roleName + "-delete");
    }
  }
}