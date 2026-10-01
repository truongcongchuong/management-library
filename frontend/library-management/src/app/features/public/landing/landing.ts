import { Component } from '@angular/core';
import { PublicFooter } from '../public-footer/public-footer';
import { Router, RouterLink } from '@angular/router';

@Component({
  imports: [PublicFooter, RouterLink],
  selector: 'app-landing',
  styleUrl: './landing.scss',
  templateUrl: './landing.html',
})
export class Landing {}
