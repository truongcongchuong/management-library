import { Service } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { JwtPayload } from '../../models/jwt-payload';

@Service()
export class JwtService {
    getToken(): string | null {
        return localStorage.getItem('accessToken');
    }

    getPayload(): JwtPayload | null {

        const token = this.getToken();

        if (!token) {
        return null;
        }

        return jwtDecode<JwtPayload>(token);
    }

    getUserId(): number | null {
        const payload = this.getPayload();

        return payload?.userId ?? null;
    }
    getUsername(): string | null {

        const payload = this.getPayload();

        return payload?.username ?? null;
    }

    getRole(): string | null {

        const payload = this.getPayload();

        return payload?.role ?? null;
    }
}
