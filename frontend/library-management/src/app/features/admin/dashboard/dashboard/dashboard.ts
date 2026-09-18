import { Component } from '@angular/core';

import { PageHead } from '../page-head/page-head';
import { State } from '../state/state';
import { AppChart } from '../app-chart/app-chart';
import { Table } from '../table/table';

@Component({
  selector: 'app-dashboard',
  imports: [PageHead, State, AppChart, Table],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss'
})
export class Dashboard {
}