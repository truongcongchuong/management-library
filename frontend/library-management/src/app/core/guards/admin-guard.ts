import { inject } from '@angular/core';
import {
  CanActivateFn,
  Router
} from '@angular/router';

import { JwtService } from '../services/jwt/jwt-service';

export const adminGuard: CanActivateFn = (route, state) => {
 const jwt = inject(JwtService);

  const router = inject(Router);

  const role = jwt.getRole();

  if (role !== 'ADMIN') {

    router.navigate(['/']);

    return false;
  }

  return true;
};
