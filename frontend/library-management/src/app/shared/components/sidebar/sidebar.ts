import { Component, inject } from '@angular/core';
import { JwtService } from '../../../core/services/jwt/jwt-service';
@Component({
  imports: [],
  selector: 'app-sidebar',
  styleUrl: './sidebar.scss',
  templateUrl: './sidebar.html',
})
export class Sidebar {

  jwt = inject(JwtService);

  role = this.jwt.getRole();
}
