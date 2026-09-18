import { Component } from '@angular/core';
import { PublicFooter } from '../public-footer/public-footer';

@Component({
  imports: [PublicFooter],
  selector: 'app-landing',
  styleUrl: './landing.scss',
  templateUrl: './landing.html',
})
export class Landing {}
