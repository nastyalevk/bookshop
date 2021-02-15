import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Role } from 'src/app/model/role/role';
import { User } from 'src/app/model/user/user';
import { AuthService } from 'src/app/_services/auth/auth.service';
import { UserService } from 'src/app/_services/user/user.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent {
  user: User;
  roles: string[] = [];
  status = '';

  constructor(private router: Router,
    private userService: UserService, private authService: AuthService) {
    this.user = new User();
    this.user.activated = true;
    console.log(this.user.activated);
    this.status = 'Unbaned';
  }

  onSubmit() {
    this.user.roles = this.rolesToEntity();
    console.log(this.user);
    this.authService.newUser(this.user.username, this.user.email).subscribe(() => this.gotoUserList());
  }

  gotoUserList() {
    this.router.navigate(['/admin']);
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

  activatedChange() {
    if (this.user.activated == true) {
      this.user.activated = false;
      this.status = "Baned";
    } else {
      this.user.activated = true;
      this.status = "Unbaned";
    }
    console.log(this.status)
  }

  getActivated(): boolean {
    let result = false;
    if (this.user.activated) {
      console.log(result);
      result = true;
      return result;
    }
    console.log(result);
    return result;
  }

  changeRoleStatusClient(state: boolean) {
    if (state) {
      this.roles.push("ROLE_CLIENT");
    }
    else {
      this.roles = this.roles.filter(role => role != "ROLE_CLIENT");
      console.log(this.roles);
    }
  }

  rolesToEntity() {
    let rolesEntity = new Array<Role>();
    for (let i of this.roles) {
      if (i == "ROLE_ADMIN") {
        let role = new Role(1, "ROLE_ADMIN");
        rolesEntity.push(role);
      }
      if (i == "ROLE_OWNER") {
        let role = new Role(2, "ROLE_OWNER");
        rolesEntity.push(role);
      }
      if (i == "ROLE_CLIENT") {
        let role = new Role(3, "ROLE_CLIENT");
        rolesEntity.push(role);
      }
    }
    return rolesEntity;
  }

  changeRoleStatus(state: boolean, roleName: string) {
    console.log(this.roles);
    if (state) {
      this.roles.push(roleName);
    }
    else {
      this.roles = this.roles.filter(role => role != roleName);
    }
  }
}
