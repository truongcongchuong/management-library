import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginRequest } from '../../../../core/models/login-request';
import { Auth } from '../../../../core/services/auth/auth';
import { Router } from '@angular/router';
import { JwtService } from '../../../../core/services/jwt/jwt-service';
import { jwtInterceptor } from '../../../../core/interceptors/jwt.interceptor';

@Component({
  imports: [FormsModule],
  selector: 'app-login',
  styleUrl: './login.scss',
  templateUrl: './login.html',
})
export class Login {

  authService = inject(Auth);
  formLogin: LoginRequest = {
    email:  '',
    password: '' 
  };

  route = inject(Router);
  jwt = inject(JwtService);
  isShowPassword = false;
  rememberPassword = false;
  showPassword() {
    this.isShowPassword = !this.isShowPassword;
  }

  login() {
    console.log(this.formLogin);

    if (this.rememberPassword == false) {
      alert("bạn có nhớ mật khẩu không")
      return;
    }
    
    this.authService.login(this.formLogin)
    .subscribe({
      next: (response) => {
        console.log(response);

        localStorage.setItem(
          'accessToken',
          response.data.accessToken
        )

        localStorage.setItem(
          'refreshToken',
          response.data.refreshToken
        )
        if (this.jwt.getRole() == 'USER') {
          this.route.navigate(['user/home'])
        } else if (this.jwt.getRole() == 'ADMIN') {
          this.route.navigate(['admin/dashboard'])
        }
        
        alert("đăng nhập thành công");
      },
      error: (err) => {
        console.log(err);
        alert("đăng nhập thất bại");
      }
    });
  }
}
