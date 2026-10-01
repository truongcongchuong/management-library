import { Service, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginRequest } from '../../models/login-request';
import { User } from '../../models/user';
import { ApiResponse } from '../../models/api-response';
import { AuthResponse } from '../../models/auth-response';
import { JwtPayload } from '../../models/jwt-payload';

@Service()
export class Auth {
    http = inject(HttpClient);

    private readonly apiLogin = "http://localhost:8080/auth/login";
    private readonly apiRegister = "http://localhost:8080/auth/register"

    login(formLogin: LoginRequest) {
        return this.http.post<ApiResponse<AuthResponse>>(
            this.apiLogin,
            formLogin
        );
    }

    register(newUser: User) {
        return this.http.post(
            this.apiRegister,
            newUser
        )
    }
}
