import { HttpInterceptorFn } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
 
export const jwtInterceptor: HttpInterceptorFn = (req, next) => {

    const token = localStorage.getItem('accessToken');

    if (token) {

      req = req.clone({
        setHeaders: {
          Authorization:
            `Bearer ${token}`
        }
      });
    }

     return next(req).pipe(

      catchError((error) => {

        if (error.status === 401) {

          localStorage.removeItem(
            'accessToken'
          );

          localStorage.removeItem(
            'refreshToken'
          );

        }

        return throwError(() => error);
      })

    );
  };
